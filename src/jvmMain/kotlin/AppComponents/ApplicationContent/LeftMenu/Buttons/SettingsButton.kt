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
fun SettingsButton(modifier: Modifier = Modifier) {
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    Button(onClick = {
        MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.Settings))
        BottomBarStateUtil.setBottomBarStateValue(
            bottomBarState.copy(
                text = Translator.Translate(
                    applicationState.language,
                    AllTexts.Navigating_To_Settings_Tab
                )
            )
        )
    }) {
        Image(
            painter = painterResource("img/gear.png"),
            contentDescription = ""
        )
    }
}