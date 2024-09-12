package AppComponents.ApplicationContent.LeftMenu.Buttons

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Storage.Project
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.google.gson.Gson
import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
@Preview
fun CloseProjectButton(modifier: Modifier = Modifier) {
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    if (!applicationState.currentProjectPath.isEmpty()) {
        Button(onClick = {
            ApplicationStateUtil.setApplicationStateValue(applicationState.copy(project = Project(), currentProjectPath = "", title = Translator.Translate(applicationState.language, AllTexts.Stock_Manager_MTH)))
            MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.Nothing))
            BottomBarStateUtil.setBottomBarStateValue(
                bottomBarState.copy(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Project_Closed
                    )
                )
            )
        }) {
            Image(
                painter = painterResource("img/folder_close.png"),
                contentDescription = ""
            )
        }
    }
}