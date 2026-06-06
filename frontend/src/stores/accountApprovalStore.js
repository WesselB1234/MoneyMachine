import { defineStore } from "pinia";
import { ref } from "vue";

export const useAccountApprovalStore = defineStore('accountApproval', () => {
    const selectedUserId = ref(null);
    const pendingBankAccounts = ref([]);
    const pendingSavingsAccount = ref(null);
    const pendingCheckingAccount = ref(null);

    pendingBankAccounts = {pendingCheckingAccount, pendingSavingsAccount};

    function initApproval(selectedUserId) {

    }

    function clearApproval() {
        pendingBankAccounts.value = [];
        selectedUserId.value = [];
    }

})