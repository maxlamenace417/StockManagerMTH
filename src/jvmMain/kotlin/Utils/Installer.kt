package Utils

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
    history
     */
    val configurationFolderPath : String = "config"
    val historyFolderPath : String = "history"
    val sessionParameterPath : String = "$configurationFolderPath/sessionParameters.json"

    fun CheckIfAlreadyInstall() : Boolean{
        //Check if config directory exists
        var config = File(configurationFolderPath)
        if(!(config.exists() && config.isDirectory)) return false

        //Check if history directory exists
        var config2 = File(historyFolderPath)
        if(!(config2.exists() && config2.isDirectory)) return false

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

            //Create history directory
            File(historyFolderPath).mkdirs()

            //Create sessionParameters.json
            SaveSessionParametersFile(ApplicationParameters())
        }
    }

    fun SaveSessionParametersFile(applicationParameters: ApplicationParameters){
        var gsonApplicationParameters = GsonBuilder().setPrettyPrinting().create()
        var gsonApplicationParametersString = gsonApplicationParameters.toJson(applicationParameters)
        var sessionParameters = File(sessionParameterPath)
        var sessionParametersWriter = FileWriter(sessionParameters)
        sessionParametersWriter.write(gsonApplicationParametersString)
        sessionParametersWriter.close()
    }

    fun LoadSessionParameters(): ApplicationParameters {
        val text = File(sessionParameterPath).readText()
        val gson = Gson()
        return gson.fromJson(text, ApplicationParameters::class.java)
    }
}