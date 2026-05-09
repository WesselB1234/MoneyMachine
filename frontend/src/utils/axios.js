import axios from 'axios';
import { useAuthStore } from '@/stores/authStore.js'
import { useErrorHandlingStore } from '@/stores/errorHandlingStore'
import router from '@/router/router.js'

const apiClient = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
    },
});

apiClient.interceptors.request.use(
    (config) => {
        const authStore = useAuthStore()
        let authToken = authStore.authToken

        if (authToken) {
            config.headers.Authorization = `Bearer ${authToken}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

apiClient.interceptors.response.use(
    response => {
        return response
    },
    error => {
        const authStore = useAuthStore()
        const errorHandlingStore = useErrorHandlingStore()

        if (error.response && error.response.status === 401 && error.response.headers['x-auth-error'] && error.response.headers['x-auth-error'] === 'invalid_token') {
            authStore.setAuthToken(null)
            errorHandlingStore.setErrorMessage("You must login again for the following reason: " + error.response.data.message)
            router.push('/atm/login')
        }

        return Promise.reject(error);
    }
);

export default apiClient;