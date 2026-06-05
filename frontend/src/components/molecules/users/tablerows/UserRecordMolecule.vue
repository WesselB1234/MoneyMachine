<script setup>
import ApproveCustomerButtonAtom from "@/atoms/users/buttons/ApproveCustomerButtonAtom.vue";
import BaseTableDataFieldAtom from "../../../atoms/bankaccounts/textelements/datafields/BaseTableDataFieldAtom.vue";

const props = defineProps({
    user: {
        type: Object,
        required: true,
        validator: (value) => {
            return typeof value.userId === 'number' &&
            typeof value.firstName === 'string' &&
            typeof value.lastName === 'string' &&
            typeof value.email === 'string' &&
            typeof value.bsn === 'string' &&
            typeof value.phoneNumber === 'string' &&
            typeof value.role === 'string' &&
            typeof value.isActive === 'boolean' &&
            typeof value.isApproved === 'boolean'
        }
    }
})

</script>

<template>
  <tr class="table table-dark">
    <BaseTableDataFieldAtom :text="user.firstName" />
    <BaseTableDataFieldAtom :text="user.lastName" />
    <BaseTableDataFieldAtom :text="user.email" />
    <BaseTableDataFieldAtom :text="user.bsn" />
    <BaseTableDataFieldAtom :text="user.phoneNumber" />
    <ApproveCustomerButtonAtom :user-id="user.userId"/>
    <router-link :to="`/transactions/user/${user.userId}`" class="btn btn-primary mb-3">view transactions</router-link>
    </tr>  
</template>