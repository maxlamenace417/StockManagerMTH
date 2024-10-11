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

    fun getMaxSizeAndIndex() : Pair<Int,Int>{
        var maxSize = 0
        var index = -1
        for(i in 0..state.value.histories.size-1){
            if(state.value.histories[i].history.histories.size>maxSize){
                maxSize = state.value.histories[i].history.histories.size
                index = i
            }
        }
        return Pair(maxSize, index)
    }
}