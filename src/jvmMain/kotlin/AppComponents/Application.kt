package AppComponents

import AppClasses.ApplicationState
import AppClasses.ApplicationStateUtil
import AppComponents.ApplicationContent.BottomBar.BottomBar
import AppComponents.ApplicationContent.LeftMenu.LeftMenu
import AppComponents.ApplicationContent.MainZone.MainZone
import Session.Installer
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun Application(modifier: Modifier = Modifier){
    Column {
        Row {
            LeftMenu()
            MainZone()
        }
        BottomBar()
    }
}