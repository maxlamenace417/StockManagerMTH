package Storage

import java.lang.StringBuilder

class GenericTransactionWithInfoList {
    var genericTransactionsWithInformation : MutableList<GenericTransactionWithInformation> = mutableListOf<GenericTransactionWithInformation>()

    fun RecalculateAll(){
        //TODO() faire gaffe au sort
        genericTransactionsWithInformation = genericTransactionsWithInformation.sortedBy { it.genericTransaction.date.time }.toMutableList()
        for(i in 0..genericTransactionsWithInformation.size-1){
            if(i==0){
                var genericTransactionInformationTemp = GenericTransactionInformation(0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
                genericTransactionsWithInformation[i].genericTransactionInformation = genericTransactionsWithInformation[i].genericTransaction.CalculateGenericTransactionInformation(genericTransactionInformationTemp)
            }else{
                genericTransactionsWithInformation[i].genericTransactionInformation = genericTransactionsWithInformation[i].genericTransaction.CalculateGenericTransactionInformation(genericTransactionsWithInformation[i-1].genericTransactionInformation)
            }
        }
    }

    fun AddGenericTransaction(genericTransaction: GenericTransaction){
        genericTransactionsWithInformation.add(GenericTransactionWithInformation(genericTransaction,GenericTransactionInformation(0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)))
        RecalculateAll()
    }

    fun ToString():String{
        var sb = StringBuilder()
        for(i in 0..genericTransactionsWithInformation.size-1){
            sb.append("Date: ")
            sb.append(genericTransactionsWithInformation[i].genericTransaction.date)
            sb.append(" Type: ")
            sb.append(genericTransactionsWithInformation[i].genericTransaction.type)
            sb.append(" Quantité: ")
            sb.append(genericTransactionsWithInformation[i].genericTransaction.quantity)
            sb.append(" Prix execution: ")
            sb.append(genericTransactionsWithInformation[i].genericTransaction.unitPrice)
            sb.append(" Taxe total: ")
            sb.append(genericTransactionsWithInformation[i].genericTransaction.taxPrice)
            sb.append(" Quantité possédée: ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentTotalQuantity)
            sb.append(" Investissement total (net de taxe): ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentTotalMoneyInWithoutTax)
            sb.append(" Taxe in: ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentTotalTaxIn)
            sb.append(" Investissement sorti (net de taxe): ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentTotalMoneyOutWithoutTax)
            sb.append(" Taxe out: ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentTotalTaxOut)
            sb.append(" Gain: ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentEarnings)
            sb.append(" PRU: ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentPRU)
            sb.append(" PRU pour etre even (sans taxe de revente): ")
            sb.append(genericTransactionsWithInformation[i].genericTransactionInformation.currentPRUToSellToBeEvenWithoutTax)
            sb.append("\n")
        }
        return sb.toString()
    }
}