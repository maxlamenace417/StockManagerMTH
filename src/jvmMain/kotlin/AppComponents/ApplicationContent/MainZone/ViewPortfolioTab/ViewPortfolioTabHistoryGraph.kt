package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationContent.MainZone.ViewPortfolioTab.PortfolioHistoryStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import Utils.ColorUtils
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode
import java.text.SimpleDateFormat

@Composable
@Preview
fun ViewPortfolioTabHistoryGraph(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val portfolioHistoryState = PortfolioHistoryStateUtil.getPortfolioHistoryStateValue()
    Column(modifier.fillMaxWidth()) {
        ViewPortfolioTabHeader()
        ViewPortfolioTabHistoryBar()
        var MaxSizeAndIndex = PortfolioHistoryStateUtil.getMaxSizeAndIndex()
        if(MaxSizeAndIndex.second>=0) {
            Text(Translator.Translate(applicationState.language, AllTexts.VerticalScrollTip), fontSize = 12.sp)
            if (MaxSizeAndIndex.first > 1000) {
                Text(Translator.Translate(applicationState.language, AllTexts.HorizontalScrollTip), fontSize = 12.sp)
            }
            var GraphZoneHeight = 500
            Row(Modifier.fillMaxWidth()) {
                var colors = ColorUtils.getListOfColors(portfolioHistoryState.histories.size, 60)
                Column(modifier.weight(0.2f).verticalScroll(rememberScrollState())) {
                    for (i in 0..portfolioHistoryState.histories.size - 1) {
                        Column {
                            Text(portfolioHistoryState.histories[i].stockName)
                            Row(Modifier.fillMaxWidth().background(colors[i])){
                                Text(" ")
                            }
                        }
                    }
                }
                Row(
                    modifier.weight(0.8f).horizontalScroll(rememberScrollState())
                        .verticalScroll(rememberScrollState()), verticalAlignment = Alignment.Bottom
                ) {
                    var maxTotalCloseValue = 0.0
                    var minTotalCloseValue = Double.MAX_VALUE
                    for (i in 0..portfolioHistoryState.histories[MaxSizeAndIndex.second].history.histories.size - 1) {
                        var date = portfolioHistoryState.histories[MaxSizeAndIndex.second].history.histories[i].date
                        var totalCloseValueTemp = 0.0
                        for (j in 0..portfolioHistoryState.histories.size - 1) {
                            var historyBourseDirect =
                                portfolioHistoryState.histories[j].history.histories.find { x -> x.date.compareTo(date) == 0 }
                            if (historyBourseDirect != null) {
                                totalCloseValueTemp += historyBourseDirect.close
                            }
                        }
                        if (totalCloseValueTemp > maxTotalCloseValue) {
                            maxTotalCloseValue = totalCloseValueTemp
                        }
                        if (totalCloseValueTemp < minTotalCloseValue) {
                            minTotalCloseValue = totalCloseValueTemp
                        }
                    }
                    if (maxTotalCloseValue > 0.0) {
                        for (i in 0..portfolioHistoryState.histories[MaxSizeAndIndex.second].history.histories.size - 1) {
                            var date = portfolioHistoryState.histories[MaxSizeAndIndex.second].history.histories[i].date
                            var currentTotalCloseValue = 0.0
                            for (j in 0..portfolioHistoryState.histories.size - 1) {
                                var historyBourseDirect =
                                    portfolioHistoryState.histories[j].history.histories.find { x ->
                                        x.date.compareTo(
                                            date
                                        ) == 0
                                    }
                                if (historyBourseDirect != null) {
                                    currentTotalCloseValue += historyBourseDirect.close
                                }
                            }
                            if (MaxSizeAndIndex.first > 1000) {
                                Column(
                                    Modifier.fillMaxHeight()
                                        .width(1.dp), verticalArrangement = Arrangement.Bottom
                                ) {
                                    for (j in 0..portfolioHistoryState.histories.size - 1) {
                                        var historyBourseDirect =
                                            portfolioHistoryState.histories[j].history.histories.find { x ->
                                                x.date.compareTo(date) == 0
                                            }
                                        if (historyBourseDirect != null) {
                                            val interactionSource = remember { MutableInteractionSource() }
                                            val hoverState = interactionSource.collectIsHoveredAsState()
                                            var percentHeight =
                                                (historyBourseDirect.close + 1) / (maxTotalCloseValue + 1)
                                            Column(
                                                Modifier.height((percentHeight * GraphZoneHeight).dp).width(1.dp)
                                                    .background(
                                                        color = if (hoverState.value) {
                                                            Color(255, 255, 255)
                                                        } else {
                                                            colors[j]
                                                        }
                                                    ).hoverable(interactionSource)
                                            ) {

                                            }
                                            if(hoverState.value){
                                                BottomBarStateUtil.setBottomBarStateValue(
                                                    bottomBarState.copy(
                                                        text = SimpleDateFormat("d MMMM yyyy").format(date) + " "+portfolioHistoryState.histories[j].stockName+" ("+portfolioHistoryState.histories[j].stockTicker+")"+ " ==> " + historyBourseDirect.close.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString() + " " +((historyBourseDirect.close/currentTotalCloseValue)*100).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString()+ "% ["+currentTotalCloseValue.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString()+"]"
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            } else {
                                Column(
                                    Modifier.fillMaxHeight()
                                        .weight(0.1f), verticalArrangement = Arrangement.Bottom
                                ) {
                                    for (j in 0..portfolioHistoryState.histories.size - 1) {
                                        var historyBourseDirect =
                                            portfolioHistoryState.histories[j].history.histories.find { x ->
                                                x.date.compareTo(date) == 0
                                            }
                                        if (historyBourseDirect != null) {
                                            val interactionSource = remember { MutableInteractionSource() }
                                            val hoverState = interactionSource.collectIsHoveredAsState()
                                            var percentHeight =
                                                (historyBourseDirect.close + 1) / (maxTotalCloseValue + 1)
                                            Row(
                                                Modifier.height((percentHeight * GraphZoneHeight).dp).fillMaxWidth(1f)
                                                    .background(
                                                        color = if (hoverState.value) {
                                                            Color(255, 255, 255)
                                                        } else {
                                                            colors[j]
                                                        }
                                                    ).hoverable(interactionSource)
                                            ) {

                                            }
                                            if(hoverState.value){
                                                BottomBarStateUtil.setBottomBarStateValue(
                                                    bottomBarState.copy(
                                                        text = SimpleDateFormat("d MMMM yyyy").format(date) + " "+portfolioHistoryState.histories[j].stockName+" ("+portfolioHistoryState.histories[j].stockTicker+")"+ " ==> " + historyBourseDirect.close.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString() + " " +((historyBourseDirect.close/currentTotalCloseValue)*100).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString()+ "% ["+currentTotalCloseValue.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString()+"]"
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}