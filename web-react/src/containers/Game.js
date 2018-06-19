import React, {Component} from 'react';
import Wrapper from '../hoc/Wrapper'
import Score from '../components/Score'
import Questions from '../components/Questions'
import Buttonbar from '../components/Buttonbar'
import Navbar from "../components/Navbar";

const gameData = {
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

class Game extends Component {

    state = gameData;


    resetGameHandler = () => {
        this.resetState();
    };

    submitGameHandler = () => {
        if (this.state.current === this.state.questions.length - 1)
            this.resetState();
        else {
            const newState = {...this.state};
            newState.current += 1;
            if(this.state.selected == this.state.questions[this.state.current].correct) {
                newState.score += 1;
                newState.selected = -1;
            }
            this.setState(newState);
        }
    };

    selectAnswerHandler = (event) => {
        this.setState({
            selected: event.target.value
        });
    }

    clickHomeHandler = () => {
        this.resetState();
    }

    clickHelpHandler = () => {
        // just use something dumb
        alert('Copyright 2017 FattySmurff - A few rights reserved')
    }

    resetState = () => {
        this.setState(gameData);
    };

    render() {
        return (
            <Wrapper>
               <Navbar homeClicked={this.clickHomeHandler} helpClicked={this.clickHelpHandler} />
                <Score level={this.state.level} score={this.state.score} current={this.state.current}></Score>
                <Questions answerClicked={this.selectAnswerHandler}  questions={this.state.questions} current={this.state.current}></Questions>
                <Buttonbar resetClicked={this.resetGameHandler} submitClicked={this.submitGameHandler}></Buttonbar>
            </Wrapper>
        );
    }

}

export default Game;