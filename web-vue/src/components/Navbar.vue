<template>
  <div id="navbar">
    <a @click="homeClicked" href="#">Home</a>
    <a @click="helpClicked" href="#">Help</a>
  </div>
</template>

<script>
  export default {
    methods: {
      homeClicked() {
        this.$emit('homeClicked');
      },
      helpClicked() {
        this.$emit('helpClicked');
      }
    }
  }
</script>

<style scoped>
  #navbar {
    background: #2aa39b;
    color: #fff;
    padding: 10px;
  }
  #navbar a {
    padding: 0 10px;
    text-decoration: none;
  }
  #navbar a, #navbar a:focus, #navbar a:active {
    color: #fff;
  }
</style>
