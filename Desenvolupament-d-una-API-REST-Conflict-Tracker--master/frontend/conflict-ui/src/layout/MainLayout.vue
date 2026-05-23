<template>
  <div class="page">
    <main class="content">
      <slot />
    </main>
  </div>
</template>

<style scoped>
.page {
  min-height: 100vh;
  background: #f1f5f9;
  font-family: system-ui;
}

.content {
  padding: 30px;
}
</style>