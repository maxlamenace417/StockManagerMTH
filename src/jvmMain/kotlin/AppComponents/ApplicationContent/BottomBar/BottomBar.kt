package AppComponents.ApplicationContent.BottomBar

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationStateUtil
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Preview
fun BottomBar(modifier: Modifier = Modifier){
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    Row{
        Text(bottomBarState.text)
    }
}