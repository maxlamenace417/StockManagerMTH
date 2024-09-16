package AppComponents.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Storage.GenericTransactionType
import Translation.AllTexts
import Translation.Translator
import Utils.DeepCopy
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
            //Return button
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolio))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Return))
            }
        }
        Text(text = portfolio.name+ ": " + stock.name + " ("+stock.ticker+")", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        Row(Modifier.fillMaxWidth()){
            Text(Translator.Translate(applicationState.language, AllTexts.Date), Modifier.weight(0.12f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Type), Modifier.weight(0.08f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Quantity), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Unit_Price), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.Total_Taxes), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Text(Translator.Translate(applicationState.language, AllTexts.PRU), Modifier.weight(0.1f), fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.weight(0.1f))
        }
        Column(Modifier.verticalScroll(rememberScrollState())){
            //TODO() Transaction of the stock view
            for(i in 0..stock.genericTransactionWithInfoList.genericTransactionsWithInformation.size-1){
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Text(SimpleDateFormat("d MMMM yyyy").format(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.date.time), Modifier.weight(0.12f))
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
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransactionInformation.currentPRU.toString(), Modifier.weight(0.1f))
                    Button(onClick = {
                        //TODO() trolled par la deep copy a créer
                        var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                        applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.first{it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker}.genericTransactionWithInfoList.genericTransactionsWithInformation.removeAt(i)
                        applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.first{it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker}.genericTransactionWithInfoList.RecalculateAll()
                        ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
                    }, Modifier.weight(0.1f)){
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