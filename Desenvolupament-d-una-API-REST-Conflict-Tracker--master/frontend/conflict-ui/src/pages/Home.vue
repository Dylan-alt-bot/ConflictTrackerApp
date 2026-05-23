<template>
  <div>
    <h1>Conflict Tracker</h1>

    <input
      v-model="store.search"
      placeholder="Cerca conflictes..."
      class="search"
    />

    <p>Total ACTIVE: {{ store.totalActive }}</p>

    <div v-if="store.loading">Carregant...</div>

    <div v-else-if="store.filteredConflicts.length === 0">
      No hi ha conflictes
    </div>

    <!-- GRID -->
    <div class="grid">
      <ConflictCard
        v-for="c in store.filteredConflicts"
        :key="c.id"
        :conflict="c"
        @delete="removeConflict"
      />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useConflictStore } from "../stores/conflictStore";
import ConflictCard from "../components/ConflictCard.vue";

const store = useConflictStore();

onMounted(() => {
  store.fetchConflicts();
});

const removeConflict = (id) => {
  store.conflicts = store.conflicts.filter((c) => c.id !== id);
};
</script>

<style scoped>
h1 {
  margin-bottom: 20px;
  color: #0f172a;
}

.search {
  padding: 10px;
  width: 300px;
  margin-bottom: 20px;
  border-radius: 8px;
  border: 1px solid #ccc;
}

/* 🔥 GRID IMPORTANT */
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
  gap: 20px;
}
</style>