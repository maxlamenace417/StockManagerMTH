package Utils

import org.jsoup.Jsoup
import java.net.URI
import java.net.URL
import java.util.*

class URLUtils {
    companion object{
        fun fetch (url : URL) : String {
            //println("Fetching resource from $url")
            return Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()
        }

        fun fetch (url : String) : String {
            return fetch(URL(url))
        }

        fun fetch (url : URI) : String {
            return fetch(url.toURL())
        }
    }
}