package AppClasses.ApplicationContent.BottomBar

import AppClasses.ApplicationState
import androidx.compose.runtime.mutableStateOf

object BottomBarStateUtil {
    private val state = mutableStateOf(BottomBarState())

    fun getBottomBarStateValue(): BottomBarState =
        state.value

    fun setBottomBarStateValue(state: BottomBarState) {
        this.state.value = state
    }
}