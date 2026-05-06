import { createRouter, createWebHistory } from 'vue-router';
import UsersWithoutBankAccountPage from '../components/pages/UsersWithoutBankAccountPage.vue';

const routes = [
    {path: '/user', component: UsersWithoutBankAccountPage}
]

const router = createRouter({
    "history": createWebHistory('/MoneyMachine/'),
    routes,
})

export default router