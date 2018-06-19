import React from 'react'
import Question from "./Question";

const questions = (props) => (
    <div id="questions">
        <p>{props.questions[props.current].question}</p>
        <form>
            {props.questions[props.current].answers
                .map((answer, index) => <Question id={index+1} key={index} text={answer} clicked={props.answerClicked}/>
                )}
        </form>
    </div>
);

export default questions;