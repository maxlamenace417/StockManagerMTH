package Session

import Storage.Portfolio
import Storage.Project
import Storage.Stock
import Translation.AvailableLanguages

class Session {
    var lastStartedProjectPath : String = ""
    var automaticallySaveProject : Boolean = false
    var language : AvailableLanguages = AvailableLanguages.FR
    var project : Project = Project()
}