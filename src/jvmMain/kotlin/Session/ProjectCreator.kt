package Session

import Translation.AllTexts
import Translation.Translator
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileWriter

class ProjectCreator {
    companion object{
        fun CreateProject(directoryPathToSave : String, name:String, sessionState:SessionState) : String{
            //Check if project name is valid
            if(name.isNullOrEmpty()){
                sessionState.bottomMessage = Translator.Translate(sessionState.applicationParameters.language, AllTexts.Invalid_Project_Name)
                return ""
            }

            //Check if directoryPathToSave exists
            val directory = File(directoryPathToSave)
            if(!(directory.exists() && directory.isDirectory)){
                sessionState.bottomMessage = Translator.Translate(sessionState.applicationParameters.language, AllTexts.Invalid_Directory_Path)+directoryPathToSave
                return ""
            }

            //Check if project json already exists
            var projectJSONAbsolutePath = directoryPathToSave+"\\"+name+".json"
            var file = File(projectJSONAbsolutePath)
            if(!(file.exists() && file.isFile)) {
                //Creation du json projet
                var gsonBuilder = GsonBuilder().setPrettyPrinting().create()
                var jsonString = gsonBuilder.toJson(sessionState.project)
                var fileWriter = FileWriter(file)
                fileWriter.write(jsonString)
                fileWriter.close()
                sessionState.bottomMessage = Translator.Translate(sessionState.applicationParameters.language, AllTexts.Project_Created_Successfully)+projectJSONAbsolutePath
                return projectJSONAbsolutePath
            }else{
                sessionState.bottomMessage = Translator.Translate(sessionState.applicationParameters.language, AllTexts.Project_Already_Exists)+projectJSONAbsolutePath
                return ""
            }
            return ""
        }
    }
}