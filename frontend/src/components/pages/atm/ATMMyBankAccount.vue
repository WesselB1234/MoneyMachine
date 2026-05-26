<script setup>
    import MyBankAccountDisplay from '@/components/organisms/atm/MyBankAccountDisplay.vue'
    import ReturnBtn from '@/components/atoms/buttons/ReturnBtn.vue'
</script>

<template>
    <ReturnBtn text="Return to bank account selection" to="/atm/select-bank-account" />
    <MyBankAccountDisplay />
</template>