<script setup>
import { useAuthStore } from '@/stores/authStore.js'
import { ref, computed } from 'vue'
import EmployeeFromField from '@/components/atoms/transactions/EmployeeFromField.vue'
import UserFromField from '@/components/atoms/transactions/UserFromField.vue'
import ToField from '@/components/atoms/transactions/ToField.vue'
const authStore = useAuthStore()
const websiteDecodedAuthToken = computed(() => authStore.websiteDecodedAuthToken ?? null)
const props = defineProps({
  transaction: Object
})
const setFromIban = (iban) => {
  emit('update:transaction', {
    ...props.transaction,
    fromBankAcountIban: iban
  })
}

const setToIban = (iban) => {
  emit('update:transaction', {
    ...props.transaction,
    toBankAcountIban: iban
  })
}

const emit = defineEmits(['update:transaction'])

</script>

<template>
    <EmployeeFromField v-if="websiteDecodedAuthToken.role=='EMPLOYEE'"  @select="setFromIban"/>
    <UserFromField v-else-if="websiteDecodedAuthToken.role=='USER'"  @select="setFromIban"/>
    <ToField  @select="setToIban"/>
</template>
