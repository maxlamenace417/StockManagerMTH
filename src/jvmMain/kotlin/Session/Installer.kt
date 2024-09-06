package Session

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileWriter

class Installer {
    /*
    Install tree
    .jar
    config
        sessionParameters.json
     */
    val configurationFolderPath : String = "config"
    val sessionParameterPath : String = "config/sessionParameters.json"

    fun CheckIfAlreadyInstall() : Boolean{
        //Check if config directory exists
        var config = File(configurationFolderPath)
        if(!(config.exists() && config.isDirectory)) return false

        //Check if sessionParameters.json exists
        var sessionParameters = File(sessionParameterPath)
        if(!(sessionParameters.exists() && sessionParameters.isFile)) return false

        //Check sessionParameters.json contents
        //TODO()

        return true
    }

    fun Install(){
        if(!CheckIfAlreadyInstall()){
            //Create config directory
            File(configurationFolderPath).mkdirs()

            //Create sessionParameters.json
            var gsonApplicationParameters = GsonBuilder().setPrettyPrinting().create()
            var defaultApplicationParameters = ApplicationParameters()
            var gsonApplicationParametersString = gsonApplicationParameters.toJson(defaultApplicationParameters)
            var sessionParameters = File(sessionParameterPath)
            var sessionParametersWriter = FileWriter(sessionParameters)
            sessionParametersWriter.write(gsonApplicationParametersString)
            sessionParametersWriter.close()
        }
    }
}