import Session.SessionState
import androidx.compose.runtime.mutableStateOf

object SessionStateUtil {

    private val state = mutableStateOf(SessionState())

    fun getSessionStateValue(): SessionState =
        state.value

    fun setSessionStateValue(state: SessionState) {
        this.state.value = state
    }

}