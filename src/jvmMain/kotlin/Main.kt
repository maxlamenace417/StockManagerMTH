// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import Components.BottomInfoZone
import Components.LeftMenu
import Components.MainZone
import Session.Installer
import Session.Session
import Storage.BuyTransaction
import Storage.DividendTransaction
import Storage.GenericTransactionWithInfoList
import Storage.SellTransaction
import Translation.AllTexts
import Translation.Translator
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.util.*

@Composable
@Preview
fun App(session:Session) {
    var sessionRemember by remember{ mutableStateOf(session) }
    MaterialTheme {
        Column {
            Row {
                LeftMenu.MainPart(sessionRemember)
                MainZone.MainPart(sessionRemember)
            }
            Spacer(modifier = Modifier.weight(1f))
            BottomInfoZone.MainPart(sessionRemember)
        }
        /*Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }*/
    }
}

fun main() = application {
    //Install on run if not already installed
    var Installer = Installer()
    Installer.Install()

    var session = Session()
    var sessionRemember by remember{ mutableStateOf(session) }

    Window(onCloseRequest = ::exitApplication, title = Translator.Translate(session.applicationParameters.language, AllTexts.Stock_Manager_MTH)) {

        App(sessionRemember)
    }
}
