<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
    name: "App"
};
</script>

<style>
#app {
    font-family: "Century Gothic", sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}
a:link,
a:visited,
a:hover {
    text-decoration: none;
    cursor: pointer;
}
body {
    background-color: #ededed;
}
/* * {
    -webkit-border-radius: 0 !important;
    -moz-border-radius: 0 !important;
    border-radius: 0 !important;
} */
</style>
