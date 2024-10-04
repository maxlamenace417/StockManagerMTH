package AppComponents.ApplicationContent.MainZone.ViewPortfolioTab

import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.math.RoundingMode

@Composable
@Preview
fun ViewPortfolioTabData(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()
    var portfolio = applicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }
    Column(modifier.fillMaxWidth()) {
        ViewPortfolioTabHeader()
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(0.5f)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        Translator.Translate(applicationState.language, AllTexts.Dividend),
                        Modifier.weight(1f),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    for (i in 0..portfolio.stocks.size - 1) {
                        val interactionSource = remember { MutableInteractionSource() }
                        val hoverState = interactionSource.collectIsHoveredAsState()
                        Row(
                            Modifier.fillMaxWidth().background(
                                color = if (hoverState.value) {
                                    Color.White
                                } else {
                                    Color.Gray
                                }
                            ).fillMaxWidth().hoverable(interactionSource)
                        ) {
                            Text(
                                portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")",
                                Modifier.weight(0.5f),
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                portfolio.stocks[i].getTotalDividend().toBigDecimal().setScale(2, RoundingMode.FLOOR)
                                    .toDouble().toString(), Modifier.weight(0.5f)
                            )
                        }
                    }
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            Translator.Translate(applicationState.language, AllTexts.Total),
                            Modifier.weight(0.5f),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            portfolio.getTotalDividend().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                                .toString(), Modifier.weight(0.5f)
                        )
                    }
                }
            }
            Column(Modifier.weight(0.5f)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        Translator.Translate(applicationState.language, AllTexts.Taxes),
                        Modifier.weight(1f),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    for (i in 0..portfolio.stocks.size - 1) {
                        val interactionSource = remember { MutableInteractionSource() }
                        val hoverState = interactionSource.collectIsHoveredAsState()
                        Row(
                            Modifier.fillMaxWidth().background(
                                color = if (hoverState.value) {
                                    Color.White
                                } else {
                                    Color.Gray
                                }
                            ).fillMaxWidth().hoverable(interactionSource)
                        ) {
                            Text(
                                portfolio.stocks[i].name + " (" + portfolio.stocks[i].ticker + ")",
                                Modifier.weight(0.5f),
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                portfolio.stocks[i].getTotalTaxes().toBigDecimal().setScale(2, RoundingMode.FLOOR)
                                    .toDouble().toString(), Modifier.weight(0.5f)
                            )
                        }
                    }
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            Translator.Translate(applicationState.language, AllTexts.Total),
                            Modifier.weight(0.5f),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            portfolio.getTotalTaxes().toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
                                .toString(), Modifier.weight(0.5f)
                        )
                    }
                }
            }
        }
    }
}