<script setup>
    import { ref } from 'vue'
    import FormLabel from '@/components/atoms/forms/FormLabel.vue'
    import SubmitBtn from '@/components/atoms/buttons/SubmitBtn.vue'

    const selectedBankAccountIban = ref('')

    async function handleLogin(e) {
        e.preventDefault()
        console.log(selectedBankAccountIban.value)
    }
</script>

<template>
    <form @submit="handleLogin">
        <div class="mb-3">
            <FormLabel id="bankAccount" labelName="Select a bank account" />
            <select v-model="selectedBankAccountIban" id="bankAccount" class="custom-select">
                <option value="" disabled>Select an account</option>
                <option value="1">hi</option>
                <option value="2">pootis</option>
            </select>
        </div>

        <SubmitBtn text="Select bank account"/>
    </form>
</template>
