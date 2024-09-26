package AppComponents.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
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
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

@Composable
@Preview
fun ViewStockTabHistory(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val stockHistoryState = StockHistoryStateUtil.getStockHistoryStateValue()


    var portfolio = applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }
    var stock =
        portfolio.stocks.first { it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker }

    Column(modifier) {
        ViewStockTabHeader()
        ViewStockTabHistoryBar()
        Row(Modifier.fillMaxWidth()){
            Text(Translator.Translate(applicationState.language, AllTexts.Date), Modifier.weight(0.15f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Open), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.High), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Low), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Close), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Volume), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
        }
        Column(Modifier.verticalScroll(rememberScrollState())){
            for(i in 0..stockHistoryState.histories.size-1){
                val interactionSource = remember{ MutableInteractionSource() }
                val hoverState = interactionSource.collectIsHoveredAsState()
                Row(Modifier.fillMaxWidth().background(color = if(hoverState.value) {
                    Color.White} else{
                    Color.Gray}).fillMaxWidth().hoverable(interactionSource), verticalAlignment = Alignment.CenterVertically) {
                    Text(SimpleDateFormat("d MMMM yyyy").format(stockHistoryState.histories[i].date), Modifier.weight(0.15f))
                    Text(stockHistoryState.histories[i].open.toString(), Modifier.weight(0.1f))
                    Text(stockHistoryState.histories[i].high.toString(), Modifier.weight(0.1f))
                    Text(stockHistoryState.histories[i].low.toString(), Modifier.weight(0.1f))
                    Text(stockHistoryState.histories[i].close.toString(), Modifier.weight(0.1f))
                    Text(stockHistoryState.histories[i].volume.toString(), Modifier.weight(0.1f))
                }
            }
        }
    }
}