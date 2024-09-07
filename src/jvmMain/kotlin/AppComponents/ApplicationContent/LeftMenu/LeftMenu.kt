package AppComponents.ApplicationContent.LeftMenu

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppComponents.ApplicationContent.LeftMenu.Buttons.NewProjectButton
import AppComponents.ApplicationContent.LeftMenu.Buttons.SaveProjectButton
import AppComponents.ApplicationContent.LeftMenu.Buttons.ViewProjectButton
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Preview
fun LeftMenu(modifier: Modifier = Modifier){
    Column{
        ViewProjectButton()
        SaveProjectButton()
        NewProjectButton()
    }
}