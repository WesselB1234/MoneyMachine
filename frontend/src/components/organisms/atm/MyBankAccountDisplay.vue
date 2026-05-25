<script setup>
    import { onMounted, ref } from 'vue'
    import { useRoute } from 'vue-router'
    import axios from '@/utils/axios.js'
    import router from '@/router/router.js'
    import { useErrorHandlingStore } from '@/stores/errorHandlingStore.js'
    import { useAuthStore } from '@/stores/authStore.js'
    import { getPriceFormatted } from '@/utils/stringFormatter.js'

    import ATMActionOptions from '@/components/molecules/atm/ATMActionOptions.vue'
    import ReturnBtn from '@/components/atoms/buttons/ReturnBtn.vue'
    
    const errorHandlingStore = useErrorHandlingStore()
    const authStore = useAuthStore()
    const route = useRoute()
    const bankAccount = ref(null)
    
    onMounted(async () => {
        try {
            const response = await axios.get('/bank-accounts/' + route.params.iban)
            bankAccount.value = response.data
        }
        catch (ex){
            if (ex.response){
                errorHandlingStore.errorMessage = ex.response.data.details
                router.push('/atm/select-bank-account')
            }
            else {
                useErrorHandlingStore.errorMessage = ex.details
            }
        }
    }) 
</script>

<template>
    <ReturnBtn text="Return to bank account selection" to="/atm/select-bank-account" />
    <h3>Welcome, {{ authStore.atmDecodedAuthToken?.firstName }} </h3>
    <h3>{{ route.params.iban }} | {{ bankAccount?.bankAccountType }}</h3>
    <h3>Balance: {{ getPriceFormatted(bankAccount?.balance) }} </h3>
    <ATMActionOptions :iban="route.params.iban" />
</template>