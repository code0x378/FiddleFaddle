import React from 'react'
import Wrapper from "../hoc/Wrapper";

const score = (props) => (
    <Wrapper>
        <h1>Fiddle Faddle</h1>
        <div id="score">
            <div id="level">Level: 1</div>
            <div id="thescore">Score: {props.score} / {props.current}</div>
        </div>
    </Wrapper>
);

export default score;