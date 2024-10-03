package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import AppComponents.Utils.ConfirmationBox
import Components.grayBoxStyle
import Translation.AllTexts
import Translation.Translator
import Utils.BourseDirectParser
import Utils.DeepCopy
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.math.RoundingMode

@Composable
@Preview
fun ViewPortfolioTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    var portfolio = applicationState.project.portfolios.first{it.name == navigationState.currentPortfolio}
    Column(modifier) {
        ViewPortfolioTabHeader()
        Row(verticalAlignment = Alignment.CenterVertically){
            Button(onClick = {
                //Create a stock button
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.CreateStock))
                BottomBarStateUtil.setBottomBarStateValue(
                    bottomBarState.copy(
                        text = Translator.Translate(
                            applicationState.language,
                            AllTexts.Navigating_To_Stock_Create_Tab
                        )
                    )
                )
            }){
                Text(Translator.Translate(applicationState.language, AllTexts.Create_Stock))
            }
            Button(onClick = {
                //Sort by price button ascending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortBy { it.getCurrentTotalValue() }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/price_sort_asc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                //Sort by price button descending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortByDescending { it.getCurrentTotalValue() }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/price_sort_desc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                //Sort by alphabetical button ascending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortBy { it.name }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/alphabet_sort_asc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                //Sort by alphabetical button descending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortByDescending { it.name }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/alphabet_sort_desc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                //Sort by evolution button ascending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortBy { it.getCurrentEvolution() }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/evolution_sort_desc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                //Sort by evolution button descending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortByDescending { it.getCurrentEvolution() }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/evolution_sort_asc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                //Sort by evolution real button ascending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortBy { it.getCurrentEvolutionReal() }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/evolution_sort_realPRU_desc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                //Sort by evolution real button descending
                var applicationStateTemp = DeepCopy.DeepCopy(applicationState)
                applicationStateTemp.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.sortByDescending { it.getCurrentEvolutionReal() }
                ApplicationStateUtil.setApplicationStateValue(applicationStateTemp)
            }){
                Image(
                    painter = painterResource("img/evolution_sort_realPRU_asc.png"),
                    contentDescription = ""
                )
            }
            Button(onClick = {
                for(i in 0..portfolio.stocks.size-1) {
                    if (portfolio.stocks[i].bourseDirectURL.isNullOrEmpty()) {
                    } else {
                        var currentValue =
                            BourseDirectParser.GetStockPriceFromBourseDirectURL(portfolio.stocks[i].bourseDirectURL)
                        var newApplicationState = DeepCopy.DeepCopy(ApplicationStateUtil.getApplicationStateValue())
                        var stock =
                            newApplicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }.stocks[i]
                        stock.currentValue = currentValue
                        ApplicationStateUtil.setApplicationStateValue(newApplicationState)
                    }
                    if(i<portfolio.stocks.size-1) {
                        Thread.sleep((500..1500).random().toLong())
                    }
                }
                BottomBarStateUtil.setBottomBarStateValue(
                    bottomBarState.copy(
                        text = Translator.Translate(
                            applicationState.language,
                            AllTexts.Stock_Price_Refreshed
                        )
                    )
                )
            }) {
                Image(
                    painter = painterResource("img/refresh.png"),
                    contentDescription = ""
                )
            }
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            //Stock of the portfolio view
            for (i in 0..portfolio.stocks.size - 1){
                val interactionSource = remember{ MutableInteractionSource() }
                val hoverState = interactionSource.collectIsHoveredAsState()
                var opened by remember { mutableStateOf(false) }
                Column(Modifier.padding(bottom = 5.dp).grayBoxStyle(backgroundColor = if(hoverState.value) {
                    Color.White} else{
                    Color(166,166,166)}).fillMaxWidth().hoverable(interactionSource).clickable(interactionSource, null, onClick={
                    opened = !opened
                })) {
                    Row(Modifier.fillMaxWidth()) {
                        Column{
                            var currentTotalValue =
                                portfolio.stocks[i].getCurrentTotalValue().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                            @Composable
                            fun CalculatePercentAndDifference(totalInvestedValue:Double, bold:Boolean){
                                var color = if (totalInvestedValue > currentTotalValue) {
                                    Color.Red
                                } else {
                                    Color.Green
                                }
                                var difference = (currentTotalValue - totalInvestedValue).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                                var differencePercent = if(totalInvestedValue!=0.0){(((currentTotalValue / totalInvestedValue)-1)*100).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()}else{0.0}
                                var sign = if(difference>0){"+"}else{""}
                                Text(
                                    text = currentTotalValue.toString() + " € ("+sign+difference+" € / "+sign+differencePercent+" %)",
                                    color = color,
                                    modifier = Modifier.padding(start=5.dp),
                                    fontWeight = if(bold){FontWeight.Bold}else{FontWeight.Normal
                                    },
                                )
                            }
                            Text(
                                text = portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ") "+portfolio.stocks[i].currentValue.toString()+ " (x "+portfolio.stocks[i].getCurrentTotalQuantity()+")",
                                fontWeight = FontWeight.ExtraBold,
                            )
                            Row {
                                Text(Translator.Translate(applicationState.language, AllTexts.PRU)+ " = " +portfolio.stocks[i].getCurrentPRU().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString())
                                var currentTotalInvestedValue =
                                    portfolio.stocks[i].getCurrentTotalInvestedValue().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                                CalculatePercentAndDifference(currentTotalInvestedValue, true)
                            }
                            Row {
                                Text(Translator.Translate(applicationState.language, AllTexts.PRU_Real)+ " = " +portfolio.stocks[i].getCurrentPRUToSellToBeEvenWithoutTax().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble().toString())
                                var currentTotalInvestedValueReal =
                                    portfolio.stocks[i].getCurrentTotalInvestedValueReal().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                                CalculatePercentAndDifference(currentTotalInvestedValueReal, false)
                            }
                        }
                        Spacer(Modifier.weight(1f))
                        Row {
                            //Button for stock price refresh
                            //TODO() maybe conditionning button display
                            Button(onClick = {
                                if(portfolio.stocks[i].bourseDirectURL.isNullOrEmpty()){
                                    BottomBarStateUtil.setBottomBarStateValue(
                                        bottomBarState.copy(
                                            text = Translator.Translate(
                                                applicationState.language,
                                                AllTexts.Bourse_Direct_URL_Not_Set
                                            ) + ": " + portfolio.name + " ==> " + portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")"
                                        )
                                    )
                                }else{
                                    var currentValue = BourseDirectParser.GetStockPriceFromBourseDirectURL(portfolio.stocks[i].bourseDirectURL)
                                    var newApplicationState = DeepCopy.DeepCopy(applicationState)
                                    var stock = newApplicationState.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks[i]
                                    stock.currentValue = currentValue
                                    ApplicationStateUtil.setApplicationStateValue(newApplicationState)
                                    BottomBarStateUtil.setBottomBarStateValue(
                                        bottomBarState.copy(
                                            text = Translator.Translate(
                                                applicationState.language,
                                                AllTexts.Stock_Price_Refreshed
                                            ) + ": " + portfolio.name + " ==> " + portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ") = "+currentValue
                                        )
                                    )
                                }
                            }) {
                                Image(
                                    painter = painterResource("img/refresh.png"),
                                    contentDescription = ""
                                )
                            }
                            //Button to go to stock detail
                            Button(onClick = {
                                NavigationStateUtil.setNavigationStateValue(navigationState.copy(currentStockTicker = portfolio.stocks[i].ticker, currentStockName = portfolio.stocks[i].name))
                                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewStock))
                                BottomBarStateUtil.setBottomBarStateValue(
                                    bottomBarState.copy(
                                        text = Translator.Translate(
                                            applicationState.language,
                                            AllTexts.Navigating_To_Stock_View_Tab
                                        ) + ": " + portfolio.name + " ==> " + portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")"
                                    )
                                )
                            }) {
                                Image(
                                    painter = painterResource("img/view.png"),
                                    contentDescription = ""
                                )
                            }
                            //Button for stock edit
                            Button(onClick = {
                                NavigationStateUtil.setNavigationStateValue(navigationState.copy(currentStockTicker = portfolio.stocks[i].ticker, currentStockName = portfolio.stocks[i].name))
                                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.EditStock))
                                BottomBarStateUtil.setBottomBarStateValue(
                                    bottomBarState.copy(
                                        text = Translator.Translate(
                                            applicationState.language,
                                            AllTexts.Navigating_To_Stock_Edit_Tab
                                        ) + ": " + portfolio.name + " ==> " + portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")"
                                    )
                                )
                            }) {
                                Image(
                                    painter = painterResource("img/modify.png"),
                                    contentDescription = ""
                                )
                            }
                            val openAlertDialog = remember { mutableStateOf(false) }
                            if(openAlertDialog.value) {
                                ConfirmationBox(
                                    onDismissRequest = { openAlertDialog.value = false },
                                    onConfirmation = {
                                        BottomBarStateUtil.setBottomBarStateValue(
                                            bottomBarState.copy(
                                                text = Translator.Translate(
                                                    applicationState.language,
                                                    AllTexts.Stock_Deleted
                                                ) + ": " + portfolio.name + " ==> " + portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")"
                                            )
                                        )
                                        applicationState.project.portfolios.first{it.name == navigationState.currentPortfolio}.stocks.removeAt(i)
                                        openAlertDialog.value = false
                                    },
                                    dialogTitle = Translator.Translate(
                                        applicationState.language,
                                        AllTexts.Stock_Delete
                                    )+ ": " + portfolio.name + " ==> " + portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")",
                                    dialogText = Translator.Translate(
                                        applicationState.language,
                                        AllTexts.Are_You_Sure
                                    ),
                                )
                            }
                            //Button for stock delete
                            Button(onClick = {
                                openAlertDialog.value = true
                            }) {
                                Image(
                                    painter = painterResource("img/delete.png"),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                    //TODO() plié déplié action transaction history
                }
            }
        }
    }
}