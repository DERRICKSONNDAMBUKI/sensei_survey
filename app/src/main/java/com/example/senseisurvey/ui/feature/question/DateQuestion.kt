package com.example.senseisurvey.ui.feature.question

import android.content.res.Configuration
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.R
import com.example.senseisurvey.common.util.date.getDefaultDateInMillis
import com.example.senseisurvey.common.util.date.simpleDateFormatPattern
import com.example.senseisurvey.ui.design.QuestionWrapper
import com.example.senseisurvey.ui.theme.SenseiSurveyTheme
import com.example.senseisurvey.ui.theme.slightlyDeemphasizedAlpha
import kotlinx.datetime.toInstant
import java.util.Locale

@Composable
fun DateQuestion(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    @StringRes directionResId: Int,
    dateInMillis: Long?,
    onClick: () -> Unit
) {
    QuestionWrapper(
        modifier = modifier,
        titleResourceId = titleResId
    ) {
        val dateFormat = SimpleDateFormat(simpleDateFormatPattern, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateString = dateFormat.format(dateInMillis ?: getDefaultDateInMillis())

        Button(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .height(54.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
                    .copy(alpha = slightlyDeemphasizedAlpha)
            ),
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f),
                text = dateString,
            )
            Icon(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                imageVector = Icons.Filled.ArrowDropDown, contentDescription = null
            )
        }
    }
}
@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DateQuestionPreview(){
    SenseiSurveyTheme {
        Surface {
            DateQuestion(
                titleResId = R.string.takeaway,
                directionResId = R.string.select_date,
                dateInMillis = "2023-01-01".toInstant().toEpochMilliseconds()
            ) {

            }
        }
    }
}