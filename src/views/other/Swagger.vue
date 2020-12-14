<template>
  <el-card class="main-card">
    <iframe
            X-Frame-Options
            src="/api/swagger-ui.html"
            class="iframe"
    />
  </el-card>
</template>

<style scoped>
  .iframe {
    height: calc(100vh - 170px);
    width: 100%;
    border: none;
    scrolling: yes
  }
</style>
