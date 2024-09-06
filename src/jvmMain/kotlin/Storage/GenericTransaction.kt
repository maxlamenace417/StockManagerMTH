package Storage

import java.util.*

abstract class GenericTransaction(var date: Date, var quantity: Int, var unitPrice: Double, var taxPrice : Double) {
    abstract var type : GenericTransactionType
    abstract fun CalculateGenericTransactionInformation(previousGenericTransactionInformation :GenericTransactionInformation): GenericTransactionInformation
}