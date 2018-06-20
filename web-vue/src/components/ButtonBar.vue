<template>
  <div id="buttonbar">
    <button @click="resetClicked">Reset</button>
    <button @click="submitClicked">Submit</button>
  </div>
</template>

<script>
  export default {
    methods: {
      resetClicked() {
        this.$emit('resetClicked');
      },
      submitClicked() {
        this.$emit('submitClicked');
      }
    }
  }
</script>

<style scoped>
  #buttonbar {
    padding: 10px;
    text-align: right;
  }
</style>
