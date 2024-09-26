package AppClasses.ApplicationContent.MainZone.ViewStockTab

import Utils.DateUtils
import Utils.HistoryBourseDirect
import java.time.LocalDate
import java.util.*

data class StockHistoryState(val histories : MutableList<HistoryBourseDirect> = mutableListOf<HistoryBourseDirect>(), val minDate : Date = DateUtils.get1WeekDateAtStart(), val maxDate : Date = DateUtils.getTodayDateAtEnd()){
}