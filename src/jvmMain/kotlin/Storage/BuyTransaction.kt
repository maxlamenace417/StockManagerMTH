package Storage

import java.util.*

class BuyTransaction(date: Date, quantity: Int, unitPrice: Double, taxPrice: Double) : GenericTransaction(date, quantity, unitPrice,
    taxPrice
) {
    init{
        type = GenericTransactionType.Buy
    }

    override fun CalculateGenericTransactionInformation(previousGenericTransactionInformation: GenericTransactionInformation): GenericTransactionInformation {
        var currentTotalQuantity = previousGenericTransactionInformation.currentTotalQuantity + quantity
        var currentTotalMoneyInWithoutTax = previousGenericTransactionInformation.currentTotalMoneyInWithoutTax + quantity * unitPrice
        var currentTotalTaxIn = previousGenericTransactionInformation.currentTotalTaxIn + taxPrice
        var currentTotalMoneyOutWithoutTax = previousGenericTransactionInformation.currentTotalMoneyOutWithoutTax
        var currentTotalTaxOut = previousGenericTransactionInformation.currentTotalTaxOut
        var currentEarnings = previousGenericTransactionInformation.currentEarnings
        var currentPRU = (previousGenericTransactionInformation.currentPRU * previousGenericTransactionInformation.currentTotalQuantity + quantity * unitPrice + taxPrice)/currentTotalQuantity
        var currentPRUToSellToBeEvenWithoutTax = (currentTotalMoneyInWithoutTax + currentTotalTaxIn + currentTotalTaxOut - currentTotalMoneyOutWithoutTax) / currentTotalQuantity
        return GenericTransactionInformation(currentTotalQuantity, currentTotalMoneyInWithoutTax, currentTotalTaxIn, currentTotalMoneyOutWithoutTax, currentTotalTaxOut, currentEarnings, currentPRU, currentPRUToSellToBeEvenWithoutTax)
    }
}