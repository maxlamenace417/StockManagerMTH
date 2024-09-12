package AppComponents.ApplicationContent.MainZone.ViewProjectTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Components.grayBoxStyle
import Storage.Project
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun ViewProjectTab(modifier: Modifier = Modifier){
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    Column(modifier){
        Row{
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
            }){
                Text(Translator.Translate(applicationState.language, AllTexts.Create_Portfolio))
            }
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            for (i in 0..applicationState.project.portfolios.size - 1) {
                //TODO() Portfolio List
                Row(Modifier.padding(bottom = 5.dp).grayBoxStyle().fillMaxWidth()) {
                    Image(
                        painter = painterResource("img/portfolio.png"),
                        contentDescription = ""
                    )
                    Row{
                        Text(applicationState.project.portfolios[i].name, Modifier.fillMaxWidth(0.9f))
                        Column(Modifier.fillMaxWidth()){
                            //Button to go to portfolio detail
                            Button(onClick = {
                                NavigationStateUtil.setNavigationStateValue(navigationState.copy(currentPortfolio = applicationState.project.portfolios[i].name))
                                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolio))
                                BottomBarStateUtil.setBottomBarStateValue(
                                    bottomBarState.copy(
                                        text = Translator.Translate(
                                            applicationState.language,
                                            AllTexts.Navigating_To_Portfolio_View_Tab
                                        ) + ": "+ applicationState.project.portfolios[i].name
                                    )
                                )
                            }, Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource("img/view.png"),
                                    contentDescription = ""
                                )
                            }
                            Button(onClick = {
                            }, Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource("img/more.png"),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}