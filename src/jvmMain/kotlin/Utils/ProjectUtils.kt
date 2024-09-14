package Utils

import AppClasses.ApplicationContent.BottomBar.BottomBarStateUtil
import AppClasses.ApplicationContent.MainZone.MainZoneScreenToDisplay
import AppClasses.ApplicationContent.MainZone.MainZoneStateUtil
import AppClasses.ApplicationStateUtil
import Storage.Project
import Translation.AllTexts
import Translation.Translator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileWriter

class ProjectUtils {
    companion object {
        fun loadProject(fileToLoadPath: String, name:String){
            val applicationState = ApplicationStateUtil.getApplicationStateValue()
            val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
            val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
            try{
                val text = File(fileToLoadPath).readText()
                val gson = Gson()
                val project = gson.fromJson(text, Project::class.java)
                ApplicationStateUtil.setApplicationStateValue(applicationState.copy(currentProjectPath = fileToLoadPath, project = project, title = name +" "+applicationState.title))
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text = Translator.Translate(applicationState.language, AllTexts.Project_Loaded)+": "+fileToLoadPath))
            }catch(e : Exception){
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text = Translator.Translate(applicationState.language, AllTexts.Select_Valid_File)+": "+fileToLoadPath))
            }
        }
        fun saveProject(){
            val applicationState = ApplicationStateUtil.getApplicationStateValue()
            val bottomBarState = BottomBarStateUtil.getBottomBarStateValue()
            var projectJSONAbsolutePath = applicationState.currentProjectPath
            var file = File(projectJSONAbsolutePath)
            var gsonBuilder = GsonBuilder().setPrettyPrinting().serializeSpecialFloatingPointValues().create()
            var jsonString = gsonBuilder.toJson(applicationState.project)
            var fileWriter = FileWriter(file)
            fileWriter.write(jsonString)
            fileWriter.close()


            BottomBarStateUtil.setBottomBarStateValue(
                bottomBarState.copy(
                    text = Translator.Translate(
                        applicationState.language,
                        AllTexts.Project_Saved
                    ) + ": " + applicationState.currentProjectPath
                )
            )
        }
        fun createProject(directoryPathToSave: String, name: String) {
            val mainZoneState = MainZoneStateUtil.getMainZoneStateValue()
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
            var projectJSONAbsolutePath = directoryPathToSave + "\\" + name + ".json"
            var file = File(projectJSONAbsolutePath)
            if (!(file.exists() && file.isFile)) {
                //Creating project directory
                var gsonBuilder = GsonBuilder().setPrettyPrinting().create()
                var jsonString = gsonBuilder.toJson(applicationState.project)
                var fileWriter = FileWriter(file)
                fileWriter.write(jsonString)
                fileWriter.close()
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text=Translator.Translate(
                    applicationState.language,
                    AllTexts.Project_Created_Successfully
                ) + ": " + projectJSONAbsolutePath))
                ApplicationStateUtil.setApplicationStateValue(applicationState.copy(currentProjectPath = projectJSONAbsolutePath, title = name +" "+applicationState.title, project = Project(projectName = name)))
                MainZoneStateUtil.setMainZoneStateValue(mainZoneState.copy(mainZoneScreenToDisplay = MainZoneScreenToDisplay.ViewProject))
            } else {
                BottomBarStateUtil.setBottomBarStateValue(bottomBarState.copy(text=Translator.Translate(
                    applicationState.language,
                    AllTexts.Project_Already_Exists
                ) + ": " + projectJSONAbsolutePath))
                return
            }
            return
        }
    }

}