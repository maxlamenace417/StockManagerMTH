// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import AppClasses.ApplicationStateUtil
import AppComponents.Application
import Components.BottomInfoZone
import Components.LeftMenuOld
import Components.MainZone
import Components.grayBoxStyle
import Translation.AllTexts
import Translation.Translator
import Utils.Installer
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
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

fun main() = application {
    //Install on run if not already installed
    var installer = Installer()
    installer.Install()


    //Load previous Session
    //TODO() Load previous Session

    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    //TODO() Load settings
    var sessionsParameters = installer.LoadSessionParameters()
    if(sessionsParameters!=null){
        ApplicationStateUtil.setApplicationStateValue(applicationState.copy(language=sessionsParameters.language, title=if(applicationState.project.projectName.isNullOrEmpty()){""}else{applicationState.project.projectName+" "} + Translator.Translate(sessionsParameters.language, AllTexts.Stock_Manager_MTH)))
    }
    val applicationState2 = ApplicationStateUtil.getApplicationStateValue()

    /*var url = "https://www.boursedirect.fr/fr/marche/euronext-paris/amundi-FR0004125920-AMUN-EUR-XPAR/seance"
    BourseDirectParser.SaveHistoryFromBourseDirectURL(url, "Amundi", "AMUN.PA")
    var test = BourseDirectParser.LoadHistoryCSVFromBourseDirect("Amundi", "AMUN.PA")*/

    Window(onCloseRequest = ::exitApplication, title = applicationState2.title) {
        window.minimumSize = Dimension(1000,900)
        Application()
    }
}
