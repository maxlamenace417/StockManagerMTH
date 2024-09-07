package Components

import Session.SessionState
import SessionStateUtil
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource

class LeftMenu {
    companion object {
        @Composable
        @Preview
        fun MainPart(sessionState: SessionState) {
            Column {
                var displaySaveButton = sessionState.applicationParameters.lastStartedProjectPath.isNotEmpty()
                if(displaySaveButton) {
                    Button(onClick = {
                        var newSession = sessionState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.CurrentProject)
                        SessionStateUtil.setSessionStateValue(newSession)
                    }) {
                        Image(
                            painter = painterResource("img/folder.png"),
                            contentDescription = ""
                        )
                    }
                }
                Button(onClick = {
                    val newSession = sessionState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.NewProject)
                    SessionStateUtil.setSessionStateValue(newSession)
                }) {
                    Image(
                        painter = painterResource("img/folder_new.png"),
                        contentDescription = ""
                    )
                }
                Button(onClick = {
                    val newSession = sessionState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.Nothing)
                    SessionStateUtil.setSessionStateValue(newSession)
                }) {
                    Image(
                        painter = painterResource("img/folder_load.png"),
                        contentDescription = ""
                    )
                }
                if (displaySaveButton) {
                    Button(onClick = {
                        val newSession = sessionState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.Nothing)
                        SessionStateUtil.setSessionStateValue(newSession)
                    }) {
                        Image(
                            painter = painterResource("img/folder_save.png"),
                            contentDescription = ""
                        )
                    }
                }
                Button(onClick = {
                    displaySaveButton = !displaySaveButton
                }) {
                    Image(
                        painter = painterResource("img/gear.png"),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}