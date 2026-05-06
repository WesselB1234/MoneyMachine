<script setup>
const props = defineProps({
    users: {
        type: Array,
        required: true,
        validator: (value) => {
            return value.every(user =>
                typeof user.userId === 'number' &&
                typeof user.firstName === 'string' &&
                typeof user.lastName === 'string' &&
                typeof user.email === 'string' &&
                typeof user.bsnNumber === 'string' &&
                typeof user.phoneNumber === 'string' &&
                typeof user.role === 'string' &&
                typeof user.isActive === 'boolean' &&
                typeof user.isApproved === 'boolean'
            )
        }
    }
})
</script>

<template>

</template>