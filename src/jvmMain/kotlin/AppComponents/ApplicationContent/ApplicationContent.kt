package AppComponents.ApplicationContent

import AppComponents.ApplicationContent.BottomBar.BottomBar
import AppComponents.ApplicationContent.LeftMenu.LeftMenu
import AppComponents.ApplicationContent.MainZone.MainZone
import Components.grayBoxStyle
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun ApplicationContent(modifier: Modifier = Modifier){
    MaterialTheme{
        Scaffold(bottomBar = {BottomBar(Modifier.padding(5.dp).grayBoxStyle())}){
            Column {
                Row {
                    LeftMenu(Modifier.padding(5.dp).grayBoxStyle())
                    MainZone()
                }
            }
        }
    }
}