<script setup>
    defineProps({
        url: { 
            type: String, 
            required: true 
        },
        text: {
            type: String,
            default: "Login"
        }
    })
</script>

<template>
    <a :href="url" class="btn btn-success text-light">{{ text }}</a>
</template>