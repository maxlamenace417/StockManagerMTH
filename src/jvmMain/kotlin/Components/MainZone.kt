package Components

import Session.Session
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainZone {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session){
            Column(modifier = Modifier.height(100.dp).width(100.dp)){
                var displayedScreen by remember { mutableStateOf(MainZoneScreenToDisplay.Nothing) }
                when(displayedScreen){
                    MainZoneScreenToDisplay.NewProject ->{

                    }
                    else ->{

                    }
                }
            }
        }
    }
}