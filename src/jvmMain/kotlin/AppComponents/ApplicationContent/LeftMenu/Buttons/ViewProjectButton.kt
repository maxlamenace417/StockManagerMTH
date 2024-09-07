package AppComponents.ApplicationContent.LeftMenu.Buttons

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
@Preview
fun ViewProjectButton(modifier: Modifier = Modifier) {
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    if (!applicationState.currentProjectPath.isEmpty()) {
        Button(onClick = {
            MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
            BottomBarStateUtil.setBottomBarStateValue(
                bottomBarState.copy(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.View_Project
                    )
                )
            )
        }) {
            Image(
                painter = painterResource("img/folder.png"),
                contentDescription = ""
            )
        }
    }
}