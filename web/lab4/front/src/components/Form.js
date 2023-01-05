import React from 'react';

import XCheckboxes from './XCheckboxes';
import RCheckboxes from './RCheckboxes';
import YInput from './YInput';

import Button from 'react-toolbox/lib/button/Button';
import { selectX, selectY, selectR, selectFormError, setFormError, clearFormError } from '../features/formHandler/formSlice';
import { useDispatch, useSelector } from 'react-redux';


function Form() {

    const x = useSelector(selectX);
    const y = useSelector(selectY);
    const r = useSelector(selectR);
    const formError = useSelector(selectFormError);

    const dispatch = useDispatch();

    const isCoordinatesCorrect = () => {
        if (x == undefined) {
            dispatch(setFormError("Выберете координату x"));
            return false;
        }

        if (y == undefined) {
            dispatch(setFormError("Введите координату y"));
            return false;
        }

        if (r == undefined) {
            dispatch(setFormError("Выберете радиус"));
            return false;
        }

        if (y === "" || y == undefined) {
            dispatch(setFormError("Заполните Y"));
            return false;
        }

        if (isNaN(y)) {
            dispatch(setFormError("Координата Y - число"));
            return false;
        }

        if (y < -5) {
            dispatch(setFormError("Координата Y должна быть больше либо равна -5"));
            return false;
        }
        if (y > 5) {
            dispatch(setFormError("Координата Y должна быть меньше либо равна 5"));
            return false;
        }

        if (r <= 0) {
            dispatch(setFormError("Радиус - положительное число"));
            return false;
        }

        return true;
    }

    const sendCoordinates = () => {
        if (!isCoordinatesCorrect())
            return;
        dispatch(clearFormError());

        fetch("/api/secure/add-point", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ x: x, y: y, r: r })
        })
            .then(
                (result) => {
                    if (result.ok) {

                        result.text().then(
                            (text) => { console.log(text) }
                        );
                    }
                    else {
                        result.text().then(
                            (text) => { dispatch(setFormError(JSON.parse(text).message)) }
                        );
                    }
                });
    };

    return (
        <div>
            <XCheckboxes />
            <YInput />
            <RCheckboxes />
            <Button type="submit" label="Send" onClick={sendCoordinates} raised />
            <p>{formError}</p>
        </div>
    );
}

export default Form;