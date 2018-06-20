<template>
  <div id="questions">
    <p>{{ questions[current].question }}</p>
    <form>
      <question v-for="(answer, index) in questions[current].answers"
        :key="index"
        :val="index+1"
        :text="answer" @questionClicked="questionClicked($event)" />
    </form>
  </div>
</template>

<script>
  import Question from './Question'

  export default {
    props: ['questions', 'current', 'selected'],
    components: {
      question: Question
    },
    methods: {
      questionClicked($event) {
        this.$emit('questionClicked', $event);
      }
    }
  }
</script>

<style scoped>
  #questions {
    padding: 20px 10px;
  }
</style>
