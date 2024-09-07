package Components

import Session.ProjectCreator
import Session.SessionState
import SessionStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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

/*
Screen to create new project
 */
class NewProject {
    companion object {
        @Composable
        @Preview
        fun MainPart(sessionState: SessionState){
            var projectName by remember { mutableStateOf(TextFieldValue("")) }
            var projectDirectoryPathToSave by remember { mutableStateOf(TextFieldValue("")) }
            Column(modifier = Modifier.border(BorderStroke(1.dp,Color.Black)).fillMaxWidth()){
                Row {
                    Text(
                        Translator.Translate(
                            sessionState.applicationParameters.language,
                            AllTexts.Project_Name
                        )
                    )
                    Row {
                        BasicTextField(projectName, onValueChange = { newProjectName ->
                            projectName = newProjectName
                        }, modifier = Modifier.height(16.dp).background(Color.White))
                    }
                }
                Row {
                    Text(
                        Translator.Translate(
                            sessionState.applicationParameters.language,
                            AllTexts.New_Project_Directory_Path
                        )
                    )
                    Row {
                        BasicTextField(projectDirectoryPathToSave, onValueChange = { newProjectDirectoryPathToSave ->
                            projectDirectoryPathToSave = newProjectDirectoryPathToSave
                        }, modifier = Modifier.height(16.dp).background(Color.White))
                        Button(onClick = {
                            val dialog = JFileChooser()
                            dialog.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                            dialog.setAcceptAllFileFilterUsed(false);
                            dialog.isVisible = true
                            if (dialog.showOpenDialog(Frame()) == JFileChooser.APPROVE_OPTION) {
                                projectDirectoryPathToSave = TextFieldValue(dialog.selectedFile.absolutePath)
                            }
                        }
                        ) {
                            Text(Translator.Translate(sessionState.applicationParameters.language, AllTexts.Select))
                        }
                    }
                }
                Button(onClick = {
                    var tempSession = sessionState.copy()
                    tempSession.applicationParameters.lastStartedProjectPath = ProjectCreator.CreateProject(projectDirectoryPathToSave.text, projectName.text, tempSession)
                    var temp = tempSession.applicationParameters.lastStartedProjectPath.split("\\")
                    tempSession.project.projectName = temp[temp.size-1]
                    SessionStateUtil.setSessionStateValue(tempSession.copy())
                }){
                    Text(Translator.Translate(sessionState.applicationParameters.language, AllTexts.Validate))
                }
            }
        }
    }
}