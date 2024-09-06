package Translation

import Storage.GenericTransactionType

abstract class Translator {
    companion object {
        fun Translate(availableLanguages : AvailableLanguages, allTexts : AllTexts) : String{
            when(availableLanguages){
                AvailableLanguages.FR -> {
                    var translatorFR = TranslatorFR()
                    return translatorFR.Translate(allTexts)
                }
                else -> {
                    var translatorEN = TranslatorEN()
                    return translatorEN.Translate(allTexts)
                }
            }
        }
    }
}