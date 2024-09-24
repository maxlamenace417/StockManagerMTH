// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import AppClasses.ApplicationStateUtil
import AppComponents.Application
import AppComponents.ApplicationContent.BottomBar.BottomBar
import AppComponents.ApplicationContent.LeftMenu.LeftMenu
import AppComponents.ApplicationContent.MainZone.MainZone
import Components.BottomInfoZone
import Components.LeftMenuOld
import Components.MainZone
import Components.grayBoxStyle
import Session.Installer
import Translation.AllTexts
import Translation.Translator
import Utils.BourseDirectParser
import Utils.URLUtils.Companion.fetch
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jsoup.Jsoup
import java.awt.Dimension


@Composable
@Preview
fun App() {
    val sessionState = SessionStateUtil.getSessionStateValue()
    MaterialTheme {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(modifier = Modifier.defaultMinSize(10.dp)) {
                LeftMenuOld.MainPart(sessionState)
                MainZone.MainPart(sessionState)
            }
            BottomInfoZone(Modifier.padding(5.dp).grayBoxStyle())
        }
    }
}

/*fun main() = application {
    //Install on run if not already installed
    var Installer = Installer()
    Installer.Install()

    //Load previous Session
    //TODO()


    val sessionState = SessionStateUtil.getSessionStateValue()
        Window(onCloseRequest = ::exitApplication, title = sessionState.project.projectName + Translator.Translate(sessionState.applicationParameters.language, AllTexts.Stock_Manager_MTH)) {
        App()
    }
}*/


fun main() = application {
    //Install on run if not already installed
    var installer = Installer()
    installer.Install()


    //Load previous Session
    //TODO()

    val applicationState = ApplicationStateUtil.getApplicationStateValue()

    //var test = BourseDirectParser.GetHistoryFromBourseDirectURL("https://www.boursedirect.fr/fr/marche/euronext-paris/amundi-FR0004125920-AMUN-EUR-XPAR/seance")

    Window(onCloseRequest = ::exitApplication, title = applicationState.title) {
        window.minimumSize = Dimension(1000,900)
        Application()
    }
}
