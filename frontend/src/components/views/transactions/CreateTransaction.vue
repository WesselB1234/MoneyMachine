<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
const transaction = {}
const createTransaction = async () => {
  const fromAccount = document.getElementById("fromAccount").value;
  const toAccount = document.getElementById("toAccount").value;
  const amount = document.getElementById("amount").value;
  transaction.value = {
    fromAccount,
    toAccount,
    amount
  }

  try {
    await axios.post("/api/transactions", {
      transaction
    });
   
  } catch (error) {
    console.log(error);
  }
};

</script>

<template>
  <form @submit.prevent="createTransaction">
    <div class="mb-3">
      <label for="fromAccount" class="form-label">From Account</label>
      <input type="text" class="form-control" id="fromAccount" name="fromAccount">
    </div>
    <div class="mb-3">
      <label for="toAccount" class="form-label">To Account</label>
      <input type="text" class="form-control" id="toAccount" name="toAccount">
    </div>
    <div class="mb-3">
      <label for="amount" class="form-label">Amount</label>
      <input type="number" class="form-control" id="amount" name="amount">
    </div>
    <button @click="createTransaction" class="btn btn-primary">Submit</button>
  </form>
 
</template>
