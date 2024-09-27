package Session

import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import Storage.Project
import Utils.ApplicationParameters

data class SessionState(var applicationParameters : ApplicationParameters = ApplicationParameters(),
                        var project : Project = Project(),
                        var mainZoneScreenToDisplay : MainZoneScreenToDisplay = MainZoneScreenToDisplay.Nothing,
                        var bottomMessage : String = "") {
}