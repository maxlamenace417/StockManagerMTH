package Components

import Session.Session
import Translation.AllTexts
import Translation.AvailableLanguages
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class LeftMenu {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session){
            Column{
                var displaySaveButton by remember { mutableStateOf(!session.applicationParameters.lastStartedProjectPath.isNullOrEmpty())}
                Button(onClick = {
                }){
                    Image(painter = painterResource("img/folder_new.png"),
                        contentDescription = ""
                    )
                }
                Button(onClick = {
                }){
                    Image(painter = painterResource("img/folder_load.png"),
                        contentDescription = ""
                    )
                }
                if(displaySaveButton) {
                    Button(onClick = {
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