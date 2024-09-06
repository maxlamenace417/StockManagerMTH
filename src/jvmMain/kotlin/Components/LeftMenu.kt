package Components

import Session.Session
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
        fun MainPart(session: Session, displayScreenToDisplay: (Session) -> Unit){
            Column{
                var displaySaveButton= !session.applicationParameters.lastStartedProjectPath.isNullOrEmpty()
                Button(onClick = {
                    var newSession = session.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.NewProject)
                    displayScreenToDisplay(newSession)
                }){
                    Image(painter = painterResource("img/folder_new.png"),
                        contentDescription = ""
                    )
                }
                Button(onClick = {
                    var newSession = session.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.Nothing)
                    displayScreenToDisplay(newSession)
                }){
                    Image(painter = painterResource("img/folder_load.png"),
                        contentDescription = ""
                    )
                }
                if(displaySaveButton) {
                    Button(onClick = {
                        var newSession = session.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.Nothing)
                        displayScreenToDisplay(newSession)
                    }) {
                        Image(
                            painter = painterResource("img/folder_save.png"),
                            contentDescription = ""
                        )
                    }
                }
                Button(onClick = { displaySaveButton =!displaySaveButton
                }){
                    Image(painter = painterResource("img/gear.png"),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}