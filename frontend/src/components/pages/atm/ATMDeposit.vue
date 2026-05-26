<script setup>
    import { useRoute } from 'vue-router'

    import DepositForm from '@/components/organisms/forms/atm/DepositForm.vue'
    import ReturnBtn from '@/components/atoms/buttons/ReturnBtn.vue'

    const route = useRoute()
</script>

<template>
    <ReturnBtn text="Return to my bank account" :to="'/atm/bank-account/' + route.params.iban" />
    <h1>Deposit</h1>
    <DepositForm />
</template>