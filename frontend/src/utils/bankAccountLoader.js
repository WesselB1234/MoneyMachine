import axios from '@/utils/axios.js'
import { useErrorHandlingStore } from '@/stores/errorHandlingStore.js'
import router from '@/router/router.js'

async function getBankAccountByIban(iban, failureRedirectUrl) {
    
    const errorHandlingStore = useErrorHandlingStore()
    
    try {
        const response = await axios.get('/bank-accounts/' + iban)
        return response.data
    }
    catch (ex){
        if (ex.response){
            errorHandlingStore.errorMessage = ex.response.data.details
        }
        else {
            errorHandlingStore.errorMessage = ex.message
        }

        if (failureRedirectUrl !== undefined) {
            router.push(failureRedirectUrl)
        }
    }
}

export { getBankAccountByIban }