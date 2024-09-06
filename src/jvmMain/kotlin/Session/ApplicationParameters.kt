package Session

import Translation.AvailableLanguages

data class ApplicationParameters(var lastStartedProjectPath : String = "",
                                 var automaticallySaveProject : Boolean = false,
                                 var language : AvailableLanguages = AvailableLanguages.FR) {
}