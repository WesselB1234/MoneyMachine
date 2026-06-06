<script setup>
import { onMounted, ref } from 'vue';
import axios from '@/utils/axios';
import router from "@/router/router";
import ApproveCustomerOrganism from '../../organisms/users/ApproveCustomerOrganism.vue';
import { useRouter } from 'vue-router';
const loading = ref(true);
const error = ref(null);
const bankAccounts = ref([]);
const router = useRouter();
const currentRoute = router.currentRoute.value.params;
const fetchUserWithoutABankAccount = async () => {
    loading.value = true;
    error.value = null;
}

const createBankAccounts = async () => {
    loading, value = true;
    error.value = null;

    try {
        const result = await axios.post('/bank-accounts', bankAccounts);
        bankAccounts.value = result.data;
        console.log(result.data);
        await router.push('/users');

    } catch (err) {
        console.log("Error creating bank accounts", err);
        error.value =
            err.message || "Failed to create bank accounts. Please try again later.";
        bankAccounts.value = null;
    }

}
onMounted(() => {
    fetchUsersWithoutABankAccount();
})

</script>

<template>
    <section>
        <!-- Loading State -->
        <section v-if="loadingFetch" class="min-h-screen flex items-center justify-center">
            <section class="text-center">
                <section class="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mb-4">
                    <p class="text-blue">Loading bankaccount</p>
                </section>
            </section>
        </section>

        <!-- Error State -->
        <section v-else-if="error" class="min-h-screen flex items-center justify-center">
            <section class="text-red-600 text-5xl mb-4">⚠️</section>
            <h2 class="text-2xl font-bold text-gray-900 mb-2">
                Error Loading a bankaccount
            </h2>
            <p class="text-gray-600 mb-4">{{ error }}</p>
            <button @click="fetchBankAccount"
                class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors">
                Try Again
            </button>
        </section>
        <ApproveCustomerOrganism @createBankAccount="createBankAccounts" v-else :bankAccount="bankAccount" />
    </section>
</template>