package com.example.senseisurvey.ui.feature.survey

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.feature.question.DateQuestion
import com.example.senseisurvey.ui.feature.question.MultipleChoiceQuestion
import com.example.senseisurvey.ui.feature.question.PhotoQuestion
import com.example.senseisurvey.ui.feature.question.SingleChoiceQuestion
import com.example.senseisurvey.ui.feature.question.SliderQuestion

@Composable
fun FreeTimeQuestion(
    modifier: Modifier = Modifier,
    selectedAnswers: List<Int>,
    onOptionSelected: (selected: Boolean, answer: Int) -> Unit
) {
    MultipleChoiceQuestion(
        modifier = modifier,
        titleResId = R.string.in_my_free_time,
        directionsResId = R.string.select_all,
        possibleAnswers = listOf(
            R.string.read,
            R.string.work_out,
            R.string.play_games,
            R.string.dance,
            R.string.watch_movies
        ),
        selectedAnswers = selectedAnswers,
        onOptionSelected = onOptionSelected,
    )
}

@Composable
fun SuperheroQuestion(
    modifier: Modifier = Modifier,
    selectedAnswer: Superhero?,
    onOptionSelected: (Superhero) -> Unit
) {
    SingleChoiceQuestion(
        modifier = modifier,
        titleResId = R.string.pick_superhero,
        directionsResId = R.string.select_one,
        possibleAnswers = listOf(
            Superhero(R.string.spark, R.drawable.spark),
            Superhero(R.string.lenz, R.drawable.lenz),
            Superhero(R.string.bugchaos, R.drawable.bug_of_chaos),
            Superhero(R.string.frag, R.drawable.frag)
        ),
        selectedAnswer = selectedAnswer,
        onOptionSelected = onOptionSelected,
    )
}

@Composable
fun TakeawayQuestion(
    modifier: Modifier = Modifier,
    dateInMillis: Long?,
    onClick: () -> Unit
) {
    DateQuestion(
        modifier = modifier,
        titleResId = R.string.takeaway,
        directionResId = R.string.select_date,
        dateInMillis = dateInMillis,
        onClick = onClick
    )
}

@Composable
fun FeelingAboutSelfiesQuestion(
    modifier: Modifier = Modifier,
    value: Float?,
    onValueChange: (Float) -> Unit,
) {
    SliderQuestion(
        modifier = modifier,
        titleResId = R.string.selfies,
        value = value,
        onValueChange = onValueChange,
        startTextRes = R.string.strongly_dislike,
        neutralTextRes = R.string.neutral,
        endTextRes = R.string.strongly_like
    )
}

@Composable
fun TakeSelfieQuestion(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    getNewImageUri: () -> Uri,
    onPhotoTaken: (Uri) -> Unit
) {
    PhotoQuestion(
        modifier = modifier,
        titleResId = R.string.selfie_skills,
        imageUri = imageUri,
        getNewImageUri = getNewImageUri,
        onPhotoTaken = onPhotoTaken
    )
}