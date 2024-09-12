package AppComponents.ApplicationContent.MainZone

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppComponents.ApplicationContent.MainZone.CreatePortfolioTab.CreatePortfolioTab
import AppComponents.ApplicationContent.MainZone.EmptyViewTab.EmptyView
import AppComponents.ApplicationContent.MainZone.NewProjectTab.NewProjectTab
import AppComponents.ApplicationContent.MainZone.ViewPortfolioTab.ViewPortfolioTab
import AppComponents.ApplicationContent.MainZone.ViewProjectTab.ViewProjectTab
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Preview
fun MainZone(modifier: Modifier = Modifier){
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    when(mainZoneState.mainZoneScreenToDisplay){
        MainZoneScreenToDisplay.NewProject -> {
            NewProjectTab(modifier)
        }
        MainZoneScreenToDisplay.ViewProject -> {
            ViewProjectTab(modifier)
        }
        MainZoneScreenToDisplay.CreatePortfolio -> {
            CreatePortfolioTab(modifier)
        }
        MainZoneScreenToDisplay.ViewPortfolio -> {
            ViewPortfolioTab(modifier)
        }
        else -> {
            EmptyView(modifier)
        }
    }
}