import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

import Checkbox from 'react-toolbox/lib/checkbox/Checkbox';
import { selectR, setR } from '../features/formHandler.js/formSlice';

function RCheckboxes() {
    const dispatch = useDispatch();
    const rValue = useSelector(selectR);
    return (
        <div>
            <div>
                <Checkbox label="-5" checked={rValue=== -5} onChange={() => dispatch(setR(-5))} />
                <Checkbox label="-4" checked={rValue === -4} onChange={() => dispatch(setR(-4))} />
                <Checkbox label="-3" checked={rValue === -3} onChange={() => dispatch(setR(-3))} />
                <Checkbox label="-2" checked={rValue === -2} onChange={() => dispatch(setR(-2))} />
                <Checkbox label="-1" checked={rValue === -1} onChange={() => dispatch(setR(-1))} />
                <Checkbox label="0" checked={rValue === 0} onChange={() => dispatch(setR(0))} />
                <Checkbox label="1" checked={rValue === 1} onChange={() => dispatch(setR(1))} />
                <Checkbox label="2" checked={rValue === 2} onChange={() => dispatch(setR(2))} />
                <Checkbox label="3" checked={rValue === 3} onChange={() => dispatch(setR(3))} />
            </div>
        </div>
    );
}

export default RCheckboxes;