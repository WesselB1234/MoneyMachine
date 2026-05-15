import axios from "axios";

const instance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 5000,
    headers: { "X-Custom-Header": "test"},
});