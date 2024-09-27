package AppComponents.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Components.grayBoxStyle
import Storage.GenericTransactionType
import Translation.AllTexts
import Translation.Translator
import Utils.DeepCopy
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode
import java.text.SimpleDateFormat

@Composable
@Preview
fun ViewStockTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()

    var portfolio = applicationState.project.portfolios.first{it.name == navigationState.currentPortfolio}
    var stock = portfolio.stocks.first{it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker}
    Column(modifier) {
        ViewStockTabHeader()
        Row{
            Button(onClick = {
                //Create a transaction
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.CreateTransaction))
                BottomBarStateUtil.setBottomBarStateValue(
                    bottomBarState.copy(
                        text = Translator.Translate(
                            applicationState.language,
                            AllTexts.Navigating_To_Transaction_Create_Tab
                        )
                    )
                )
            }){
                Text(Translator.Translate(applicationState.language, AllTexts.Create_Transaction))
            }
        }
        Row(Modifier.fillMaxWidth()){
            Text(Translator.Translate(applicationState.language, AllTexts.Date), Modifier.weight(0.15f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Type), Modifier.weight(0.08f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Quantity), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Unit_Price), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Total_Taxes), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Total_Number_Of_Stocks), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.PRU), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.PRU_Real), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Earnings), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.weight(0.05f))
        }
        Column(Modifier.verticalScroll(rememberScrollState())){
            //Transaction of the stock view
            for(i in 0..stock.genericTransactionWithInfoList.genericTransactionsWithInformation.size-1){
                val interactionSource = remember{ MutableInteractionSource() }
                val hoverState = interactionSource.collectIsHoveredAsState()
                Row(Modifier.fillMaxWidth().background(color = if(hoverState.value) {
                    Color.White} else{
                    Color.Gray}).fillMaxWidth().hoverable(interactionSource), verticalAlignment = Alignment.CenterVertically){
                    Text(SimpleDateFormat("d MMMM yyyy").format(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.date.time), Modifier.weight(0.15f))
                    var color = if(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.type==GenericTransactionType.Buy){
                        Color.Green
                    }else if(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.type==GenericTransactionType.Sell){
                        Color.Red
                    }else{
                        Color.Black
                    }
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.type.toString(), Modifier.weight(0.08f), color = color)
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.quantity.toString(), Modifier.weight(0.1f))
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.unitPrice.toString(), Modifier.weight(0.1f))
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.taxPrice.toString(), Modifier.weight(0.1f))
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransactionInformation.currentTotalQuantity.toString(), Modifier.weight(0.1f))
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransactionInformation.currentPRU.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString(), Modifier.weight(0.1f))
                    Text(if(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransactionInformation.currentPRUToSellToBeEvenWithoutTax.isInfinite()){"0"}else{stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransactionInformation.currentPRUToSellToBeEvenWithoutTax.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString()}, Modifier.weight(0.1f))
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransactionInformation.currentEarnings.toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString(), Modifier.weight(0.1f))
                    //TODO() Edit transaction button
                    Button(onClick = {
                        var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                        applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.first{it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker}.genericTransactionWithInfoList.genericTransactionsWithInformation.removeAt(i)
                        applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.first{it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker}.genericTransactionWithInfoList.RecalculateAll()
                        ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
                    }, Modifier.weight(0.05f)){
                        Image(
                            painter = painterResource("img/delete.png"),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}