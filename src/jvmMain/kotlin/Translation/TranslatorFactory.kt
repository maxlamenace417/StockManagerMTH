package Translation

abstract class TranslatorFactory {
     abstract var language : AvailableLanguages
     abstract fun translate(toTranslate:AllTexts) : String
}