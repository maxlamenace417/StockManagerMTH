package AppComponents.ApplicationContent.MainZone

import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppComponents.ApplicationContent.MainZone.CreateTransactionTab.CreateTransactionTab
import AppComponents.ApplicationContent.MainZone.PortfolioTab.CreatePortfolioTab
import AppComponents.ApplicationContent.MainZone.StockTab.CreateStockTab
import AppComponents.ApplicationContent.MainZone.StockTab.EditStockTab
import AppComponents.ApplicationContent.MainZone.EmptyViewTab.EmptyView
import AppComponents.ApplicationContent.MainZone.NewProjectTab.NewProjectTab
import AppComponents.ApplicationContent.MainZone.PortfolioTab.EditPortfolioTab
import AppComponents.ApplicationContent.MainZone.ViewPortfolioTab.ViewPortfolioTab
import AppComponents.ApplicationContent.MainZone.ViewPortfolioTab.ViewPortfolioTabHistogram
import AppComponents.ApplicationContent.MainZone.ViewProjectTab.ViewProjectTab
import AppComponents.ApplicationContent.MainZone.ViewStockTab.ViewStockTab
import androidx.compose.desktop.ui.tooling.preview.Preview
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
        MainZoneScreenToDisplay.CreateStock -> {
            CreateStockTab(modifier)
        }
        MainZoneScreenToDisplay.EditPortfolio -> {
            EditPortfolioTab(modifier)
        }
        MainZoneScreenToDisplay.ViewStock -> {
            ViewStockTab(modifier)
        }
        MainZoneScreenToDisplay.EditStock -> {
            EditStockTab(modifier)
        }
        MainZoneScreenToDisplay.CreateTransaction -> {
            CreateTransactionTab(modifier)
        }
        MainZoneScreenToDisplay.ViewPortfolioEvolution -> {
            ViewPortfolioTabHistogram(modifier)
        }
        else -> {
            EmptyView(modifier)
        }
    }
}