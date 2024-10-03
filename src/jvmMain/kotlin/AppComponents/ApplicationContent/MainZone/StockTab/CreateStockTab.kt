package AppComponents.ApplicationContent.MainZone.StockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import AppComponents.Utils.RequiredField
import Storage.Stock
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun CreateStockTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()

    var stockName by remember { mutableStateOf(TextFieldValue("")) }
    var ticker by remember { mutableStateOf(TextFieldValue("")) }
    var bourseDirectURL by remember { mutableStateOf(TextFieldValue("")) }
    var stockPrice by remember { mutableStateOf(TextFieldValue("")) }
    //TODO() Add optionnal currentStock field
    Column(modifier) {
        Row(Modifier.padding(bottom = 5.dp)) {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Stock_Name
                    )
                )
                Row {
                    BasicTextField(
                        stockName,
                        onValueChange = { newStockName ->
                            stockName = newStockName
                        },
                        Modifier.background(Color.White).fillMaxWidth(0.8f).padding(5.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true
                    )
                }
            }
        }
        Row {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Ticker
                    )
                )
                Row {
                    BasicTextField(
                        ticker,
                        onValueChange = { newTicker ->
                            ticker = newTicker
                        },
                        Modifier.background(Color.White).fillMaxWidth(0.8f).padding(5.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true
                    )
                }
            }
        }
        Row {
            Column {
                Text(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Current_Price
                    )
                )
                Row {
                    BasicTextField(
                        stockPrice,
                        onValueChange = { newStockPrice->
                            stockPrice = newStockPrice
                        },
                        Modifier.background(Color.White).fillMaxWidth(0.8f).padding(5.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true
                    )
                }
            }
        }
        Row {
            Column {
                Text(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Bourse_Direct_URL
                    )
                )
                Row {
                    BasicTextField(
                        bourseDirectURL,
                        onValueChange = { newBourseDirectURL ->
                            bourseDirectURL = newBourseDirectURL
                        },
                        Modifier.background(Color.White).fillMaxWidth(0.8f).padding(5.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true
                    )
                }
            }
        }
        Row {
            Button(onClick = {
                if (stockName.text.isNullOrEmpty()) {
                    BottomBarStateUtil.setBottomBarStateValue(
                        bottomBarState.copy(
                            text = Translator.Translate(
                                applicationState.language,
                                AllTexts.Stock_Name_Empty
                            )
                        )
                    )
                } else {
                    if (ticker.text.isNullOrEmpty()) {
                        BottomBarStateUtil.setBottomBarStateValue(
                            bottomBarState.copy(
                                text = Translator.Translate(
                                    applicationState.language,
                                    AllTexts.Ticker_Empty
                                )
                            )
                        )
                    } else {
                        if (applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }.stocks.any { it.ticker == ticker.text && it.name == stockName.text }) {
                            BottomBarStateUtil.setBottomBarStateValue(
                                bottomBarState.copy(
                                    text = Translator.Translate(
                                        applicationState.language,
                                        AllTexts.Stocks_Already_Exists_In_Portofolio
                                    )
                                )
                            )
                        } else {
                            var stockPriceFinal = stockPrice.text.toDoubleOrNull()
                            //TODO() check if stocks not already in portfolio
                            var newApplicationState = applicationState.copy()
                            newApplicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }.stocks.add(
                                Stock(name = stockName.text, ticker = ticker.text, bourseDirectURL = bourseDirectURL.text, currentValue = if(stockPriceFinal!=null){stockPriceFinal}else{0.0})
                            )
                            BottomBarStateUtil.setBottomBarStateValue(
                                bottomBarState.copy(
                                    text = Translator.Translate(
                                        applicationState.language,
                                        AllTexts.Stock_Created
                                    ) + ": " + stockName.text + " (" + ticker.text + ") " + Translator.Translate(
                                        applicationState.language,
                                        AllTexts.`in`
                                    ) + " " + navigationState.currentPortfolio
                                )
                            )
                            MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolio))
                            ApplicationStateUtil.setApplicationStateValue(newApplicationState)
                        }
                    }
                }
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Validate))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolio))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Cancel))
            }
        }
    }
}