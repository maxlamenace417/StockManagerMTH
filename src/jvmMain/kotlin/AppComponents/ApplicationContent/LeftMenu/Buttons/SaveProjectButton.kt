package AppComponents.ApplicationContent.LeftMenu.Buttons

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import Utils.ProjectUtils
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
@Preview
fun SaveProjectButton(modifier: Modifier = Modifier) {
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    if (!applicationState.currentProjectPath.isEmpty()) {
        Button(onClick = {
            ProjectUtils.saveProject()
        }) {
            Image(
                painter = painterResource("img/folder_save.png"),
                contentDescription = ""
            )
        }
    }
}