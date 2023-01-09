import React from 'react';

import MapImg from '../img/map.svg';

import '../css/Map.css';

function Map() {
    return (
        <div>
            <img src={MapImg} alt='Map'/>
            <canvas onClick={handleClick} class="map"></canvas>
        </div>
    );
}

function handleClick(event) {
    console.log(event);
}

export default Map;