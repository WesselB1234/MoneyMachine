import { defineStore } from 'pinia'
import { jwtDecode } from 'jwt-decode'
import { ref } from 'vue'
import axios from '@/utils/axios.js'

const localStorageAtmAuthKey = 'money_machine_atm_auth_token'

export const useAuthStore = defineStore('auth', () => {

    let atmAuthToken = ref(getAuthTokenFromLocalStorage(localStorageAtmAuthKey))
    let atmDecodedAuthToken = ref(getDecodedAuthToken(atmAuthToken.value))

    function getAuthTokenFromLocalStorage(localStorageAuthKey){

        const localStorageAuthToken = localStorage.getItem(localStorageAuthKey)

        if (localStorageAuthToken){
            return localStorageAuthToken
        }

        return null
    }
    
    function setAuthTokenByVariables(token, authTokenVariable, decodedAuthTokenVariable) {
        
        authTokenVariable.value = token;
        decodedAuthTokenVariable.value = getDecodedAuthToken(authTokenVariable.value)

        if (token) {
            localStorage.setItem(localStorageAtmAuthKey, token)
        } 
        else {
            localStorage.removeItem(localStorageAtmAuthKey)
        }
    }

    function setAtmAuthToken(token) {
        setAuthTokenByVariables(token, atmAuthToken, atmDecodedAuthToken)
    }

    function getDecodedAuthToken(authToken) {

        if (authToken !== null) {
            return jwtDecode(authToken)
        }
        
        return null
    }

    return {atmAuthToken, atmDecodedAuthToken, setAtmAuthToken}
})