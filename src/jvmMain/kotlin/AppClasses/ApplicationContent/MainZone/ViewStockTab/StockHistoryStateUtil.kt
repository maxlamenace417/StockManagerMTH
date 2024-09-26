package AppClasses.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.MainZone.MainZoneState
import androidx.compose.runtime.mutableStateOf

object StockHistoryStateUtil {
    private val state = mutableStateOf(StockHistoryState())

    fun getStockHistoryStateValue(): StockHistoryState =
        state.value

    fun setStockHistoryStateValue(state: StockHistoryState) {
        this.state.value = state
    }
}