package AppComponents.ApplicationContent.LeftMenu.Buttons

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Components.grayBoxStyle
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun NewProjectButton(modifier: Modifier = Modifier) {
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    Button(onClick = {
        MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.NewProject))
        BottomBarStateUtil.setBottomBarStateValue(
            bottomBarState.copy(
                text = Translator.Translate(
                    applicationState.language,
                    AllTexts.Navigating_To_New_Project_Tab
                )
            )
        )
    }) {
        Image(
            painter = painterResource("img/folder_new.png"),
            contentDescription = ""
        )
    }
}