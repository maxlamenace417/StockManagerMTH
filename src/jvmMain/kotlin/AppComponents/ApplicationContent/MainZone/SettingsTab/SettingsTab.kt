package AppComponents.ApplicationContent.MainZone.SettingsTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.AvailableLanguages
import Translation.Translator
import Utils.ApplicationParameters
import Utils.Installer
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
@Preview
fun SettingsTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    var language by remember { mutableStateOf(applicationState.language) }
    Column(modifier) {
        Column {
            Text(Translator.Translate(applicationState.language, AllTexts.Language))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(Translator.Translate(applicationState.language, AllTexts.English))
                RadioButton(selected = language == AvailableLanguages.EN, onClick = {
                    language = AvailableLanguages.EN
                })
                Text(Translator.Translate(applicationState.language, AllTexts.French))
                RadioButton(selected = language == AvailableLanguages.FR, onClick = {
                    language = AvailableLanguages.FR
                })
            }
        }
        Button(onClick = {
            //TODO() to evolve later
            var installer = Installer()
            ApplicationStateUtil.setApplicationStateValue(applicationState.copy(language=language, title = if(applicationState.project.projectName.isNullOrEmpty()){""}else{applicationState.project.projectName+" "} +Translator.Translate(language, AllTexts.Stock_Manager_MTH)))
            installer.SaveSessionParametersFile(ApplicationParameters(language = language))
        }) {
            Text(Translator.Translate(applicationState.language, AllTexts.Modify))
        }
    }
}