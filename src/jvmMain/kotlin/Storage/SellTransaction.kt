package Storage

import java.util.*

class SellTransaction(date: Date, quantity: Int, unitPrice: Double, taxPrice: Double) : GenericTransaction(date, quantity, unitPrice,
    taxPrice
) {
    init{
        type = GenericTransactionType.Sell
    }
    override fun CalculateGenericTransactionInformation(previousGenericTransactionInformation: GenericTransactionInformation): GenericTransactionInformation {
        var currentTotalQuantity = previousGenericTransactionInformation.currentTotalQuantity - quantity
        var currentTotalMoneyInWithoutTax = previousGenericTransactionInformation.currentTotalMoneyInWithoutTax
        var currentTotalTaxIn = previousGenericTransactionInformation.currentTotalTaxIn
        var currentTotalMoneyOutWithoutTax = previousGenericTransactionInformation.currentTotalMoneyOutWithoutTax + quantity * unitPrice
        var currentTotalTaxOut = previousGenericTransactionInformation.currentTotalTaxOut + taxPrice
        var currentEarnings = previousGenericTransactionInformation.currentEarnings + quantity * (unitPrice - previousGenericTransactionInformation.currentPRU) - taxPrice
        var currentPRU = previousGenericTransactionInformation.currentPRU
        var currentPRUToSellToBeEvenWithoutTax = (currentTotalMoneyInWithoutTax + currentTotalTaxIn + currentTotalTaxOut - currentTotalMoneyOutWithoutTax) / currentTotalQuantity
        return GenericTransactionInformation(currentTotalQuantity, currentTotalMoneyInWithoutTax, currentTotalTaxIn, currentTotalMoneyOutWithoutTax, currentTotalTaxOut, currentEarnings, currentPRU, currentPRUToSellToBeEvenWithoutTax)
    }
}