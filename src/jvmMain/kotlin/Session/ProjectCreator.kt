package Session

import Translation.AllTexts
import Translation.Translator
import java.io.File

class ProjectCreator {
    companion object{
        fun CreateProject(directoryPathToSave : String, name:String, session:Session) : String{
            //Check if project name is valid
            if(name.isNullOrEmpty()){
                session.bottomMessage = Translator.Translate(session.applicationParameters.language, AllTexts.Invalid_Project_Name)
                return ""
            }

            //Check if directoryPathToSave exists
            val directory = File(directoryPathToSave)
            if(!(directory.exists() && directory.isDirectory)){
                session.bottomMessage = Translator.Translate(session.applicationParameters.language, AllTexts.Invalid_Directory_Path)+directoryPathToSave
                return ""
            }

            //Check if project directory already exists
            var projectDirectoryAbsolutePath = directoryPathToSave+"\\"+name+".mth"
            var directory2 = File(projectDirectoryAbsolutePath)
            if(!(directory2.exists() && directory2.isDirectory)) {
                //Creation du dossier projet
                File(projectDirectoryAbsolutePath).mkdirs()
                session.bottomMessage = Translator.Translate(session.applicationParameters.language, AllTexts.Project_Created_Successfully)+projectDirectoryAbsolutePath
                return projectDirectoryAbsolutePath
            }else{
                session.bottomMessage = Translator.Translate(session.applicationParameters.language, AllTexts.Project_Already_Exists)+projectDirectoryAbsolutePath
                return ""
            }
            return ""
        }
    }
}