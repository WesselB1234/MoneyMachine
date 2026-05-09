<script setup>
import UsersTableGridTemplate from '../templates/UsersTableGridTemplate.vue';
import axios from 'axios'
import { onMounted, ref } from 'vue';

const usersWithoutBankAccounts = ref([])

onMounted(async () => {
    try
    {
        const result = await axios.get("/users");
        usersWithoutBankAccounts.value = result.data;
        console.log(result.data);
    }
    catch (err)
    {
        console.log(err);
    }
})
</script>

<template>
<UsersTableGridTemplate :users="usersWithoutBankAccounts" />
</template>