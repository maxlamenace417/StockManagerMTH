package AppComponents.ApplicationContent.MainZone.PortfolioTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import AppComponents.Utils.RequiredField
import Storage.Portfolio
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
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun CreatePortfolioTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    var portfolioName by remember { mutableStateOf(TextFieldValue("")) }
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    Column(modifier) {
        Row {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Porftolio_Name
                    )
                )
                Row {
                    BasicTextField(
                        portfolioName,
                        onValueChange = { newPortfolioName ->
                            portfolioName = newPortfolioName
                        },
                        Modifier.background(Color.White).fillMaxWidth().padding(5.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true
                    )
                }
            }
        }
        Row {
            Button(onClick = {
                if (portfolioName.text.isNullOrEmpty()) {
                    BottomBarStateUtil.setBottomBarStateValue(
                        bottomBarState.copy(
                            text = Translator.Translate(
                                applicationState.language,
                                AllTexts.Portfolio_Name_Empty
                            )
                        )
                    )
                } else {
                    if (applicationState.project.portfolios.any { it.name == portfolioName.text }) {
                        BottomBarStateUtil.setBottomBarStateValue(
                            bottomBarState.copy(
                                text = Translator.Translate(
                                    applicationState.language,
                                    AllTexts.Portfolio_Already_Exists
                                )
                            )
                        )
                    } else {
                        var newApplicationState = applicationState.copy()
                        newApplicationState.project.portfolios.add(Portfolio(name = portfolioName.text))
                        BottomBarStateUtil.setBottomBarStateValue(
                            bottomBarState.copy(
                                text = Translator.Translate(
                                    applicationState.language,
                                    AllTexts.Portfolio_Created
                                ) + ": " + portfolioName.text
                            )
                        )
                        MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
                        ApplicationStateUtil.setApplicationStateValue(newApplicationState)
                    }
                }
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Validate))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Cancel))
            }
        }
    }
}