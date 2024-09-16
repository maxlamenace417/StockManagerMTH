package Storage

import java.util.*

open class GenericTransaction(var date: Date, var quantity: Int, var unitPrice: Double, var taxPrice : Double) {
    open var type : GenericTransactionType = GenericTransactionType.Default
    open fun CalculateGenericTransactionInformation(previousGenericTransactionInformation :GenericTransactionInformation): GenericTransactionInformation{
        if(type == GenericTransactionType.Buy){
            var currentTotalQuantity = previousGenericTransactionInformation.currentTotalQuantity + quantity
            var currentTotalMoneyInWithoutTax = previousGenericTransactionInformation.currentTotalMoneyInWithoutTax + quantity * unitPrice
            var currentTotalTaxIn = previousGenericTransactionInformation.currentTotalTaxIn + taxPrice
            var currentTotalMoneyOutWithoutTax = previousGenericTransactionInformation.currentTotalMoneyOutWithoutTax
            var currentTotalTaxOut = previousGenericTransactionInformation.currentTotalTaxOut
            var currentEarnings = previousGenericTransactionInformation.currentEarnings
            var currentPRU = (previousGenericTransactionInformation.currentPRU * previousGenericTransactionInformation.currentTotalQuantity + quantity * unitPrice + taxPrice)/currentTotalQuantity
            var currentPRUToSellToBeEvenWithoutTax = (currentTotalMoneyInWithoutTax + currentTotalTaxIn + currentTotalTaxOut - currentTotalMoneyOutWithoutTax) / currentTotalQuantity
            return GenericTransactionInformation(currentTotalQuantity, currentTotalMoneyInWithoutTax, currentTotalTaxIn, currentTotalMoneyOutWithoutTax, currentTotalTaxOut, currentEarnings, currentPRU, currentPRUToSellToBeEvenWithoutTax)
        }else if(type == GenericTransactionType.Sell){
            var currentTotalQuantity = previousGenericTransactionInformation.currentTotalQuantity - quantity
            var currentTotalMoneyInWithoutTax = previousGenericTransactionInformation.currentTotalMoneyInWithoutTax
            var currentTotalTaxIn = previousGenericTransactionInformation.currentTotalTaxIn
            var currentTotalMoneyOutWithoutTax = previousGenericTransactionInformation.currentTotalMoneyOutWithoutTax + quantity * unitPrice
            var currentTotalTaxOut = previousGenericTransactionInformation.currentTotalTaxOut + taxPrice
            var currentEarnings = previousGenericTransactionInformation.currentEarnings + quantity * (unitPrice - previousGenericTransactionInformation.currentPRU) - taxPrice
            var currentPRU = previousGenericTransactionInformation.currentPRU
            var currentPRUToSellToBeEvenWithoutTax = (currentTotalMoneyInWithoutTax + currentTotalTaxIn + currentTotalTaxOut - currentTotalMoneyOutWithoutTax) / currentTotalQuantity
            return GenericTransactionInformation(currentTotalQuantity, currentTotalMoneyInWithoutTax, currentTotalTaxIn, currentTotalMoneyOutWithoutTax, currentTotalTaxOut, currentEarnings, currentPRU, currentPRUToSellToBeEvenWithoutTax)
        }else if(type == GenericTransactionType.Dividend){
            var currentTotalQuantity = previousGenericTransactionInformation.currentTotalQuantity
            var currentTotalMoneyInWithoutTax = previousGenericTransactionInformation.currentTotalMoneyInWithoutTax
            var currentTotalTaxIn = previousGenericTransactionInformation.currentTotalTaxIn
            var currentTotalMoneyOutWithoutTax = previousGenericTransactionInformation.currentTotalMoneyOutWithoutTax + quantity * unitPrice
            var currentTotalTaxOut = previousGenericTransactionInformation.currentTotalTaxOut + taxPrice
            var currentEarnings = previousGenericTransactionInformation.currentEarnings + quantity * unitPrice - taxPrice
            var currentPRU = previousGenericTransactionInformation.currentPRU
            var currentPRUToSellToBeEvenWithoutTax = (currentTotalMoneyInWithoutTax + currentTotalTaxIn + currentTotalTaxOut - currentTotalMoneyOutWithoutTax) / currentTotalQuantity
            return GenericTransactionInformation(currentTotalQuantity, currentTotalMoneyInWithoutTax, currentTotalTaxIn, currentTotalMoneyOutWithoutTax, currentTotalTaxOut, currentEarnings, currentPRU, currentPRUToSellToBeEvenWithoutTax)
        }
        return GenericTransactionInformation(0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    }
}