package AppClasses

import Storage.Project
import Translation.AllTexts
import Translation.AvailableLanguages
import Translation.Translator

/*
Define the global application current parameters
 */
data class ApplicationState(val currentProjectPath : String = "",
                            val automaticallySaveProject : Boolean = false,
                            val language : AvailableLanguages = AvailableLanguages.FR,
                            val title : String = Translator.Translate(language, AllTexts.Stock_Manager_MTH),
                            val project : Project = Project()
){
}