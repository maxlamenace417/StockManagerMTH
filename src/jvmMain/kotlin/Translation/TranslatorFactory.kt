package Translation

abstract class TranslatorFactory {
     abstract var language : AvailableLanguages
     abstract fun Translate(toTranslate:AllTexts) : String
}