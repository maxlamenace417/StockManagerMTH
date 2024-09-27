package AppComponents

import AppComponents.ApplicationContent.ApplicationContent
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Preview
fun Application(modifier: Modifier = Modifier){
    ApplicationContent()
}