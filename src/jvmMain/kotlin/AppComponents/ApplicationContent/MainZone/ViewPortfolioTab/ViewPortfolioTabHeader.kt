package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode

@Composable
@Preview
fun ViewPortfolioTabHeader(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    var portfolio = applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }
    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            //Return button
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Return))
            }
            Text(text = portfolio.name, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp,modifier = Modifier.padding(start=5.dp))
            var currentTotalInvestedValue =
                portfolio.getCurrentTotalInvestedValue().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
            var currentTotalValue =
                portfolio.getCurrentTotalValue().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
            var color = if (currentTotalInvestedValue > currentTotalValue) {
                Color.Red
            } else {
                Color.Green
            }
            var difference = (currentTotalValue - currentTotalInvestedValue).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
            var differencePercent = if(currentTotalInvestedValue!=0.0){(((currentTotalValue / currentTotalInvestedValue)-1)*100).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()}else{0.0}
            var sign = if(difference>0){"+"}else{""}
            var currentTotalInvestedValueReal =
                portfolio.getCurrentTotalInvestedValueReal().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
            var colorReal = if (currentTotalInvestedValueReal > currentTotalValue) {
                Color.Red
            } else {
                Color.Green
            }
            var differenceReal = (currentTotalValue - currentTotalInvestedValueReal).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
            var differencePercentReal = if(currentTotalInvestedValueReal!=0.0){(((currentTotalValue / currentTotalInvestedValueReal)-1)*100).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()}else{0.0}
            var signReal = if(differenceReal>0){"+"}else{""}
            Column {
                Text(
                    text = currentTotalValue.toString() + " € (" + sign + difference + " € / " + sign + differencePercent + " %)",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = color,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = currentTotalValue.toString() + " € (" + signReal + differenceReal + " € / " + signReal + differencePercentReal + " %)",
                    fontSize = 16.sp,
                    color = colorReal,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
        //Portfolio tab buttons
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolio))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Portfolio))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolioData))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Data))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolioEvolution))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Evolution))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolioEvolutionReal))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Evolution_Real))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolioHistory))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.History))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewPortfolioHistoryGraph))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Graphics))
            }
        }
    }
}