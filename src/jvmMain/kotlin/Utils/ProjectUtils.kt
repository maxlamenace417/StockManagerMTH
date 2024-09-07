package Utils

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Session.SessionState
import Translation.AllTexts
import Translation.Translator
import java.io.File

class ProjectUtils {
    companion object {
        fun CreateProject(directoryPathToSave: String, name: String) {
            var mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
            val applicationState = ApplicationStateUtil.getApplicationStateValue()
            val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
            //Check if project name is valid
            if (name.isNullOrEmpty()) {
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text=Translator.Translate(applicationState.language, AllTexts.Invalid_Project_Name)))
                return
            }

            //Check if directoryPathToSave exists
            val directory = File(directoryPathToSave)
            if (!(directory.exists() && directory.isDirectory)) {
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text=Translator.Translate(
                    applicationState.language,
                    AllTexts.Invalid_Directory_Path
                ) + ": " + directoryPathToSave))
                return
            }

            //Check if project directory already exists
            var projectDirectoryAbsolutePath = directoryPathToSave + "\\" + name + ".mth"
            var directory2 = File(projectDirectoryAbsolutePath)
            if (!(directory2.exists() && directory2.isDirectory)) {
                //Creating project directory
                File(projectDirectoryAbsolutePath).mkdirs()
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text=Translator.Translate(
                    applicationState.language,
                    AllTexts.Project_Created_Successfully
                ) + ": " + projectDirectoryAbsolutePath))
                ApplicationStateUtil.setApplicationStateValue(applicationState.copy(currentProjectPath = projectDirectoryAbsolutePath, title = name +" "+applicationState.title))
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
            } else {
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text=Translator.Translate(
                    applicationState.language,
                    AllTexts.Project_Already_Exists
                ) + ": " + projectDirectoryAbsolutePath))
                return
            }
            return
        }
    }
}