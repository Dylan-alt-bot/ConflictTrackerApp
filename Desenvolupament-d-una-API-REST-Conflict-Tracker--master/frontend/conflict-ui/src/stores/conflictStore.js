import { defineStore } from "pinia";
import { api } from "../services/api";

export const useConflictStore = defineStore("conflicts", {
  state: () => ({
    conflicts: [],
    loading: false,
    error: null,
    search: "",
  }),

  actions: {
    async fetchConflicts() {
      this.loading = true;
      try {
        const res = await api.get("/conflicts");
        this.conflicts = res.data;
      } catch (e) {
        this.error = "Error carregant conflictes";
      } finally {
        this.loading = false;
      }
    },
  },

  getters: {
    filteredConflicts: (state) =>
      state.conflicts.filter((c) =>
        c.name.toLowerCase().includes(state.search.toLowerCase())
      ),

    totalActive: (state) =>
      state.conflicts.filter((c) => c.status === "ACTIVE").length,
  },
});