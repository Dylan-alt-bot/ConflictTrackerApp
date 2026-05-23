<template>
  <BaseCard class="card">

    <h3>{{ conflict.name }}</h3>

    <p :class="statusClass(conflict.status)">
      {{ conflict.status }}
    </p>

    <p class="date">
      {{ formatDate(conflict.startDate) }}
    </p>

    <router-link class="btn" :to="`/conflicts/${conflict.id}`">
      Detalls
    </router-link>

    <button class="delete" @click="$emit('delete', conflict.id)">
      Eliminar
    </button>

  </BaseCard>
</template>

<script setup>
import BaseCard from "./BaseCard.vue";

defineProps({
  conflict: Object,
});

defineEmits(["delete"]);

const statusClass = (status) => {
  return {
    ACTIVE: "green",
    FROZEN: "orange",
    ENDED: "red",
  }[status];
};

const formatDate = (date) => {
  return new Date(date).toLocaleDateString("ca-ES");
};
</script>

<style scoped>
.card {
  padding: 15px;
  border-radius: 12px;
  background: white;
  box-shadow: 0 4px 10px rgba(0,0,0,0.08);
}

.green { color: #16a34a; font-weight: bold; }
.orange { color: #f59e0b; font-weight: bold; }
.red { color: #dc2626; font-weight: bold; }

.date {
  color: #64748b;
  font-size: 14px;
}

.btn {
  display: inline-block;
  margin-top: 10px;
  padding: 6px 10px;
  background: #2563eb;
  color: white;
  border-radius: 6px;
  text-decoration: none;
}

.delete {
  margin-left: 10px;
  background: #ef4444;
  color: white;
  border: none;
  padding: 6px 10px;
  border-radius: 6px;
  cursor: pointer;
}
</style>