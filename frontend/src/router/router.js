const routes = [
    {path: '/'},
    {path: '/about'}
]

const router = createRouter({
    "history": createWebHistory('/MoneyMachine/'),
    routes,
})

export default router