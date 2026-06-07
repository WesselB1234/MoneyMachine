import { createApp } from 'vue'
import router from './router/router'
import './style.css'
import App from './App.vue'
import { createPinia } from 'pinia'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

const app = createApp(App);
app.use(createPinia())
app.use(router);
app.mount('#app');
console.log("API URL:" + import.meta.env.VITE_API_URL);
