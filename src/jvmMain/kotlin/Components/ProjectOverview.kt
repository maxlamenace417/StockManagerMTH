package Components

import Session.ProjectCreator
import Session.Session
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.awt.Frame
import javax.swing.JFileChooser

class ProjectOverview {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session, endCreate: (Session) -> Unit) {
            Column{
                Row{
                    Button(onClick = {
                        //TODO()
                    }){
                        Text(Translator.Translate(session.applicationParameters.language, AllTexts.Create_Portfolio))
                    }
                }
                for(i in 0..session.project.portfolios.size-1){
                    Row{
                        Text(session.project.portfolios[i].name)
                    }
                }
            }

        }
    }
}