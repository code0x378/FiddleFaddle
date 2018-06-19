import React from 'react'

const question = (props) => (
    <div id="question">
        <div><input type="radio" name="answer" value={props.id} onClick={props.clicked} />{props.text}</div>
    </div>
);

export default question;