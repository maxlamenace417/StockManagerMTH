package Storage

import java.util.*

open class GenericTransaction(var date: Date, var quantity: Int, var unitPrice: Double, var taxPrice : Double) {
    open var type : GenericTransactionType = GenericTransactionType.Default
    open fun CalculateGenericTransactionInformation(previousGenericTransactionInformation :GenericTransactionInformation): GenericTransactionInformation{
        return GenericTransactionInformation(0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    }
}