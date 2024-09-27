package Utils

import org.jsoup.Jsoup
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.text.SimpleDateFormat

class BourseDirectParser {
    companion object {
        fun GetStockPriceFromBourseDirectURL(url: String): Double {
            var res = URLUtils.fetch(url)
            var doc = Jsoup.parse(res)
            var divs = doc.select("span.quotation-last.bd-streaming-select-value-last")
            var price = if (divs.isEmpty()) {
                0.0
            } else {
                divs[0].text().toDouble()
            }
            return price
        }

        fun GetHistoryURLFromBourseDirectURL(url: String): String {
            var urlHistory = "https://www.boursedirect.fr/api/instrument/download/history/"
            var firstSeparator = url.split("/")
            if (firstSeparator.size > 3) {
                var secondSeparator = firstSeparator[firstSeparator.size - 2].split("-")
                if (secondSeparator.size > 2) {
                    urlHistory += secondSeparator[secondSeparator.size - 1] + "/"
                    urlHistory += secondSeparator[secondSeparator.size - 3] + "/"
                    urlHistory += secondSeparator[secondSeparator.size - 2]
                    return urlHistory
                }
            }
            return ""
        }

        fun GetHistoryCSVFromBourseDirectURL(urlHistory: String): String {
            var result = ""
            if (!urlHistory.isNullOrEmpty()) {
                result = URLUtils.fetch(urlHistory)
                return result
            }
            return result
        }

        fun SaveHistoryCSVFromBourseDirect(csv: String, stockName: String, stockTicker: String) {
            var installer = Installer()
            var file = File(installer.historyFolderPath + "/bd_" + stockName + "_" + stockTicker + ".csv")
            var fileWriter = FileWriter(file)
            fileWriter.write(csv)
            fileWriter.close()
        }

        fun HistoryCSVFromBourseDirectExists(stockName: String, stockTicker: String): Boolean {
            var installer = Installer()
            var file = File(installer.historyFolderPath + "/bd_" + stockName + "_" + stockTicker + ".csv")
            return file.exists() && file.isFile
        }

        fun LoadHistoryCSVFromBourseDirect(stockName: String, stockTicker: String): String {
            var installer = Installer()
            if(HistoryCSVFromBourseDirectExists(stockName, stockTicker)) {
                var file = File(installer.historyFolderPath + "/bd_" + stockName + "_" + stockTicker + ".csv")
                return file.readText()
            }
            return ""
        }

        /*
        Allows to save as a CSV history Load from url then save
        Return true if load and save is OK
         */
        fun SaveHistoryFromBourseDirectURL(url: String, stockName: String, stockTicker: String): Boolean {
            var result = false
            var urlHistory = GetHistoryURLFromBourseDirectURL(url)
            if (!urlHistory.isNullOrEmpty()) {
                var csv = GetHistoryCSVFromBourseDirectURL(urlHistory)
                if (!csv.isNullOrEmpty()) {
                    SaveHistoryCSVFromBourseDirect(csv, stockName, stockTicker)
                    result = true
                }
            }
            return result
        }

        fun GetHistoryFromBourseDirectCSV(
            stockName: String,
            stockTicker: String
        ): MutableList<HistoryBourseDirect> {
            var result = mutableListOf<HistoryBourseDirect>()
            var csv = LoadHistoryCSVFromBourseDirect(stockName, stockTicker)
            var csvSplit = csv.split("\n")
            if (csvSplit.size > 1) {
                for (i in 1..(csvSplit.size - 2)) {
                    var line = csvSplit[i].split(";")
                    val formatter = SimpleDateFormat("\"yyyy/MM/dd")
                    try {
                        var historyBourseDirect = HistoryBourseDirect(
                            formatter.parse(line[0].split(" ")[0]),
                            if(line[1].isNullOrEmpty()){0.0}else{line[1].toDouble()},
                            if(line[2].isNullOrEmpty()){0.0}else{line[2].toDouble()},
                            if(line[3].isNullOrEmpty()){0.0}else{line[3].toDouble()},
                            if(line[4].isNullOrEmpty()){0.0}else{line[4].toDouble()},
                            if(line[5].isNullOrEmpty()){0}else{line[5].toInt()}
                        )
                        result.add(historyBourseDirect)
                    }catch(e:Exception){
                        var rr=0
                    }
                }
            }
            return result
        }
    }
}