package Components

import Session.Session
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainZone {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session, refresh: (Session) -> Unit){
            var sessionRemember by remember{ mutableStateOf(session) }
            Column(modifier = Modifier.fillMaxWidth().background(Color.Gray).padding(3.dp)){
                when(session.mainZoneScreenToDisplay){
                    MainZoneScreenToDisplay.NewProject ->{
                        NewProject.MainPart(session){
                            sessionRemember = it
                            refresh(sessionRemember)
                        }
                    }
                    else ->{

                    }
                }
            }
        }
    }
}