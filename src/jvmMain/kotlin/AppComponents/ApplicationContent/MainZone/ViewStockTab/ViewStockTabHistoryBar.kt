package AppComponents.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationContent.MainZone.ViewStockTab.StockHistoryState
import AppClasses.ApplicationContent.MainZone.ViewStockTab.StockHistoryStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import Utils.BourseDirectParser
import Utils.DateUtils
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.util.*

@Composable
@Preview
fun ViewStockTabHistoryBar(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val stockHistoryState = StockHistoryStateUtil.getStockHistoryStateValue()
    var portfolio = applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }
    var stock =
        portfolio.stocks.first { it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker }

    fun UpdatePeriod(minDate : Date, maxDate: Date){
        if(BourseDirectParser.HistoryCSVFromBourseDirectExists(stock.name, stock.ticker)){
            var histories = BourseDirectParser.GetHistoryFromBourseDirectCSV(stock.name, stock.ticker)
            histories.removeAll { it.date.compareTo(minDate)<=0 || it.date.compareTo(maxDate)>=0}
            var tempStockHistoryState = StockHistoryState(histories, minDate, maxDate)
            StockHistoryStateUtil.setStockHistoryStateValue(tempStockHistoryState)
            BottomBarStateUtil.setBottomBarStateValue(
                bottomBarState.copy(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.CSV_Loaded
                    ) + ": " + portfolio.name + " ==> " + stock.name + " (" + stock.ticker + ")"
                )
            )
        }else{
            StockHistoryStateUtil.setStockHistoryStateValue(StockHistoryState())
            BottomBarStateUtil.setBottomBarStateValue(
                bottomBarState.copy(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.No_CSV
                    ) + ": " + portfolio.name + " ==> " + stock.name + " (" + stock.ticker + ")"
                )
            )
        }
    }

    Row{
        Button(onClick = {
            //Download CSV from URL
            BourseDirectParser.SaveHistoryFromBourseDirectURL(stock.bourseDirectURL, stock.name, stock.ticker)
            if(BourseDirectParser.HistoryCSVFromBourseDirectExists(stock.name, stock.ticker)){
                var histories = BourseDirectParser.GetHistoryFromBourseDirectCSV(stock.name, stock.ticker)
                histories.removeAll { it.date.compareTo(stockHistoryState.minDate)<=0 || it.date.compareTo(stockHistoryState.maxDate)>=0}
                var tempStockHistoryState = StockHistoryState(histories)
                StockHistoryStateUtil.setStockHistoryStateValue(tempStockHistoryState)
                BottomBarStateUtil.setBottomBarStateValue(
                    bottomBarState.copy(
                        text = Translator.Translate(
                            applicationState.language,
                            AllTexts.CSV_Loaded
                        ) + ": " + portfolio.name + " ==> " + stock.name + " (" + stock.ticker + ")"
                    )
                )
            }else{
                StockHistoryStateUtil.setStockHistoryStateValue(StockHistoryState())
                BottomBarStateUtil.setBottomBarStateValue(
                    bottomBarState.copy(
                        text = Translator.Translate(
                            applicationState.language,
                            AllTexts.No_CSV
                        ) + ": " + portfolio.name + " ==> " + stock.name + " (" + stock.ticker + ")"
                    )
                )
            }
        }){
            Text(Translator.Translate(applicationState.language, AllTexts.Update))
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get1WeekDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("1W")
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get1MonthDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("1M")
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get3MonthDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("3M")
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get6MonthDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("6M")
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get1YearDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("1Y")
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get2YearDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("2Y")
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get5YearDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("5Y")
        }
        Button(onClick = {
            UpdatePeriod(DateUtils.get10YearDateAtStart(), DateUtils.getTodayDateAtEnd())
        }){
            Text("10Y")
        }
    }
}