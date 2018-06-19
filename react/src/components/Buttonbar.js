import React from 'react'

const buttonbar = (props) => (
    <div id="buttonbar">
        <button onClick={props.resetClicked}>Reset</button>
        <button onClick={props.submitClicked} >Submit</button>
    </div>
);

export default buttonbar;