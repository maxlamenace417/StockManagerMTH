package Components

import Session.Session
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

class BottomInfoZone {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session){
            Row{
                Text(Translator.Translate(session.applicationParameters.language,AllTexts.Stock_Manager_MTH))
            }
        }
    }
}