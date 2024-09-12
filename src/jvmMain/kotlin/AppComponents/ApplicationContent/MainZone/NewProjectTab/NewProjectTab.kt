package AppComponents.ApplicationContent.MainZone.NewProjectTab

import AppClasses.ApplicationStateUtil
import AppComponents.Utils.RequiredField
import Translation.AllTexts
import Translation.Translator
import Utils.ProjectUtils
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.Frame
import javax.swing.JFileChooser

@Composable
@Preview
fun NewProjectTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    var projectName by remember { mutableStateOf(TextFieldValue("")) }
    var projectDirectoryPathToSave by remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier) {
        Row(Modifier.padding(bottom = 5.dp)) {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Project_Name
                    )
                )
                Row {
                    BasicTextField(
                        projectName,
                        onValueChange = { newProjectName ->
                            projectName = newProjectName
                        },
                        Modifier.background(Color.White).fillMaxWidth().padding(5.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true
                    )
                }
            }
        }
        Row {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.New_Project_Directory_Path
                    )
                )
                Row {
                    BasicTextField(
                        projectDirectoryPathToSave,
                        onValueChange = { newProjectDirectoryPathToSave ->
                            projectDirectoryPathToSave = newProjectDirectoryPathToSave
                        },
                        Modifier.background(Color.White).fillMaxWidth(0.8f).padding(5.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true
                    )
                    Button(
                        onClick = {
                            val dialog = JFileChooser()
                            dialog.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                            dialog.setAcceptAllFileFilterUsed(false);
                            dialog.isVisible = true
                            if (dialog.showOpenDialog(Frame()) == JFileChooser.APPROVE_OPTION) {
                                projectDirectoryPathToSave = TextFieldValue(dialog.selectedFile.absolutePath)
                            }
                        }, Modifier.fillMaxWidth()
                    ) {
                        Text(Translator.Translate(applicationState.language, AllTexts.Select))
                    }
                }
            }
        }
        Button(onClick = {
            ProjectUtils.createProject(projectDirectoryPathToSave.text, projectName.text)
        }) {
            Text(Translator.Translate(applicationState.language, AllTexts.Validate))
        }
    }
}