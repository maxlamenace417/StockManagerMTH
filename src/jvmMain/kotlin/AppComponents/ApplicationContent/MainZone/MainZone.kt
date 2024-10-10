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
import AppComponents.ApplicationContent.MainZone.SettingsTab.SettingsTab
import AppComponents.ApplicationContent.MainZone.ViewPortfolioTab.*
import AppComponents.ApplicationContent.MainZone.ViewProjectTab.ViewProjectTab
import AppComponents.ApplicationContent.MainZone.ViewStockTab.ViewStockTab
import AppComponents.ApplicationContent.MainZone.ViewStockTab.ViewStockTabHistory
import AppComponents.ApplicationContent.MainZone.ViewStockTab.ViewStockTabHistoryGraph
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
        MainZoneScreenToDisplay.ViewPortfolioEvolutionReal -> {
            ViewPortfolioTabHistogramReal(modifier)
        }
        MainZoneScreenToDisplay.ViewStockHistory -> {
            ViewStockTabHistory(modifier)
        }
        MainZoneScreenToDisplay.ViewStockHistoryGraph -> {
            ViewStockTabHistoryGraph(modifier)
        }
        MainZoneScreenToDisplay.Settings -> {
            SettingsTab(modifier)
        }
        MainZoneScreenToDisplay.ViewPortfolioData -> {
            ViewPortfolioTabData(modifier)
        }
        MainZoneScreenToDisplay.ViewPortfolioHistory -> {
            ViewPortfolioTabHistory(modifier)
        }
        MainZoneScreenToDisplay.ViewPortfolioHistoryGraph -> {
            ViewPortfolioTabHistoryGraph(modifier)
        }
        else -> {
            EmptyView(modifier)
        }
    }
}