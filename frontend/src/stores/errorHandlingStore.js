import { defineStore } from "pinia"
import { ref, watch } from "vue";

export const useErrorHandlingStore = defineStore('errorHandling', () => {

    const errorMessage = ref(null)
    const successMessage = ref(null)

    return {errorMessage, successMessage}
})