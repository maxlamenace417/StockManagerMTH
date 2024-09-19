package AppComponents.ApplicationContent.MainZone.ViewStockTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun ViewStockTabHeader(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()

    var portfolio = applicationState.project.portfolios.first{it.name == navigationState.currentPortfolio}
    var stock = portfolio.stocks.first{it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker}
    Row(verticalAlignment = Alignment.CenterVertically) {
        //Return button
        Button(onClick = {
            MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolio))
        }) {
            Text(Translator.Translate(applicationState.language, AllTexts.Return))
        }
        Text(
            text = portfolio.name + ": " + stock.name + " (" + stock.ticker + ")",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )
    }
}