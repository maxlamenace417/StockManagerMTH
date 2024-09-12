package AppClasses.ApplicationContent.MainZone

import androidx.compose.runtime.mutableStateOf

object NavigationStateUtil {
    private val state = mutableStateOf(NavigationState())

    fun getNavigationStateValue(): NavigationState =
        state.value

    fun setNavigationStateValue(state: NavigationState) {
        this.state.value = state
    }
}