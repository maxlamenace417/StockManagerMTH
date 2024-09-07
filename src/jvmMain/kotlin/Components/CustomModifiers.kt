package Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.grayBoxStyle(padding: Dp = 5.dp): Modifier =
    this.background(Color.Gray).border(BorderStroke(1.dp, Color.Black)).padding(padding)