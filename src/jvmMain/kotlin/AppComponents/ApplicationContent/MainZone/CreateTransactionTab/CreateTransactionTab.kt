package AppComponents.ApplicationContent.MainZone.CreateTransactionTab

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationContent.MainZone.NavigationStateUtil
import AppClasses.ApplicationStateUtil
import AppComponents.Utils.RequiredField
import DatePicker
import Storage.*
import Translation.AllTexts
import Translation.Translator
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
@Preview
fun CreateTransactionTab(modifier: Modifier = Modifier) {
    val applicationState = ApplicationStateUtil.getApplicationStateValue()
    val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
    val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
    val navigationState = NavigationStateUtil.getNavigationStateValue()

    var transactionType by remember { mutableStateOf(GenericTransactionType.Default) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMin by remember { mutableStateOf(0) }
    var quantity by remember { mutableStateOf(TextFieldValue("")) }
    var unitPrice by remember { mutableStateOf(TextFieldValue("")) }
    var taxPrice by remember { mutableStateOf(TextFieldValue("")) }

    var selectedDate by remember { mutableStateOf(Date()) }
    val cal = Calendar.getInstance()
    cal.setTime(selectedDate)
    cal.set(Calendar.HOUR_OF_DAY, selectedHour)
    cal.set(Calendar.MINUTE, selectedMin)
    cal.set(Calendar.SECOND, 0)
    selectedDate = cal.time

    Column(modifier) {
        Row(Modifier.padding(bottom = 5.dp)) {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Transaction_Type
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(Translator.Translate(applicationState.language, AllTexts.Buy))
                    RadioButton(selected = transactionType == GenericTransactionType.Buy, onClick = {
                        transactionType = GenericTransactionType.Buy
                    })
                    Text(Translator.Translate(applicationState.language, AllTexts.Sell))
                    RadioButton(selected = transactionType == GenericTransactionType.Sell, onClick = {
                        transactionType = GenericTransactionType.Sell
                    })
                    Text(Translator.Translate(applicationState.language, AllTexts.Dividend))
                    RadioButton(selected = transactionType == GenericTransactionType.Dividend, onClick = {
                        transactionType = GenericTransactionType.Dividend
                    })
                }
            }
        }
        Row(Modifier.padding(bottom = 5.dp)) {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Date
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(SimpleDateFormat("EEEE d MMMM yyyy HH:mm").format(selectedDate), Modifier.background(color = Color.White))
                    //Text(selectedDate.toString())
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Image(
                            painter = painterResource("img/calendar.png"),
                            contentDescription = ""
                        )
                    }
                    if (showDatePicker) {
                        DatePicker(
                            initDate = selectedDate,
                            onDismissRequest = { showDatePicker = false },
                            onDateSelect = {
                                selectedDate = it
                                showDatePicker = false
                                val cal = Calendar.getInstance()
                                cal.setTime(selectedDate)
                                cal.set(Calendar.HOUR_OF_DAY, selectedHour)
                                cal.set(Calendar.MINUTE, selectedMin)
                                selectedDate = cal.time
                            }
                        )
                    }
                }
            }
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Hour
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    var expandedHourSelector by remember { mutableStateOf(false) }
                    var expandedMinSelector by remember { mutableStateOf(false) }
                    Text(selectedHour.toString(), Modifier.clickable(onClick = {
                        expandedHourSelector = !expandedHourSelector
                    }).background(color=Color.White), fontSize = 40.sp)
                    DropdownMenu(
                        expanded = expandedHourSelector,
                        onDismissRequest = { expandedHourSelector = false }
                    ) {
                        for(i in 0..23){
                            DropdownMenuItem(
                                onClick = {
                                    expandedHourSelector = false
                                    selectedHour = i
                                }
                            ) {
                                Text(i.toString())
                            }
                        }
                    }
                    Text(":", fontSize = 24.sp)
                    Text(selectedMin.toString(), Modifier.clickable(onClick = {
                        expandedMinSelector = !expandedMinSelector
                    }).background(color=Color.White), fontSize = 40.sp)
                    DropdownMenu(
                        expanded = expandedMinSelector,
                        onDismissRequest = { expandedMinSelector = false }
                    ) {
                        for(i in 0..59){
                            DropdownMenuItem(
                                onClick = {
                                    expandedMinSelector = false
                                    selectedMin = i
                                }
                            ) {
                                Text(i.toString())
                            }
                        }
                    }
                }

                val cal = Calendar.getInstance()
                cal.setTime(selectedDate)
                cal.set(Calendar.HOUR_OF_DAY, selectedHour)
                cal.set(Calendar.MINUTE, selectedMin)
                selectedDate = cal.time
            }
        }
        Row(Modifier.padding(bottom = 5.dp)) {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Quantity
                    )
                )
                BasicTextField(
                    quantity,
                    onValueChange = { newQuantity ->
                        quantity = newQuantity
                    },
                    Modifier.background(Color.White).fillMaxWidth().padding(5.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                    singleLine = true
                )
            }
        }
        Row(Modifier.padding(bottom = 5.dp)) {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Unit_Price
                    )
                )
                BasicTextField(
                    unitPrice,
                    onValueChange = { newUnitPrice ->
                        unitPrice = newUnitPrice
                    },
                    Modifier.background(Color.White).fillMaxWidth().padding(5.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                    singleLine = true
                )
            }
        }
        Row(Modifier.padding(bottom = 5.dp)) {
            Column {
                RequiredField(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Total_Taxes
                    )
                )
                BasicTextField(
                    taxPrice,
                    onValueChange = { newTaxPrice ->
                        taxPrice = newTaxPrice
                    },
                    Modifier.background(Color.White).fillMaxWidth().padding(5.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                    singleLine = true
                )
            }
        }
        Row {
            Button(onClick = {
                if(transactionType==GenericTransactionType.Default){
                    BottomBarStateUtil.setBottomBarStateValue(
                        bottomBarState.copy(
                            text = Translator.Translate(
                                applicationState.language,
                                AllTexts.Select_Transaction_Type
                            )
                        )
                    )
                }else{
                    var quantityFinal = quantity.text.toIntOrNull()
                    if(quantityFinal==null){
                        BottomBarStateUtil.setBottomBarStateValue(
                            bottomBarState.copy(
                                text = Translator.Translate(
                                    applicationState.language,
                                    AllTexts.Select_Valid_Quantity
                                )
                            )
                        )
                    }else{
                        var unitPriceFinal = unitPrice.text.toDoubleOrNull()
                        if(unitPriceFinal==null){
                            BottomBarStateUtil.setBottomBarStateValue(
                                bottomBarState.copy(
                                    text = Translator.Translate(
                                        applicationState.language,
                                        AllTexts.Select_Valid_Unit_Price
                                    )
                                )
                            )
                        }else{
                            var taxPriceFinal = taxPrice.text.toDoubleOrNull()
                            if(taxPriceFinal==null){
                                BottomBarStateUtil.setBottomBarStateValue(
                                    bottomBarState.copy(
                                        text = Translator.Translate(
                                            applicationState.language,
                                            AllTexts.Select_Valid_Tax_Price
                                        )
                                    )
                                )
                            }else{
                                //TODO() Add more controls
                                var newApplicationState = applicationState.copy()
                                newApplicationState.project.portfolios.first { it.name == navigationState.currentPortfolio }.stocks.first { it.name == navigationState.currentStockName && it.ticker == navigationState.currentStockTicker }.genericTransactionWithInfoList.AddGenericTransaction(
                                    GenericTransaction(selectedDate, quantityFinal, unitPriceFinal, taxPriceFinal, transactionType)
                                )
                                BottomBarStateUtil.setBottomBarStateValue(
                                    bottomBarState.copy(
                                        text = Translator.Translate(
                                            applicationState.language,
                                            AllTexts.Transaction_Created
                                        ) + ": " + navigationState.currentStockName  + " (" + navigationState.currentStockTicker + ") " + Translator.Translate(
                                            applicationState.language,
                                            AllTexts.`in`
                                        ) + " " + navigationState.currentPortfolio
                                    )
                                )
                                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewStock))
                                ApplicationStateUtil.setApplicationStateValue(newApplicationState)
                            }
                        }
                    }
                }
            }){
                Text(Translator.Translate(applicationState.language, AllTexts.Validate))
            }
            Button(onClick = {
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewStock))
            }) {
                Text(Translator.Translate(applicationState.language, AllTexts.Cancel))
            }
        }
    }
}