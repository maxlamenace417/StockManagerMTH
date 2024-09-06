package Components

import Session.Session
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class LeftMenu {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session){
            Column{
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
                Button(onClick = {
                }){
                    Image(painter = painterResource("img/folder_save.png"),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}