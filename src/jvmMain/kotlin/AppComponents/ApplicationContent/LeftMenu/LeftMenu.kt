package AppComponents.ApplicationContent.LeftMenu

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppComponents.ApplicationContent.LeftMenu.Buttons.*
import Components.grayBoxStyle
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Preview
fun LeftMenu(modifier: Modifier = Modifier){
    Column(modifier){
        ViewProjectButton()
        SaveProjectButton()
        NewProjectButton()
        LoadProjectButton()
        CloseProjectButton()
    }
}