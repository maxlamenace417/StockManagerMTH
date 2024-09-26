package AppComponents.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationContent.MainZone.ViewStockTab.StockHistoryStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat

@Composable
@Preview
fun ViewStockTabHistoryGraph(modifier: Modifier = Modifier) {
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
        if(stockHistoryState.histories.size>1000){
            Text(Translator.Translate(applicationState.language, AllTexts.HorizontalScrollTip), fontSize = 12.sp)
        }
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), verticalAlignment = Alignment.Bottom){
            var maxCloseValue = 0.0
            var minCloseValue = Double.MAX_VALUE
            for(i in 0..stockHistoryState.histories.size-1){
                if(stockHistoryState.histories[i].close>maxCloseValue){
                    maxCloseValue = stockHistoryState.histories[i].close
                }
                if(stockHistoryState.histories[i].close<minCloseValue){
                    minCloseValue = stockHistoryState.histories[i].close
                }
            }
            if(maxCloseValue>0.0) {
                for (i in 0..stockHistoryState.histories.size - 1) {
                    val interactionSource = remember{ MutableInteractionSource() }
                    val hoverState = interactionSource.collectIsHoveredAsState()
                    if(stockHistoryState.histories.size>1000){
                        Column(Modifier.fillMaxHeight(((stockHistoryState.histories[i].close-minCloseValue+1) / (maxCloseValue-minCloseValue+1)).toFloat()).width(1.dp).background(color=Color(if(hoverState.value){255}else{0},0,if(hoverState.value){0}else{i*255/stockHistoryState.histories.size})).hoverable(interactionSource)) {

                        }
                    }else{
                        Column(Modifier.fillMaxHeight(((stockHistoryState.histories[i].close-minCloseValue+1) / (maxCloseValue-minCloseValue+1)).toFloat()).weight(0.1f).background(color=Color(if(hoverState.value){255}else{0},0,if(hoverState.value){0}else{i*255/stockHistoryState.histories.size})).hoverable(interactionSource)) {

                        }
                    }
                    if(hoverState.value){
                        BottomBarStateUtil.setBottomBarStateValue(
                            bottomBarState.copy(
                                text = SimpleDateFormat("d MMMM yyyy").format(stockHistoryState.histories[i].date) + " ==> " +stockHistoryState.histories[i].close.toString()
                            )
                        )
                    }
                }
            }
            /*Column(Modifier.fillMaxHeight().weight(0.01f).background(color = Color.Red)){

            }
            Column(Modifier.fillMaxHeight(0.8f).weight(0.01f).background(color = Color.Green)){

            }
            Column(Modifier.fillMaxHeight(0.2f).weight(0.01f).background(color = Color.Red)){

            }
            Column(Modifier.fillMaxHeight(0.5f).weight(0.01f).background(color = Color.Green)){

            }
            Column(Modifier.fillMaxHeight(0.6f).weight(0.01f).background(color = Color.Red)){

            }*/
        }
    }
}