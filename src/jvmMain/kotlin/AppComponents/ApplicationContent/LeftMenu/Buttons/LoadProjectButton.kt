package AppComponents.ApplicationContent.LeftMenu.Buttons

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Storage.Project
import Translation.AllTexts
import Translation.Translator
import Utils.ProjectUtils
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.google.gson.Gson
import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.plaf.FileChooserUI

@Composable
@Preview
fun LoadProjectButton(modifier: Modifier = Modifier) {
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    Button(onClick = {
        val dialog = JFileChooser()
        dialog.fileSelectionMode = JFileChooser.FILES_ONLY
        dialog.addChoosableFileFilter(FileNameExtensionFilter(".json","json"))
        dialog.setAcceptAllFileFilterUsed(false);
        dialog.isVisible = true
        var fileToLoadPath = ""
        var name = ""
        if (dialog.showOpenDialog(Frame()) == JFileChooser.APPROVE_OPTION) {
            fileToLoadPath = dialog.selectedFile.absolutePath
            name = dialog.selectedFile.nameWithoutExtension
        }
        if(fileToLoadPath.isNullOrEmpty()){
            BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text = Translator.Translate(applicationState.language, AllTexts.Select_Valid_File)))
        }else{
            ProjectUtils.loadProject(fileToLoadPath,name)
        }
    }) {
        Image(
            painter = painterResource("img/folder_load.png"),
            contentDescription = ""
        )
    }
}