package Translation

class TranslatorFR : TranslatorFactory() {
    override var language: AvailableLanguages = AvailableLanguages.FR

    override fun translate(toTranslate: AllTexts): String {
        when(toTranslate){
            AllTexts.Stock_Manager_MTH -> return ("Gestionnaire de bourse @MTH")
            AllTexts.New_Project -> return ("Nouveau projet")
            AllTexts.Load_Project -> return ("Charger projet")
            AllTexts.Save_Project -> return ("Sauvegarder projet")
            AllTexts.New_Project_Directory_Path -> return("Chemin du dossier contenant le projet")
            AllTexts.Select -> return ("Choisir")
            AllTexts.Validate -> return ("Valider")
            AllTexts.Project_Name -> return ("Nom du projet")
            AllTexts.Invalid_Directory_Path -> return ("Chemin du dossier invalide")
            AllTexts.Project_Already_Exists -> return ("Le projet existe déjà")
            AllTexts.Project_Created_Successfully -> return ("Projet créé")
            AllTexts.Invalid_Project_Name -> return ("Nom de projet vide")
            AllTexts.Create_Portfolio -> return ("Créer un portefeuille")
            AllTexts.Navigating_To_New_Project_Tab -> return ("Ouverture de l'onglet de création de projet")
            AllTexts.Project_Saved -> return ("Projet sauvegardé")
            AllTexts.View_Project -> return ("Ouverture de l'onglet de visualisation de projet")
            AllTexts.Portfolio_Name_Empty -> return ("Nom du portefeuille vide")
            AllTexts.Portfolio_Already_Exists -> return ("Le portefeuille existe déjà dans le projet")
            AllTexts.Portfolio_Created -> return ("Portefeuille créé")
            AllTexts.Navigating_To_Portfolio_Create_Tab -> return("Ouverture de l'onglet de création de portefeuille")
            AllTexts.Porftolio_Name -> return("Nom du portefeuille")
            AllTexts.Select_Valid_File -> return("Veuillez choisir un fichier valide")
            AllTexts.Project_Loaded -> return("Projet chargé")
            AllTexts.Project_Closed -> return("Projet fermé")
            else -> return "LABEL KO: "+toTranslate
        }
    }
}