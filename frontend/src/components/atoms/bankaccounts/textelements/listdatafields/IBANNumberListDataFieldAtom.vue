<script setup>
const props = defineProps({
    bankAccount: {
        type: Object,
        required: true
    }
})
</script>

<template>
    <p>{{ bankAccount.iban }}</p>
</template>