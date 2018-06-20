<template>
  <div id="game">
    <navbar @homeClicked="clickHomeHandler" @helpClicked="clickHelpHandler"></navbar>
    <score :score="gamedata.score" :current="gamedata.current"></score>
    <questions
      @questionClicked="selectAnswerHandler($event)"
      :current="gamedata.current"
      :selected="gamedata.selected"
      :questions="gamedata.questions"></questions>
    <buttonbar @resetClicked="resetGameHandler" @submitClicked="submitGameHandler"></buttonbar>
  </div>
</template>

<script>
  import Navbar from "./Navbar";
  import Score from "./Score";
  import Questions from "./Questions";
  import ButtonBar from "./ButtonBar";

  const data = {
    questions: [
      {
        'question': 'What is the job title of the person in charge of the camera and lighting crews working on a film?',
        'answers': [
          'Production Manager',
          'Cinematographer',
          'Unit Manager',
          'Gaffer'
        ],
        'correct': 2
      },
      {
        'question': 'What is a baby rabbit called?',
        'answers': [
          'Kitten',
          'Pup',
          'Rabee',
          'Tuber'
        ],
        'correct': 1
      },
      {
        'question': 'In January 1996 Bill Clinton challenged congress to \"Never, ever\" do this again',
        'answers': [
          'Kiss Monica',
          'Kiss Hilary',
          'Shutdown government',
          'Run for re-election'
        ],
        'correct': 3
      },
      {
        'question': 'This Mississippi capital was named for a general who later became president?',
        'answers': [
          'Topeka',
          'Jackson',
          'Richmond',
          'Grant'
        ],
        'correct': 3
      },
      {
        'question': 'This Honda minivan has a Homeric name',
        'answers': [
          'Ulysses',
          'Troy',
          'Spartan',
          'Odyssey'
        ],
        'correct': 4
      }
    ],
    current: 0,
    level: 1,
    score: 0,
    selected: -1
  };

  export default {
    data() {
      return {
        gamedata: {...data}
      }
    },
    components: {
      navbar: Navbar,
      score: Score,
      questions: Questions,
      buttonbar: ButtonBar
    },
    methods: {
      resetGameHandler() {
        this.resetState();
      },
      submitGameHandler() {
        if (this.gamedata.current >= this.gamedata.questions.length - 1)
          this.resetState();
        else {
          if (this.gamedata.selected == this.gamedata.questions[this.gamedata.current].correct) {
            this.gamedata.score++;
            this.gamedata.selected = -1;
          }
          this.gamedata.current++;
        }
      },
      selectAnswerHandler($event) {
        this.gamedata.selected = $event.target.value;
      },
      clickHomeHandler() {
        this.resetState();
      },
      clickHelpHandler() {
        // just use something dumb
        alert('Copyright 2017 FattySmurff - A few rights reserved')
      },
      resetState() {
        this.gamedata = {...data};
      }
    }
  }
</script>

