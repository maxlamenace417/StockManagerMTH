package Translation

class TranslatorFR : TranslatorFactory() {
    override var language: AvailableLanguages = AvailableLanguages.FR

    override fun translate(toTranslate: AllTexts): String {
        when (toTranslate) {
            AllTexts.Stock_Manager_MTH -> return ("Gestionnaire de bourse @MTH")
            AllTexts.New_Project -> return ("Nouveau projet")
            AllTexts.Load_Project -> return ("Charger projet")
            AllTexts.Save_Project -> return ("Sauvegarder projet")
            AllTexts.New_Project_Directory_Path -> return ("Chemin du dossier contenant le projet")
            AllTexts.Select -> return ("Choisir")
            AllTexts.Validate -> return ("Valider")
            AllTexts.Project_Name -> return ("Nom du projet")
            AllTexts.Invalid_Directory_Path -> return ("Chemin du dossier invalide")
            AllTexts.Project_Already_Exists -> return ("Le projet existe déjà")
            AllTexts.Project_Created_Successfully -> return ("Projet créé")
            AllTexts.Invalid_Project_Name -> return ("Nom du projet vide")
            AllTexts.Create_Portfolio -> return ("Créer un portefeuille")
            AllTexts.Navigating_To_New_Project_Tab -> return ("Ouverture de l'onglet de création de projet")
            AllTexts.Project_Saved -> return ("Projet sauvegardé")
            AllTexts.View_Project -> return ("Ouverture de l'onglet de visualisation de projet")
            AllTexts.Portfolio_Name_Empty -> return ("Nom du portefeuille vide")
            AllTexts.Portfolio_Already_Exists -> return ("Le portefeuille existe déjà dans le projet")
            AllTexts.Portfolio_Created -> return ("Portefeuille créé")
            AllTexts.Navigating_To_Portfolio_Create_Tab -> return ("Ouverture de l'onglet de création de portefeuille")
            AllTexts.Porftolio_Name -> return ("Nom du portefeuille")
            AllTexts.Select_Valid_File -> return ("Veuillez choisir un fichier valide")
            AllTexts.Project_Loaded -> return ("Projet chargé")
            AllTexts.Project_Closed -> return ("Projet fermé")
            AllTexts.Navigating_To_Portfolio_View_Tab -> return ("Ouverture du portefeuille")
            AllTexts.Navigating_To_Stock_Create_Tab -> return ("Ouverture de l'onglet de création d'action")
            AllTexts.Create_Stock -> return ("Ajouter une action")
            AllTexts.Stock_Name -> return ("Nom de l'action")
            AllTexts.Stock_Name_Empty -> return ("Nom de l'action vide")
            AllTexts.Ticker -> return ("Ticker")
            AllTexts.Ticker_Empty -> return ("Ticker vide")
            AllTexts.Stock_Created -> return ("Action crée")
            AllTexts.`in` -> return ("dans")
            AllTexts.Navigating_To_Portfolio_Edit_Tab -> return ("Edition du portefeuille")
            AllTexts.Portfolio_Edited -> return ("Portefeuille modifié")
            AllTexts.Stocks_Already_Exists_In_Portofolio -> return ("L'action existe déjà dans le portefeuille")
            AllTexts.Navigating_To_Stock_View_Tab -> return ("Ouverture de l'onglet de visualisation de l'action")
            AllTexts.Navigating_To_Stock_Edit_Tab -> return ("Edition de l'action")
            AllTexts.Cancel -> return ("Abandonner")
            AllTexts.Stock_Edited -> return ("Action modifiée")
            AllTexts.Navigating_To_Transaction_Create_Tab -> return ("Ouverture de l'onglet de création de transaction")
            AllTexts.Create_Transaction -> return ("Ajouter une transaction")
            AllTexts.Return -> return ("Retour")
            AllTexts.Transaction_Type -> return ("Type de transaction")
            AllTexts.Buy -> return ("Achat")
            AllTexts.Sell -> return ("Vente")
            AllTexts.Dividend -> return ("Dividende")
            AllTexts.Date -> return ("Date")
            AllTexts.Monday_Calendar -> return ("L")
            AllTexts.Tuesday_Calendar -> return ("M")
            AllTexts.Wednesday_Calendar -> return ("M")
            AllTexts.Thursday_Calendar -> return ("J")
            AllTexts.Friday_Calendar -> return ("V")
            AllTexts.Saturday_Calendar -> return ("S")
            AllTexts.Sunday_Calendar -> return ("D")
            AllTexts.JANUARY -> return ("Janvier")
            AllTexts.FEBRUARY -> return ("Février")
            AllTexts.MARCH -> return ("Mars")
            AllTexts.APRIL -> return ("Avril")
            AllTexts.MAY -> return ("Mai")
            AllTexts.JUNE -> return ("Juin")
            AllTexts.JULY -> return ("Juillet")
            AllTexts.AUGUST -> return ("Aout")
            AllTexts.SEPTEMBER -> return ("Septembre")
            AllTexts.OCTOBER -> return ("Octobre")
            AllTexts.NOVEMBER -> return ("Novembre")
            AllTexts.DECEMBER -> return ("Décembre")
            AllTexts.Hour -> return ("Heure")
            AllTexts.Quantity -> return ("Quantité")
            AllTexts.Unit_Price -> return ("Prix unitaire")
            AllTexts.Total_Taxes -> return ("Taxes totales")
            AllTexts.Select_Transaction_Type -> return ("Sélectionnez le type de transaction")
            AllTexts.Select_Valid_Quantity -> return ("Entrez une quantité valide")
            AllTexts.Select_Valid_Unit_Price -> return ("Entrez un prix unitaire valide")
            AllTexts.Select_Valid_Tax_Price -> return ("Entrez une taxe valide")
            AllTexts.Transaction_Created -> return ("Transaction créée")
            AllTexts.Type -> return ("Type")
            AllTexts.PRU -> return ("PRU")
            AllTexts.Total_Number_Of_Stocks -> return ("Nombre d'action")
            AllTexts.Bourse_Direct_URL -> return ("URL Bourse Direct")
            AllTexts.Bourse_Direct_URL_Not_Set -> return ("Renseignez l'URL Bourse Direct")
            AllTexts.Stock_Price_Refreshed -> return ("Prix de l'action mis à jour")
            AllTexts.Portfolio -> return ("Portefeuille")
            AllTexts.Evolution -> return ("Evolution")
            AllTexts.No_Stocks_Currently_In_Portfolio -> return("Pas d'actions en portefeuille actuellement")
            AllTexts.Transactions -> return ("Transactions")
            AllTexts.History -> return("Historique")
            AllTexts.Open -> return("Ouverture")
            AllTexts.High -> return("Haut")
            AllTexts.Low -> return("Bas")
            AllTexts.Close -> return("Clotûre")
            AllTexts.Volume -> return("Volume")
            AllTexts.CSV_Loaded -> return("CSV chargé")
            AllTexts.No_CSV -> return("Pas de CSV trouvé")
            AllTexts.Update -> return("Mettre à jour")
            else -> return "LABEL KO: " + toTranslate
        }
    }
}