package Components

import Session.Session
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class NewProject {
    companion object {
        @Composable
        @Preview
        fun MainPart(session: Session){
            Column(modifier = Modifier.height(100.dp).width(100.dp)){
                Text("teub")
            }
        }
    }
}