package AppClasses

import Session.SessionState
import androidx.compose.runtime.mutableStateOf

object ApplicationStateUtil {
    private val state = mutableStateOf(ApplicationState())

    fun getApplicationStateValue(): ApplicationState =
        state.value

    fun setApplicationStateValue(state: ApplicationState) {
        this.state.value = state
    }
}