package Components

import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import Session.SessionState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainZone {
    companion object {
        @Composable
        @Preview
        fun MainPart(sessionState: SessionState){
            Column(modifier = Modifier.fillMaxWidth().background(Color.Gray).padding(3.dp)){
                when(sessionState.mainZoneScreenToDisplay){
                    MainZoneScreenToDisplay.NewProject ->{
                        NewProject.MainPart(sessionState)
                    }
                    MainZoneScreenToDisplay.ViewProject ->{
                        ProjectOverview.MainPart(sessionState)
                    }
                    else ->{

                    }
                }
            }
        }
    }
}