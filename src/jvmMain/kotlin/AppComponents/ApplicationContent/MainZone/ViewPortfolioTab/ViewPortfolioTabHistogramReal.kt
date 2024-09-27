package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.math.RoundingMode

@Composable
@Preview
fun ViewPortfolioTabHistogramReal(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    var portfolio = applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }
    Column(modifier.fillMaxWidth()) {
        ViewPortfolioTabHeader()
        var maxValue = 0.0 //for normalization
        for(i in 0..portfolio.stocks.size-1){
            var currentTotalInvestedValue = portfolio.stocks[i].getCurrentTotalInvestedValueReal()
            if(currentTotalInvestedValue>maxValue){
                maxValue = currentTotalInvestedValue
            }
            var currentTotalValue = portfolio.stocks[i].getCurrentTotalValue()
            if(currentTotalValue>maxValue){
                maxValue = currentTotalValue
            }
        }
        for(i in 0..portfolio.stocks.size-1) {
            val interactionSource = remember{ MutableInteractionSource() }
            val hoverState = interactionSource.collectIsHoveredAsState()
            Row(
                Modifier.fillMaxWidth().border(if(hoverState.value){
                BorderStroke(1.dp, Color.Blue)
            }else{
                BorderStroke(0.dp, Color.Transparent)
            }).hoverable(interactionSource)) {
                var currentStockTotalInvestedValue = portfolio.stocks[i].getCurrentTotalInvestedValueReal()
                var currentStockTotalValue = portfolio.stocks[i].getCurrentTotalValue()
                var currentPortfolioTotalInvestedValue = portfolio.getCurrentTotalInvestedValueReal()
                var currentPortfolioTotalValue = portfolio.getCurrentTotalValue()
                Row(Modifier.weight(0.4f).background(color = Color.White)) {
                    Text(
                        modifier = Modifier.weight(0.2f)
                            .background(color = Color.White), text = portfolio.stocks[i].name, fontWeight = FontWeight.Bold
                    )
                    var currentPercentOfPortfolio = (currentStockTotalValue/currentPortfolioTotalValue*100).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                    var originalPercentOfPortfolio = (currentStockTotalInvestedValue/currentPortfolioTotalInvestedValue*100).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                    var difference = (currentPercentOfPortfolio - originalPercentOfPortfolio).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                    var sign = if(difference>=0.0){"+"}else{""}
                    Text(
                        modifier = Modifier.weight(0.1f).background(color = Color.White), text = currentPercentOfPortfolio.toString() + "%"
                    )
                    Text(
                        modifier = Modifier.weight(0.25f).background(color = Color.White), text = "("+originalPercentOfPortfolio+sign+difference + "%)", color=if(difference>=0.0){
                            Color.Green}else{
                            Color.Red}
                    )
                }
                if (portfolio.stocks[i].genericTransactionWithInfoList.genericTransactionsWithInformation.size > 0 && portfolio.stocks[i].getCurrentTotalQuantity() > 0) {
                    if (currentStockTotalInvestedValue > currentStockTotalValue) {
                        Row(Modifier.weight(0.6f)) {
                            Text(
                                modifier = Modifier.weight((currentStockTotalValue / maxValue).toFloat())
                                    .background(color = Color.LightGray), text = ""
                            )
                            Text(
                                modifier = Modifier.weight(((currentStockTotalInvestedValue - currentStockTotalValue) / maxValue).toFloat())
                                    .background(color = Color.Red), text = ""
                            )
                            if (maxValue - currentStockTotalInvestedValue > 0) {
                                Spacer(Modifier.weight(((maxValue - currentStockTotalInvestedValue) / maxValue).toFloat()))
                            }
                        }
                    } else if (currentStockTotalInvestedValue == currentStockTotalValue) {
                        Row(Modifier.weight(0.6f)) {
                            Text(
                                modifier = Modifier.weight((currentStockTotalValue / maxValue).toFloat())
                                    .background(color = Color.LightGray), text = ""
                            )
                            if (maxValue - currentStockTotalInvestedValue > 0) {
                                Spacer(Modifier.weight(((maxValue - currentStockTotalValue) / maxValue).toFloat()))
                            }
                        }
                    } else {
                        Row(Modifier.weight(0.6f)) {
                            Text(
                                modifier = Modifier.weight((currentStockTotalInvestedValue / maxValue).toFloat())
                                    .background(color = Color.LightGray), text = ""
                            )
                            Text(
                                modifier = Modifier.weight(((currentStockTotalValue - currentStockTotalInvestedValue) / maxValue).toFloat())
                                    .background(color = Color.Green), text = ""
                            )
                            if (maxValue - currentStockTotalValue > 0) {
                                Spacer(Modifier.weight(((maxValue - currentStockTotalValue) / maxValue).toFloat()))
                            }
                        }
                    }
                } else {
                    Row(Modifier.weight(0.6f).background(color= Color.Gray)) {
                        Text(
                            modifier = Modifier.fillMaxWidth().background(color = Color.Gray),
                            text = "(" + Translator.Translate(
                                applicationState.language,
                                AllTexts.No_Stocks_Currently_In_Portfolio
                            ) + ")"
                        )
                    }
                }
            }
        }
    }
}