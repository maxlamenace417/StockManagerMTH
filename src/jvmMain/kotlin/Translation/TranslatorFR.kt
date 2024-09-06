package Translation

class TranslatorFR : TranslatorFactory() {
    override var language: AvailableLanguages = AvailableLanguages.FR

    override fun Translate(toTranslate: AllTexts): String {
        when(toTranslate){
            AllTexts.Stock_Manager_MTH -> return ("Gestionnaire de bourse @MTH")
            AllTexts.New_Project -> return ("Nouveau projet")
            AllTexts.Load_Project -> return ("Charger projet")
            AllTexts.Save_Project -> return ("Sauvegarder projet")
            else -> return "LABEL KO: "+toTranslate
        }
    }
}