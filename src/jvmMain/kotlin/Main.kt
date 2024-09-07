// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import Components.BottomInfoZone
import Components.LeftMenu
import Components.MainZone
import Components.grayBoxStyle
import Session.Installer
import Session.SessionState
import Translation.AllTexts
import Translation.Translator
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application



@Composable
@Preview
fun App() {
    val sessionState = SessionStateUtil.getSessionStateValue()
    MaterialTheme {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(modifier = Modifier.defaultMinSize(10.dp)) {
                LeftMenu.MainPart(sessionState)
                MainZone.MainPart(sessionState)
            }
            BottomInfoZone(Modifier.padding(5.dp).grayBoxStyle())
        }
    }
}

fun main() = application {
    //Install on run if not already installed
    var Installer = Installer()
    Installer.Install()

    //Load previous Session
    //TODO()


    val sessionState = SessionStateUtil.getSessionStateValue()
        Window(onCloseRequest = ::exitApplication, title = sessionState.project.projectName + Translator.Translate(sessionState.applicationParameters.language, AllTexts.Stock_Manager_MTH)) {
        App()
    }
}
