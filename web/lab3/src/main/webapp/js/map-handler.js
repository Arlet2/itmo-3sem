let canvas = $('canvas.map')[0];
const coefficientX = canvas.width/2.8169;
const coefficientY = canvas.height/2.8169;
const circleSize = canvas.width/100;

$('input[name="coordinatesForm:radius"]').attr("readonly", true); // lock r area

loadPoints();

function loadPoints() {
    let canvas = $('canvas.map')[0];
    let ctx = canvas.getContext('2d');

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    ajaxSend("map", handleResults);
}

function handleResults(points) {
    points = JSON.parse(points);

    console.log(points);

    let canvas = $('canvas.map')[0];
    let ctx = canvas.getContext('2d');
    let r = $('input[name="coordinatesForm:radius"]').val();

    for (let point of points) {
        drawCircle(ctx, point.x*coefficientX/r+canvas.width/2,
            -point.y*coefficientY/r+canvas.height/2,
            circleSize, point.hit?"green":"red");
    }
}

$('canvas.map').on('click', function(event) {
    let canvas = $('canvas.map')[0];

    let r = $('input[name="coordinatesForm:radius"]').val();

    if (r === undefined) {
        alert("Выберите корректный радиус для отправки данных");
        return;
    }

    let coordinates = getMouseCoordinates(canvas, event, r);

    console.log("X: "+coordinates.x+" Y: "+-coordinates.y);

    setFields(coordinates.x, coordinates.y, r);
});

function getMouseCoordinates(canvas, event, r) {
    let rect = canvas.getBoundingClientRect();

    let x = event.clientX - rect.left - canvas.width/2;
    let y = event.clientY - rect.top - canvas.height/2;

    x *= (r/coefficientX);
    y *= (r/coefficientY);

    return {
        "x": x.toFixed(2),
        "y": y.toFixed(2)
    };
}

function setFields(x, y, r) {
    document.getElementById("hiddenForm:xValue").value = x;
    document.getElementById("hiddenForm:yValue").value = y;
    document.getElementById("hiddenForm:rValue").value = r;

    document.getElementById("hiddenForm:phantomButton").click();
}

function drawCircle(context, x, y, radius, color) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x, y, radius, 0, Math.PI*2);
    context.stroke();

    context.fill();
}