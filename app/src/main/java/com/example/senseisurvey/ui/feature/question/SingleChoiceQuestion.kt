package com.example.senseisurvey.ui.feature.question

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.design.QuestionWrapper
import com.example.senseisurvey.ui.feature.survey.Superhero

@Composable
fun SingleChoiceQuestion(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    @StringRes directionsResId: Int,
    possibleAnswers: List<Superhero>,
    selectedAnswer: Superhero?,
    onOptionSelected: (Superhero) -> Unit,
) {
    QuestionWrapper(
        modifier = modifier.selectableGroup(),
        titleResourceId = titleResId,
        directionsResourceId = directionsResId
    ) {
        possibleAnswers.forEach { superhero ->
            val selected = superhero == selectedAnswer
            RadioButtonWithImageRow(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(id = superhero.stringResourceId),
                imageResId = superhero.imageResourceId,
                selected = selected,
                onOptionSelected = { onOptionSelected(superhero) }
            )
        }
    }
}

@Composable
fun RadioButtonWithImageRow(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes imageResId: Int,
    selected: Boolean,
    onOptionSelected: () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .selectable(selected, onClick = onOptionSelected, role = Role.RadioButton),
        shape = MaterialTheme.shapes.small,
        color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(56.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .padding(start = 0.dp, end = 8.dp),
                painter = painterResource(id = imageResId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Box(modifier = Modifier.padding(8.dp)) {
                RadioButton(selected = selected, onClick = null)
            }
        }
    }
}

@Preview
@Composable
fun SingleChoiceQuestionPreview() {
    val possibleAnswers = listOf(
        Superhero(R.string.spark, R.drawable.spark),
        Superhero(R.string.lenz, R.drawable.lenz),
        Superhero(R.string.bugchaos, R.drawable.bug_of_chaos),
    )
    var selectedAnswer by remember { mutableStateOf<Superhero?>(null) }

    SingleChoiceQuestion(
        titleResId = R.string.pick_superhero,
        directionsResId = R.string.select_one,
        possibleAnswers = possibleAnswers,
        selectedAnswer = selectedAnswer,
        onOptionSelected = { selectedAnswer = it },
    )
}