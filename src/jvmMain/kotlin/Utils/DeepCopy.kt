package Utils

import AppClasses.ApplicationState
import Storage.Project
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class DeepCopy {
    companion object {
        fun DeepCopy(applicationState: ApplicationState): ApplicationState {
            return GsonBuilder().setPrettyPrinting().serializeSpecialFloatingPointValues().create().fromJson(GsonBuilder().setPrettyPrinting().serializeSpecialFloatingPointValues().create().toJson(applicationState), ApplicationState::class.java)
        }
    }
}