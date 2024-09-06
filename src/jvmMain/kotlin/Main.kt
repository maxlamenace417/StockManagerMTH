// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import Components.LeftMenu
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
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.util.*

@Composable
@Preview
fun App(session:Session) {
    var text by remember { mutableStateOf("Hello, World!") }

    var genericTransactionWithInfoList = GenericTransactionWithInfoList()
    genericTransactionWithInfoList.AddGenericTransaction(BuyTransaction(Date(2024,1,1), 10,20.0,20*10*0.008))
    genericTransactionWithInfoList.AddGenericTransaction(SellTransaction(Date(2024,1,2), 5,35.0,35*5*0.005))
    genericTransactionWithInfoList.AddGenericTransaction(BuyTransaction(Date(2024,1,2), 10,40.0,40*10*0.008))
    genericTransactionWithInfoList.AddGenericTransaction(DividendTransaction(Date(2024,1,3), 15,1.0,0.0))
    genericTransactionWithInfoList.AddGenericTransaction(SellTransaction(Date(2024,1,4), 15,50.0,50*15*0.005))

    MaterialTheme {
        Row{
            LeftMenu.MainPart(session)
            Text(genericTransactionWithInfoList.ToString())
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

    Window(onCloseRequest = ::exitApplication, title = Translator.Translate(session.applicationParameters.language, AllTexts.Stock_Manager_MTH)) {

        App(session)
    }
}
