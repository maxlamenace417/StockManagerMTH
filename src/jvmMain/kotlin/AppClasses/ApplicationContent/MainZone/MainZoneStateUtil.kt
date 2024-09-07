package AppClasses.ApplicationContent.MainZone

import AppClasses.ApplicationState
import androidx.compose.runtime.mutableStateOf

object MainZoneStateUtil {
    private val state = mutableStateOf(MainZoneState())

    fun getMainZoneStateValue(): MainZoneState =
        state.value

    fun setMainZoneStateValue(state: MainZoneState) {
        this.state.value = state
    }
}