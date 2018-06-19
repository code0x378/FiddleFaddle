import React from 'react'

const navbar = (props) => (
    <div id="navbar">
        <a onClick={props.homeClicked} href="#">Home</a>
        <a onClick={props.helpClicked} href="#">Help</a>
    </div>
);

export default navbar;