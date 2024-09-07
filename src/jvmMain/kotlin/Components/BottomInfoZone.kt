package Components

import Session.SessionState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
@Preview
fun BottomInfoZone(modifier: Modifier = Modifier) {
    val sessionState = SessionStateUtil.getSessionStateValue()
    Row(modifier) {
        Text(sessionState.bottomMessage)
    }
}