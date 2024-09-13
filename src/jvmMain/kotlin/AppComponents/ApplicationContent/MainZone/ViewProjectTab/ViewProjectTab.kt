package AppComponents.ApplicationContent.MainZone.ViewProjectTab

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun ViewProjectTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    Column(modifier) {
        Row {
            Button(onClick = {
                //Create a portfolio
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.CreatePortfolio))
                BottomBarStateUtil.setBottomBarStateValue(
                    bottomBarState.copy(
                        text = Translator.Translate(
                            applicationState.language,
                            AllTexts.Navigating_To_Portfolio_Create_Tab
                        )
                    )
                )
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Create_Portfolio))
            }
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            for (i in 0..applicationState.project.portfolios.size - 1) {
                //TODO() Portfolio List
                val interactionSource = remember { MutableInteractionSource() }
                val hoverState = interactionSource.collectIsHoveredAsState()
                var opened by remember { mutableStateOf(false) }
                Column(
                    Modifier.padding(bottom = 5.dp).grayBoxStyle(
                        backgroundColor = if (hoverState.value) {
                            Color.White
                        } else {
                            Color.Gray
                        }
                    ).fillMaxWidth().hoverable(interactionSource).clickable(interactionSource, null, onClick = {
                        opened = !opened
                    })
                ) {
                    Row {
                        Image(
                            painter = painterResource("img/portfolio.png"),
                            contentDescription = ""
                        )
                        Text(applicationState.project.portfolios[i].name)
                        Spacer(Modifier.weight(1f))
                        Row {
                            //Button to go to portfolio detail
                            Button(onClick = {
                                NavigationStateUtil.setNavigationStateValue(navigationState.copy(currentPortfolio = applicationState.project.portfolios[i].name))
                                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolio))
                                BottomBarStateUtil.setBottomBarStateValue(
                                    bottomBarState.copy(
                                        text = Translator.Translate(
                                            applicationState.language,
                                            AllTexts.Navigating_To_Portfolio_View_Tab
                                        ) + ": " + applicationState.project.portfolios[i].name
                                    )
                                )
                            }) {
                                Image(
                                    painter = painterResource("img/view.png"),
                                    contentDescription = ""
                                )
                            }
                            //Button for portfolio edit
                            Button(onClick = {
                                NavigationStateUtil.setNavigationStateValue(navigationState.copy(currentPortfolio = applicationState.project.portfolios[i].name))
                                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.EditPortfolio))
                                BottomBarStateUtil.setBottomBarStateValue(
                                    bottomBarState.copy(
                                        text = Translator.Translate(
                                            applicationState.language,
                                            AllTexts.Navigating_To_Portfolio_Edit_Tab
                                        ) + ": " + applicationState.project.portfolios[i].name
                                    )
                                )
                            }) {
                                Image(
                                    painter = painterResource("img/modify.png"),
                                    contentDescription = ""
                                )
                            }
                            //Button for portfolio delete
                            Button(onClick = {
                                //TODO() suppression portefeuille
                            }) {
                                Image(
                                    painter = painterResource("img/delete.png"),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                    if (opened && applicationState.project.portfolios[i].stocks.size > 0) {
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                "↑ ↑ ↑ ↑ ↑",
                                Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                    if (!opened && applicationState.project.portfolios[i].stocks.size > 0) {
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                "↓ ↓ ↓ ↓ ↓",
                                Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                    if (opened && applicationState.project.portfolios[i].stocks.size > 0) {
                        for (j in 0..applicationState.project.portfolios[i].stocks.size - 1) {
                            //TODO() Stock light view
                            Row {
                                Text(applicationState.project.portfolios[i].stocks[j].name + " (" + applicationState.project.portfolios[i].stocks[j].ticker + ")")
                            }
                        }
                    }
                }
            }
        }
    }
}