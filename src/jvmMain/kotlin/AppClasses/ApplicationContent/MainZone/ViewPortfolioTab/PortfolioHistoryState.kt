package AppClasses.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.MainZone.ViewStockTab.StockHistoryState
import Utils.DateUtils
import java.util.*

data class PortfolioHistoryState(val histories : MutableList<StockHistoryStateWithStockInfos> = mutableListOf<StockHistoryStateWithStockInfos>(), val minDate : Date = DateUtils.get1WeekDateAtStart(), val maxDate : Date = DateUtils.getTodayDateAtEnd()) {
}