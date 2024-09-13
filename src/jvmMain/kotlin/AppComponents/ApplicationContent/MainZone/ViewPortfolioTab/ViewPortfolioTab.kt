package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Components.grayBoxStyle
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun ViewPortfolioTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    var portfolio = applicationState.project.portfolios.first{it.name == navigationState.currentPortfolio}
    Column(modifier) {
        Row{
            Button(onClick = {
                //Create a portfolio
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
        }
        Text(text = portfolio.name, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        Column(Modifier.verticalScroll(rememberScrollState())) {
            //TODO() Stock of the portfolio view
            for (i in 0..portfolio.stocks.size - 1){
                val interactionSource = remember{ MutableInteractionSource() }
                val hoverState = interactionSource.collectIsHoveredAsState()
                var opened by remember { mutableStateOf(false) }
                Column(Modifier.padding(bottom = 5.dp).grayBoxStyle(backgroundColor = if(hoverState.value) {
                    Color.White} else{
                    Color.Gray}).fillMaxWidth().hoverable(interactionSource).clickable(interactionSource, null, onClick={
                    opened = !opened
                })) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")"
                        )
                        Spacer(Modifier.weight(1f))
                        Row {
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
                            //Button for stock delete
                            Button(onClick = {
                                //TODO() suppression action
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