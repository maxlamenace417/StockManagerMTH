package Components

import Session.Session
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class LeftMenu {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session){
            Column{
                Button(onClick = {
                }){
                    Text(Translator.Translate(session.language, AllTexts.New_Project))
                }
                Button(onClick = {
                }){
                    Text(Translator.Translate(session.language, AllTexts.Load_Project))
                }
                Button(onClick = {
                }){
                    Text(Translator.Translate(session.language, AllTexts.Save_Project))
                }
            }
        }
    }
}