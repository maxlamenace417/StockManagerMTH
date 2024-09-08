package Translation

abstract class Translator {
    companion object {
        fun Translate(availableLanguages : AvailableLanguages, allTexts : AllTexts) : String{
            when(availableLanguages){
                AvailableLanguages.FR -> {
                    var translatorFR = TranslatorFR()
                    return translatorFR.translate(allTexts)
                }
                else -> {
                    var translatorEN = TranslatorEN()
                    return translatorEN.translate(allTexts)
                }
            }
        }
    }
}