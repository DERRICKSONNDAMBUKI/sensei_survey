package com.example.senseisurvey.ui.feature.question

import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.design.QuestionWrapper
import com.example.senseisurvey.ui.theme.SenseiSurveyTheme

@Composable
fun PhotoQuestion(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    imageUri: Uri?,
    getNewImageUri: () -> Uri,
    onPhotoTaken: (Uri) -> Unit,
) {
    val hasPhoto = imageUri != null
    val iconRes = if (hasPhoto) Icons.Filled.SwapHoriz else Icons.Filled.AddAPhoto
    var newImageUri: Uri? = null

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success -> if (success) onPhotoTaken(newImageUri!!) }
    )
    QuestionWrapper(modifier = modifier, titleResourceId = titleResId) {
        OutlinedButton(
            onClick = {
                newImageUri = getNewImageUri()
                cameraLauncher.launch(newImageUri)
            },
            shape = MaterialTheme.shapes.small,
            contentPadding = PaddingValues()
        ) {
            Column {
                if (hasPhoto) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(96.dp)
                            .aspectRatio(4 / 3f),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUri)
                            .crossfade(true)
                            .build(),
                        contentDescription = null
                    )
                } else {
                    PhotoDefaultImage(
                        modifier = Modifier
                            .padding(horizontal = 86.dp, vertical = 74.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.BottomCenter)
                        .padding(vertical = 26.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = iconRes, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = if (hasPhoto) R.string.retake_photo else R.string.add_photo))
                }
            }
        }
    }
}

@Composable
fun PhotoDefaultImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null
    )
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PhotoQuestionPreview() {
    SenseiSurveyTheme {
        Surface {
            PhotoQuestion(
                titleResId = R.string.selfie_skills,
                imageUri = Uri.parse("https://example.bogus/wow"),
                getNewImageUri = { Uri.EMPTY },
                onPhotoTaken = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
