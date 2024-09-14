package AppComponents.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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
            Text(Translator.Translate(applicationState.language, AllTexts.Type), Modifier.weight(0.1f))
            Text(Translator.Translate(applicationState.language, AllTexts.Quantity), Modifier.weight(0.1f))
            Text(Translator.Translate(applicationState.language, AllTexts.Unit_Price), Modifier.weight(0.8f))
        }
        Column(Modifier.verticalScroll(rememberScrollState())){
            //TODO() Transaction of the stock view
            for(i in 0..stock.genericTransactionWithInfoList.genericTransactionsWithInformation.size-1){
                Row(Modifier.fillMaxWidth()){
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.type.toString(), Modifier.weight(0.1f))
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.quantity.toString(), Modifier.weight(0.1f))
                    Text(stock.genericTransactionWithInfoList.genericTransactionsWithInformation[i].genericTransaction.unitPrice.toString(), Modifier.weight(0.8f))
                }
            }
        }
    }
}