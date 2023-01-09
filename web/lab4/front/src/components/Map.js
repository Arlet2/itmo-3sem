import React from 'react';

import '../css/Map.css';

import $ from 'jquery';
import { sendCoordinates } from '../utils';
import { useDispatch, useSelector, useStore } from 'react-redux';
import { selectR, setFormError } from '../features/formHandler/formSlice';
import { selectRows } from '../features/tableHandler/tableSlice';

const coefficientX = 201;
const coefficientY = 199.5;

const width = 601;
const height = 601;

const circleSize = width / 120;

function Map() {

    const dispatch = useDispatch();

    const r = useSelector(selectR);

    const rows = useSelector(selectRows);


    const handleClick = (event) => {
        if (r == undefined) {
            dispatch(setFormError("Выберите радиус"));
            return;
        }

        let canvas = $('canvas.map')[0];

        let coordinates = getMouseCoordinates(canvas, event, r);

        sendCoordinates(dispatch, Number(coordinates.x), -coordinates.y, r);
    }

    rows.forEach(element => {
        if (element.r == r && element.status !== "error") {
            drawCircle($('canvas.map')[0].getContext("2d"), element.x * coefficientX / r + width / 2,
            -element.y * coefficientY / r + height / 2,
            circleSize, element.status==="hit"?"green":"red");
        }
    });

    return (
        <div className='Map'>
            <canvas width={width} height={height} onClick={handleClick} class="map" />
            <svg xmlns="http://www.w3.org/2000/svg" width={width} height={height} fill="none" viewBox={"0 0 " + width + " " + height}>
                <path fill="#000" d="M300.354.646a.501.501 0 0 0-.708 0l-3.182 3.182a.5.5 0 0 0 .708.708L300 1.707l2.828 2.829a.501.501 0 0 0 .708-.708L300.354.646ZM300.5 601V1h-1v600h1Z" />
                <path fill="#000" d="M600.354 301.354a.502.502 0 0 0 0-.708l-3.182-3.182a.502.502 0 0 0-.708.708l2.829 2.828-2.829 2.828a.502.502 0 0 0 .708.708l3.182-3.182ZM0 301.5h600v-1H0v1Z" />
                <path fill="#43F3D4" fill-opacity=".4" d="M300 101h100v200H300z" />
                <path stroke="#000" d="M300 101h100v200H300z" />
                <path fill="#43F3D4" fill-opacity=".4" d="M300 301H100l200-100v100Zm100 0c0 55.228-44.772 100-100 100V301h100Z" />
            </svg>
        </div>
    );
}

export function clearMap() {
    let canvas = $('canvas.map')[0];

    if (canvas == undefined)
        return;

    let ctx = canvas.getContext("2d");

    ctx.clearRect(0, 0, width, height);
}

function drawCircle(context, x, y, radius, color) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x, y, radius, 0, Math.PI * 2);
    context.stroke();

    context.fill();
}

function getMouseCoordinates(canvas, event, r) {
    let rect = canvas.getBoundingClientRect();

    let x = event.clientX - rect.left - canvas.width / 2;
    let y = event.clientY - rect.top - canvas.height / 2;

    x *= (r / coefficientX);
    y *= (r / coefficientY);

    return {
        "x": x.toFixed(2),
        "y": y.toFixed(2)
    };
}

export default Map;