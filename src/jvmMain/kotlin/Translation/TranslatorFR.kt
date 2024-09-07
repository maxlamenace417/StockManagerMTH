package Translation

class TranslatorFR : TranslatorFactory() {
    override var language: AvailableLanguages = AvailableLanguages.FR

    override fun Translate(toTranslate: AllTexts): String {
        when(toTranslate){
            AllTexts.Stock_Manager_MTH -> return ("Gestionnaire de bourse @MTH")
            AllTexts.New_Project -> return ("Nouveau projet")
            AllTexts.Load_Project -> return ("Charger projet")
            AllTexts.Save_Project -> return ("Sauvegarder projet")
            AllTexts.New_Project_Directory_Path -> return("Chemin du dossier contenant le projet")
            AllTexts.Select -> return ("Choisir")
            AllTexts.Validate -> return ("Valider")
            AllTexts.Invalid_Directory_Path -> return ("Chemin du dossier invalide: ")
            AllTexts.Project_Already_Exists -> return ("Le projet existe déjà: ")
            AllTexts.Project_Created_Successfully -> return ("Projet créé: ")
            AllTexts.Invalid_Project_Name -> return ("Nom de projet vide")
            AllTexts.Create_Portfolio -> return ("Créer un portefeuille")
            else -> return "LABEL KO: "+toTranslate
        }
    }
}