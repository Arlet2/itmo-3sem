import React from "react";
import { useDispatch, useSelector } from "react-redux";

import Input from 'react-toolbox/lib/input/Input';
import { selectY, setY } from "../features/formHandler/formSlice";

let errorMessage="";

function YInput() {
    const yCoordinate = useSelector(selectY);
    const dispatch = useDispatch();
    return (
        <div>
            <Input label="Y" hint="-5..5" name="yValue" 
                error={isYCorrect(yCoordinate)?null:errorMessage} 
                required={true} value={yCoordinate} 
                onChange={(value) => dispatch(setY(clearSpacesAndChangeCommaToPoint(value)))} 
                maxLength={16}/>
        </div>
    );
}

function isYCorrect(value) {
    console.log(value);

    if (value == undefined)
        return false;

    if (value === "") {
        errorMessage = "Поле не может быть пустым";
        return false;
    }

    if (isNaN(value)) {
        errorMessage = "Координата Y - число";
        return false;
    }

    if (value < -5) {
        errorMessage = "Координата Y должна быть больше либо равна -5";
        return false;
    }
    if (value > 5) {
        errorMessage = "Координата Y должна быть меньше либо равна 5";
        return false;
    }

    return true;
}

function clearSpacesAndChangeCommaToPoint(sendingValue) {
    return sendingValue
                        .replace(',', '.')
                        .replace(' ', "")
                        .replace('\t', "")
                        .replace('\n', "")
                        .replace('\r', "");
}

export default YInput;