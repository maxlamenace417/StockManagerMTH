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

}