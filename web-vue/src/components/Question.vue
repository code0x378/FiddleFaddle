<template>
  <div id="question">
    <div><input type="radio" name="answer" :value="val" @click="questionClicked($event)"/>{{ text }}</div>
  </div>
</template>

<script>
  export default {
    props: {
      val: Number,
      text: String
    },
    methods: {
      questionClicked($event) {
        this.$emit('questionClicked', $event);
      }
    }
  }
</script>

<style scoped>
  #question {
    padding: 5px 0;
  }
</style>
