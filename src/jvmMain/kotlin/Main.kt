// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import Components.BottomInfoZone
import Components.LeftMenu
import Components.MainZone
import Session.Installer
import Session.Session
import Translation.AllTexts
import Translation.Translator
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.util.*

@Composable
@Preview
fun App(session:Session) {
    var sessionRemember by remember{ mutableStateOf(session) }
    MaterialTheme {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(modifier = Modifier.defaultMinSize(10.dp)) {
                LeftMenu.MainPart(sessionRemember) {
                    sessionRemember = it
                }
                MainZone.MainPart(sessionRemember){
                    sessionRemember = it
                }
            }
            BottomInfoZone.MainPart(sessionRemember)
        }
    }
}

fun main() = application {
    //Install on run if not already installed
    var Installer = Installer()
    Installer.Install()

    //Load previous Session
    //TODO()

    var session = Session()


    Window(onCloseRequest = ::exitApplication, title = Translator.Translate(session.applicationParameters.language, AllTexts.Stock_Manager_MTH)) {
        App(session)
    }
}
