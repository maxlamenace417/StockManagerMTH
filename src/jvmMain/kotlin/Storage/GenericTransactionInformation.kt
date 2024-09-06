package Storage

class GenericTransactionInformation(var currentTotalQuantity: Int, var currentTotalMoneyInWithoutTax: Double, var currentTotalTaxIn: Double,
                                    var currentTotalMoneyOutWithoutTax : Double, var currentTotalTaxOut: Double,
                                    var currentEarnings : Double, var currentPRU : Double, var currentPRUToSellToBeEvenWithoutTax: Double) {
}