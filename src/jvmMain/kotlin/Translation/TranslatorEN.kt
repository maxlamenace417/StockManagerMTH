package Translation

class TranslatorEN : TranslatorFactory() {
    override var language: AvailableLanguages = AvailableLanguages.FR

    override fun Translate(toTranslate: AllTexts): String {
        when(toTranslate){
            AllTexts.Stock_Manager_MTH -> return ("Stock Manager @MTH")
            else -> return "LABEL KO: "+toTranslate
        }
    }
}