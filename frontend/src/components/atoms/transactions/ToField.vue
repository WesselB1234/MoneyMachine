<script setup>
import apiClient from '@/utils/axios.js'
import { ref, onMounted } from 'vue'
const accounts = ref([])
const ibans = ref([])
const selectedIban = ref('')

const emit = defineEmits(['select'])
    
   onMounted(async () => {
    try {

        const response = await apiClient.get("/bank-accounts")

         console.log("RESPONSE:", response)

        if (response.status === 200) {

            accounts.value = response.data.items
            ibans.value = accounts.value.map(account => account.iban)
            console.log(accounts)
            console.log(ibans)

        }

    }catch (error) {
    console.log("FULL ERROR OBJECT:", error)

    console.log("RESPONSE:", error.response)
    console.log("DATA:", error.response?.data)

    console.log("MESSAGE:", error.response?.data?.message)
    console.log("TYPE:", error.response?.data?.errorType)
    console.log("CODE:", error.response?.data?.code)
    console.log("LOCATION:", error.response?.data?.location)

    console.log("STRINGIFIED:", JSON.stringify(error.response?.data))
   

    }
}) 
const selectIban = (iban) => {
  selectedIban.value = iban   // UI update
  emit('select', iban)        // naar parent
}
</script>


   <template>
    <div class="dropdown">
  <button
    class="btn btn-secondary dropdown-toggle"
    type="button"
    data-bs-toggle="dropdown"
    aria-expanded="false"
  >
    {{ selectedIban || 'Select to IBAN' }}
  </button>

  <ul class="dropdown-menu">
    <li v-for="iban in ibans" :key="iban">
      <a
        class="dropdown-item"
        href="#"
       @click.prevent="selectIban(iban)"
      >
        {{ iban }}
      </a>
    </li>
  </ul>
</div>
</template>


