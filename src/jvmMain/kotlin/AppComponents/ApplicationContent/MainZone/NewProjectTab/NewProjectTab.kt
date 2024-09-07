package AppComponents.ApplicationContent.MainZone.NewProjectTab

import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Session.ProjectCreator
import SessionStateUtil
import Translation.AllTexts
import Translation.Translator
import Utils.ProjectUtils
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
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.awt.Frame
import javax.swing.JFileChooser

@Composable
@Preview
fun NewProjectTab(modifier: Modifier = Modifier){
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    var projectName by remember { mutableStateOf(TextFieldValue("")) }
    var projectDirectoryPathToSave by remember { mutableStateOf(TextFieldValue("")) }
    Column{
        Row {
            Text(
                Translator.Translate(
                    applicationState.language,
                    AllTexts.Project_Name
                )
            )
            Row {
                TextField(projectName, onValueChange = { newProjectName ->
                    projectName = newProjectName
                })
            }
        }
        Row {
            Text(
                Translator.Translate(
                    applicationState.language,
                    AllTexts.New_Project_Directory_Path
                )
            )
            Row {
                TextField(projectDirectoryPathToSave, onValueChange = { newProjectDirectoryPathToSave ->
                    projectDirectoryPathToSave = newProjectDirectoryPathToSave
                })
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
                    Text(Translator.Translate(applicationState.language, AllTexts.Select))
                }
            }
        }
        Button(onClick = {
            ProjectUtils.CreateProject(projectDirectoryPathToSave.text, projectName.text)
        }){
            Text(Translator.Translate(applicationState.language, AllTexts.Validate))
        }
    }
}