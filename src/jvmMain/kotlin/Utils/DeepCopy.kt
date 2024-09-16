package Utils

import AppClasses.ApplicationState
import Storage.Project
import com.google.gson.Gson

class DeepCopy {
    companion object {
        fun DeepCopy(applicationState: ApplicationState): ApplicationState {
            return Gson().fromJson(Gson().toJson(applicationState), ApplicationState::class.java)
        }
    }
}