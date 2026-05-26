<script setup>
const props = defineProps({
    bankAccount: {
        type: Object,
        required: true
    }
})
</script>

<template>
    <h2>${{ bankAccount.bankAccountType }}</h2>
</template>