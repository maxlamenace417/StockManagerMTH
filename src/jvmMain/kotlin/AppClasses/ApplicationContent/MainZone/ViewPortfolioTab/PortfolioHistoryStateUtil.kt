package AppClasses.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.MainZone.ViewStockTab.StockHistoryState
import androidx.compose.runtime.mutableStateOf

object PortfolioHistoryStateUtil {
    private val state = mutableStateOf(PortfolioHistoryState())

    fun getPortfolioHistoryStateValue(): PortfolioHistoryState =
        state.value

    fun setPortfolioHistoryStateValue(state: PortfolioHistoryState) {
        this.state.value = state
    }
}