package Storage

class Portfolio(var name: String = "", var stocks: MutableList<Stock> = mutableListOf<Stock>()) {
    fun getCurrentTotalInvestedValue(): Double {
        var res = 0.0
        for (i in 0..stocks.size - 1) {
            res = res + stocks[i].getCurrentTotalInvestedValue()
        }
        return res
    }

    fun getCurrentTotalValue(): Double {
        var res = 0.0
        for (i in 0..stocks.size - 1) {
            res = res + stocks[i].getCurrentTotalValue()
        }
        return res
    }
}