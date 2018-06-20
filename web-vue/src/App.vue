<template>
  <div id="app">
    <game></game>
  </div>
</template>

<script>
  import Game from "./components/Game"

  export default {
    components: {
      game: Game
    }
  }
</script>

<style>
  body {
    margin: 10px;
    padding: 0;
    font-family: sans-serif;
    background: #efefef;
  }
  h1 {
    font-size: 20px;
    text-align: center;
  }
  button {
    background: #2aa39b;
    color: #fff;
    border: 0;
    padding: 5px 10px;
    border-radius: 5px;
    margin: 0 10px;
  }
  button:hover {
    cursor: pointer;
  }
  #app {
    max-width: 300px;
    margin: 0 auto;
    border: 1px solid #ccc;
    background: #fff;
  }
</style>
