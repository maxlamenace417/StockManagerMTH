package Utils

import org.jsoup.Jsoup
import java.text.SimpleDateFormat

class BourseDirectParser {
    companion object{
        fun GetStockPriceFromBourseDirectURL(url : String) : Double{
            var res = URLUtils.fetch(url)
            var doc = Jsoup.parse(res)
            var divs = doc.select("span.quotation-last.bd-streaming-select-value-last")
            var price = if(divs.isEmpty()){0.0}else{divs[0].text().toDouble()}
            return price
        }

        fun GetHistoryURLFromBourseDirectURL(url:String) : String{
            var urlHistory = "https://www.boursedirect.fr/api/instrument/download/history/"
            var firstSeparator = url.split("/")
            if(firstSeparator.size>3){
                var secondSeparator = firstSeparator[firstSeparator.size-2].split("-")
                if(secondSeparator.size>2){
                    urlHistory+= secondSeparator[secondSeparator.size-1]+"/"
                    urlHistory+= secondSeparator[secondSeparator.size-3]+"/"
                    urlHistory+= secondSeparator[secondSeparator.size-2]
                    return urlHistory
                }
            }
            return ""
        }

        fun GetHistoryFromBourseDirectURL(url:String) : MutableList<HistoryBourseDirect>{
            var result = mutableListOf<HistoryBourseDirect>()
            var urlHistory = GetHistoryURLFromBourseDirectURL(url)
            if(!urlHistory.isNullOrEmpty()){
                var csv = URLUtils.fetch(urlHistory)
                var csvSplit = csv.split("\n")
                if(csvSplit.size>1){
                    for(i in 1..(csvSplit.size-2)){
                        var line = csvSplit[i].split(";")
                        val formatter = SimpleDateFormat("\"yyyy/MM/dd")
                        var historyBourseDirect = HistoryBourseDirect(formatter.parse(line[0].split(" ")[0]), line[1].toDouble(), line[2].toDouble(), line[3].toDouble(), line[4].toDouble(), line[5].toInt())
                        result.add(historyBourseDirect)
                    }
                }
            }
            return result
        }
    }
}