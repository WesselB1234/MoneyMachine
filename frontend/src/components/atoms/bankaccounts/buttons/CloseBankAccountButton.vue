<script setup>
const props = defineProps({
    bankAccount: {
        type: Object,
        required: true
    }
})

const emits = defineEmits(['closeAccount'])
</script>

<template>
    <button @click="$emit('closeAccount', bankAccount.iban)">Close bank account</button>
</template>