package com.example.senseisurvey.ui.feature.question

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.design.QuestionWrapper
import com.example.senseisurvey.ui.theme.SenseiSurveyTheme

@Composable
fun SliderQuestion(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    value: Float?,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 3,
    @StringRes startTextRes: Int,
    @StringRes neutralTextRes: Int,
    @StringRes endTextRes: Int,
) {
    var sliderPosition by remember {
        mutableFloatStateOf(value ?: ((valueRange.endInclusive - valueRange.start) / 2))
    }
    QuestionWrapper(modifier = modifier, titleResourceId = titleResId) {
        Row {
            Slider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = sliderPosition, onValueChange = {
                    sliderPosition = it
                    onValueChange(it)
                },
                valueRange = valueRange,
                steps = steps
            )
        }
        Row {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f),
                text = stringResource(id = startTextRes),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f),
                text = stringResource(id = neutralTextRes),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f),
                text = stringResource(id = endTextRes),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SliderQuestionPreview() {
    SenseiSurveyTheme {
        Surface {
            SliderQuestion(
                titleResId = R.string.selfies,
                value = 0.4f,
                onValueChange = {},
                startTextRes = R.string.strongly_dislike,
                endTextRes = R.string.strongly_like,
                neutralTextRes = R.string.neutral
            )
        }
    }
}
