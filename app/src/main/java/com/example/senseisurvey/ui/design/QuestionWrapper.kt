package com.example.senseisurvey.ui.design

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.ui.theme.slightlyDeemphasizedAlpha
import com.example.senseisurvey.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun QuestionWrapper(
    modifier: Modifier = Modifier,
    @StringRes titleResourceId: Int,
    @StringRes directionsResourceId: Int? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        QuestionTitle(title = titleResourceId)
        directionsResourceId?.let {
            Spacer(modifier = Modifier.height(18.dp))
            QuestionDirection(directionResId = it)
        }
        Spacer(modifier = Modifier.height(18.dp))

        content()
    }
}

@Composable
fun QuestionDirection(modifier: Modifier = Modifier, @StringRes directionResId: Int) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        text = stringResource(id = directionResId),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
        style = MaterialTheme.typography.bodySmall,
    )
}

@Composable
fun QuestionTitle(modifier: Modifier = Modifier, @StringRes title: Int) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 24.dp, horizontal = 16.dp),
        text = stringResource(id = title),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = slightlyDeemphasizedAlpha)
    )
}
