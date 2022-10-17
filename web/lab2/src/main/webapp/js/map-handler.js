const coefficientX = $('canvas.map')[0].width/2.32;
const coefficientY = $('canvas.map')[0].height/2.32;
const circleSize = $('canvas.map')[0].width/100;
const MAP_MODE = 1;

loadCircles();

$('canvas.map').on('click', function(event) {
    let canvas = $('canvas.map')[0];

    let r = $('input[name="r"]:checked').val();

    if (r === undefined) {
        alert("Выберите радиус для отправки данных");
        return;
    }

    let coordinates = getMouseCoordinates(canvas, event, r);

    console.log("X: "+coordinates.x+" Y: "+-coordinates.y);

    sendCoordinatesByMap(coordinates.x, -coordinates.y);
});

function getMouseCoordinates(canvas, event, r) {
    let rect = canvas.getBoundingClientRect();

    let x = event.clientX - rect.left - canvas.width/2;
    let y = event.clientY - rect.top - canvas.height/2;

    x *= (r/coefficientX);
    y *= (r/coefficientY);

    return {
    "x": x,
    "y": y
    };
}

function sendCoordinatesByMap(x, y) {
    let coordinates = createCoordinates(x, y, $('input[name="r"]:checked').val());
    ajaxSend(coordinates, MAP_MODE, setResponseOnMap);
}

function setResponseOnMap(response, coordinates) {
    coordinates.x *= (coefficientX/coordinates.r);
    coordinates.y *= -(coefficientY/coordinates.r);

    let color;

    if (response=="true")
        color = "green";
    else
        color = "red";

    let canvas = $('canvas.map')[0];
    let ctx = canvas.getContext('2d');
    drawCircle(ctx, coordinates.x+canvas.width/2, coordinates.y+canvas.height/2, circleSize, color);
    saveCircle(coordinates.x+canvas.width/2, coordinates.y+canvas.height/2, circleSize, color);
}

function drawCircle(context, x, y, radius, color) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x, y, radius, 0, Math.PI*2);
    context.stroke();

    context.fill();
}

function saveCircle(x, y, radius, color) {
    let circle = {
        "x": x,
        "y": y,
        "radius": radius,
        "color": color
    };
    let circles = JSON.parse(localStorage.getItem("circles"));

    if (circles == null)
        circles = [];

    circles.push(circle);

    localStorage.setItem("circles", JSON.stringify(circles));
}

function loadCircles() {
    circlesStorage = localStorage.getItem("circles");

    circles = JSON.parse(circlesStorage);

    if (circles == null)
        return;

    let ctx = $('canvas.map')[0].getContext("2d");

    for (let circle of circles) {
        drawCircle(ctx, circle.x, circle.y, circle.radius, circle.color);
    }

}

