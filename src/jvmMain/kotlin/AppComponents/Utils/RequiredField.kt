package AppComponents.Utils

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
@Preview
fun RequiredField(modifier: Modifier = Modifier, text:String = "") {
    Row{
        Text(text)
        Text(" *", color = Color.Red)
    }
}