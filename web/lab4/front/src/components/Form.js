import React from 'react';

import XCheckboxes from './XCheckboxes';
import RCheckboxes from './RCheckboxes';
import YInput from './YInput';

import Button from 'react-toolbox/lib/button/Button';


function Form() {
    return (
        <div>
            <XCheckboxes/>
            <YInput/>
            <RCheckboxes/>
            <Button type="submit" label="Send" onMouseEnter={console.log("123")} raised/>
        </div>
    );
}

export default Form;