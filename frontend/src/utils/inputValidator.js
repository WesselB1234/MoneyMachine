function throwIfMoneyAmountIsNotValid(amount) {
    
    if (amount <= 0) {
        throw new Error('Amount cannot be less or equal to 0.')
    }
}

function throwIfWithdrawAmountIsNotValid(amount, totalAmount, absoluteLimit) {

    throwIfMoneyAmountIsNotValid(amount)

    if (totalAmount !== undefined && absoluteLimit !== undefined && totalAmount - amount < absoluteLimit) {
        throw new Error('Total amount cannot be less the absolute limit.')
    }
}

export { throwIfMoneyAmountIsNotValid, throwIfWithdrawAmountIsNotValid }