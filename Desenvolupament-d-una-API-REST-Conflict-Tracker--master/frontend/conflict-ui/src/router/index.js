import { createRouter, createWebHistory } from "vue-router";
import Home from "../pages/Home.vue";
import ConflictDetail from "../pages/ConflictDetail.vue";

const routes = [
  { path: "/", component: Home },
  { path: "/conflicts/:id", component: ConflictDetail },
];

export default createRouter({
  history: createWebHistory(),
  routes,
});