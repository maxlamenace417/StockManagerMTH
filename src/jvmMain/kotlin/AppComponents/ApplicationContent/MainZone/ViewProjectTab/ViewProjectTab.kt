package AppComponents.ApplicationContent.MainZone.ViewProjectTab

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
    Column{
        Row{
            Button(onClick = {
                //TODO() Create a portfolio
            }){
                Text(Translator.Translate(applicationState.language, AllTexts.Create_Portfolio))
            }
        }
        for(i in 0..applicationState.project.portfolios.size-1){
            //TODO() create a view for portfolios
        }
    }
}