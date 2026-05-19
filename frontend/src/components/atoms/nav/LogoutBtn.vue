<script setup>
    defineProps({
        url: { 
            type: String, 
            required: true 
        },
        text: {
            type: String,
            default: "Logout"
        }
    })
</script>

<template>
    <a :href="url" class="btn btn-danger text-light">{{ text }}</a>
</template>