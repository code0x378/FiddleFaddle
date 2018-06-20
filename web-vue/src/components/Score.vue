<template>
  <div>
    <h1>Fiddle Faddle</h1>
    <div id="score">
      <div id="level">Level: 1</div>
      <div id="thescore">Score: {{ score }} / {{ current }}</div>
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      score: Number,
      current: Number
    }
  }
</script>

<style scoped>
  #score {
    padding: 15px 10px;
    font-weight: bold;
  }

  #score div {
    float: left;
    width: 50%;
  }

  #thescore {
    text-align: right;
  }
</style>
