package Storage

class Stock(
    var name: String = "",
    var ticker: String = "",
    var currentValue: Double = 0.0,
    var genericTransactionWithInfoList: GenericTransactionWithInfoList = GenericTransactionWithInfoList(),
    var bourseDirectURL: String = ""
) {
    fun getCurrentTotalInvestedValue(): Double {
        var res = 0.0
        if (genericTransactionWithInfoList.genericTransactionsWithInformation.size > 0) {
            res = genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentPRU * genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentTotalQuantity
        }
        return res
    }

    fun getCurrentTotalInvestedValueReal(): Double {
        var res = 0.0
        if (genericTransactionWithInfoList.genericTransactionsWithInformation.size > 0) {
            res = genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentPRUToSellToBeEvenWithoutTax * genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentTotalQuantity
        }
        if(res.isNaN()){
            res = 0.0
        }
        return res
    }

    fun getCurrentTotalValue(): Double {
        var res = 0.0
        if (genericTransactionWithInfoList.genericTransactionsWithInformation.size > 0) {
            res = currentValue * genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentTotalQuantity
        }
        return res
    }

    fun getCurrentTotalQuantity(): Int{
        var res = 0
        if (genericTransactionWithInfoList.genericTransactionsWithInformation.size > 0) {
            res = genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentTotalQuantity
        }
        return res
    }

    fun getCurrentPRU():Double{
        var res = 0.0
        if (genericTransactionWithInfoList.genericTransactionsWithInformation.size > 0) {
            res = genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentPRU
        }
        return res
    }

    fun getCurrentPRUToSellToBeEvenWithoutTax():Double{
        var res = 0.0
        if (genericTransactionWithInfoList.genericTransactionsWithInformation.size > 0) {
            res = genericTransactionWithInfoList.genericTransactionsWithInformation.last().genericTransactionInformation.currentPRUToSellToBeEvenWithoutTax
        }
        if(res.isInfinite()){
            res=0.0
        }
        return res
    }

    fun getCurrentEvolution() : Double{
        return getCurrentTotalValue()/getCurrentTotalInvestedValue()
    }
    fun getCurrentEvolutionReal() : Double{
        return getCurrentTotalValue()/getCurrentTotalInvestedValueReal()
    }


}