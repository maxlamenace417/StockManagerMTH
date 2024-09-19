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

        fun GetStockPrice(url : String) : Double{
            var res = fetch(url)
            var doc = Jsoup.parse(res)
            var divs = doc.select("span.quotation-last.bd-streaming-select-value-last")
            var price = if(divs.isEmpty()){0.0}else{divs[0].text().toDouble()}
            return price
        }
    }
}