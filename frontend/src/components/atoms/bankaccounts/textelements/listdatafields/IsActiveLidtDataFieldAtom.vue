<script setup>
const props = defineProps({
    bankAccount: {
        type: Object,
        required: true
    }
})
</script>

<template>
    <p> {{ bankAccount.isActive }}</p>
</template>