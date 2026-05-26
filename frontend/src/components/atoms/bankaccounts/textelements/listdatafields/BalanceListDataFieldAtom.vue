<script setup>
const props = defineProps({
    bankAccount: {
        type: Object,
        required: true
    }
})
</script>

<template>
    <p>${{ bankAccount.balance }}</p>
</template>