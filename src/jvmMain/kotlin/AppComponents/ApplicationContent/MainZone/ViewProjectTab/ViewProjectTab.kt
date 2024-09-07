package AppComponents.ApplicationContent.MainZone.ViewProjectTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@Preview
fun ViewProjectTab(modifier: Modifier = Modifier){
    var applicationState = ApplicationStateUtil.getApplicationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    Column{
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
        for(i in 0..applicationState.project.portfolios.size-1){
            //TODO() Portfolio List
            Row{
                Text(applicationState.project.portfolios[i].name)
            }
        }
    }
}