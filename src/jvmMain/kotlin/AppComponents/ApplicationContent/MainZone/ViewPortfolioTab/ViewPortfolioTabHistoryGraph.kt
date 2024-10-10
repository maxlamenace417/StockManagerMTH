package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Preview
fun ViewPortfolioTabHistoryGraph(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    Column(modifier.fillMaxWidth()) {
        ViewPortfolioTabHeader()
        ViewPortfolioTabHistoryBar()
    }
}