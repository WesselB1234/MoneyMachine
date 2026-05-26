<script setup>
    import { ref } from 'vue'
    import { useRoute } from 'vue-router'

    import BaseFormField from '@/components/molecules/forms/BaseFormField.vue'
    import SubmitBtn from '@/components/atoms/buttons/SubmitBtn.vue'
    
    const route = useRoute()
    const amount = ref(null)

    function handleDeposit(e) {
        e.preventDefault()
        console.log(amount.value)
    }
</script>

<template>
    <form @submit="handleDeposit">
        <BaseFormField labelName="Amount" type="number" id="amount" name="amount" placeholder="Enter amount of money" v-model="amount"/>
        <SubmitBtn text="Deposit" />
    </form>
</template>