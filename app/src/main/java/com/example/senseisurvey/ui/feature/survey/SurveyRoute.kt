package com.example.senseisurvey.ui.feature.survey

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.senseisurvey.common.util.Constants
import com.example.senseisurvey.common.util.PhotoUriManager
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun SurveyRoute(
    onSurfaceComplete: () -> Unit,
    onNavUp: () -> Unit
) {
    val viewModel: SurveyViewModel = viewModel(
        factory = SurveyViewModelFactory(
            PhotoUriManager(
                LocalContext.current
            )
        )
    )
    val surveyScreenData = viewModel.surveyScreenData
    BackHandler {
        if (!viewModel.onBackPressed()) onNavUp
    }

    SurveyQuestionScreen(
        surveyScreenData = surveyScreenData,
        isNextEnabled = viewModel.isNextEnable,
        onClosePressed = { onNavUp },
        onPreviousPressed = { viewModel.onPreviousPressed() },
        onNextPressed = { viewModel.onNextPressed() },
        onDonePressed = { viewModel.onDonePressed(onSurfaceComplete) }
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues = paddingValues)

        AnimatedContent(
            targetState = surveyScreenData,
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> =
                    tween(Constants.CONTENT_ANIMATION_DURATION)
                val direction = getTransitionDirection(
                    initialIndex = initialState.questionIndex,
                    targetState = targetState.questionIndex
                )
                slideIntoContainer(
                    towards = direction,
                    animationSpec = animationSpec
                ) togetherWith slideOutOfContainer(
                    towards = direction,
                    animationSpec = animationSpec
                )
            },
            label = "surveyScreenDataAnimation"
        ) { targetState ->
            when (targetState.surveyQuestion) {
                SurveyQuestion.FREE_TIME -> {
                    FreeTimeQuestion(
                        modifier = modifier,
                        selectedAnswers = viewModel.freeTimeResponse,
                        onOptionSelected = viewModel::onFreeTimeResponse
                    )
                }

                SurveyQuestion.SUPERHERO -> SuperheroQuestion(
                    modifier = modifier,
                    selectedAnswer = viewModel.superheroResponse,
                    onOptionSelected = viewModel::onSuperheroResponse
                )

                SurveyQuestion.LAST_TAKEAWAY -> {
                    val supportFragmentManager =
                        LocalContext.current.findActivity().supportFragmentManager
                    TakeawayQuestion(
                        modifier = modifier,
                        dateInMillis = viewModel.takeawayResponse,
                        onClick = {
                            showTakeawayDatePicker(
                                date = viewModel.takeawayResponse,
                                supportFragmentManager = supportFragmentManager,
                                onDateSelected = viewModel::onTakeawayResponse
                            )
                        }
                    )
                }

                SurveyQuestion.FEELING_ABOUT_SELFIES -> FeelingAboutSelfiesQuestion(
                    modifier = modifier,
                    value = viewModel.feelingAboutSelfiesResponse,
                    onValueChange = viewModel::onFeelingAboutSelfiesResponse
                )

                SurveyQuestion.TAKE_SELFIE -> TakeSelfieQuestion(
                    modifier = modifier,
                    imageUri = viewModel.selfieUri,
                    getNewImageUri = viewModel::getNewSelfieUri,
                    onPhotoTaken = viewModel::onSelfieResponse
                )
            }
        }

    }
}

fun showTakeawayDatePicker(
    date: Long?,
    supportFragmentManager: FragmentManager,
    onDateSelected: (date: Long) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker()
        .setSelection(date)
        .build()
    picker.show(supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        picker.selection?.let(onDateSelected) // collects the date
    }
}

private fun Context.findActivity(): AppCompatActivity =
    when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> this.findActivity()
        else -> throw IllegalArgumentException("could not find activity")
    }


fun getTransitionDirection(
    initialIndex: Int,
    targetState: Int
): AnimatedContentTransitionScope.SlideDirection {
    return if (targetState > initialIndex) {
        // Going forwards in the survey: Set the initial offset to start
        // at the size of the content so it slides in from right to left, and
        // slides out from the left of the screen to -fullWidth
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        // Going back to the previous question in the set, we do the same
        // transition as above, but with different offsets - the inverse of
        // above, negative fullWidth to enter, and fullWidth to exit.

        AnimatedContentTransitionScope.SlideDirection.Right
    }
}
