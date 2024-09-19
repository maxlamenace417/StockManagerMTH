package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun ViewPortfolioTabHeader(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    var portfolio = applicationState.project.portfolios.first{it.name == navigationState.currentPortfolio}
    Row(verticalAlignment = Alignment.CenterVertically) {
        //Return button
        Button(onClick = {
            MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
        }) {
            Text(Translator.Translate(applicationState.language, AllTexts.Return))
        }
        Text(text = portfolio.name, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
    }
}