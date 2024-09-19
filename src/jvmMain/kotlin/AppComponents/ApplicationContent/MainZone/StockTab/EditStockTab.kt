package AppComponents.ApplicationContent.MainZone.StockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import AppComponents.Utils.RequiredField
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
fun EditStockTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()

    var stockName by remember { mutableStateOf(TextFieldValue(navigationState.currentStockName)) }
    var ticker by remember { mutableStateOf(TextFieldValue(navigationState.currentStockTicker)) }
    val oldBourseDirectUrl = applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }.stocks.first { it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker }.bourseDirectURL
    var bourseDirectURL by remember { mutableStateOf(TextFieldValue(oldBourseDirectUrl)) }
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
                        if (applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }.stocks.any { it.ticker == ticker.text && it.name == stockName.text } && oldBourseDirectUrl==bourseDirectURL.text) {
                            BottomBarStateUtil.setBottomBarStateValue(
                                bottomBarState.copy(
                                    text = Translator.Translate(
                                        applicationState.language,
                                        AllTexts.Stocks_Already_Exists_In_Portofolio
                                    )
                                )
                            )
                        } else {
                            //TODO() check if stocks not already in portfolio
                            var newApplicationState = applicationState.copy()
                            var stock = newApplicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }.stocks.first { it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker }
                            stock.name = stockName.text
                            stock.ticker = ticker.text
                            stock.bourseDirectURL = bourseDirectURL.text
                            BottomBarStateUtil.setBottomBarStateValue(
                                bottomBarState.copy(
                                    text = Translator.Translate(
                                        applicationState.language,
                                        AllTexts.Stock_Edited
                                    ) + ": " + navigationState.currentStockName + " (" + navigationState.currentStockTicker + ") " + " ==> " + stockName.text + " (" + ticker.text + ") " + Translator.Translate(
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