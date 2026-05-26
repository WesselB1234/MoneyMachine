<script setup>
    import { onMounted, ref } from 'vue'
    import { useRoute } from 'vue-router'
    import { getBankAccountByIban } from '@/utils/bankAccountLoader.js'
    import { getPriceFormatted } from '@/utils/stringFormatter.js'

    import BaseFormField from '@/components/molecules/forms/BaseFormField.vue'
    import SubmitBtn from '@/components/atoms/buttons/SubmitBtn.vue'
    
    const route = useRoute()
    const amount = ref(null)
    const bankAccount = ref(null)

    function handleDeposit(e) {
        e.preventDefault()
        console.log(amount.value)
    }

    onMounted(async () => {
        bankAccount.value = await getBankAccountByIban(route.params.iban, '/atm/select-bank-account')
    })
</script>

<template>
    <form @submit="handleDeposit">
        <BaseFormField :labelName="'Amount (max ' + getPriceFormatted(bankAccount?.singleTransferLimit) + ')'" type="number" id="amount" placeholder="Enter amount of money" v-model="amount"/>
        <SubmitBtn text="Deposit" />
    </form>
</template>