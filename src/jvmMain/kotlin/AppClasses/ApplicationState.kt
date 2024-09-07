package AppClasses

import Translation.AllTexts
import Translation.AvailableLanguages
import Translation.Translator

/*
Define the global application current parameters
 */
data class ApplicationState(var currentProjectPath : String = "",
                            var automaticallySaveProject : Boolean = false,
                            var language : AvailableLanguages = AvailableLanguages.FR,
                            var title : String = Translator.Translate(language, AllTexts.Stock_Manager_MTH)
){
}