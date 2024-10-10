package AppClasses.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.MainZone.ViewStockTab.StockHistoryState

data class StockHistoryStateWithStockInfos (val history : StockHistoryState, val stockName : String, val stockTicker : String){
}