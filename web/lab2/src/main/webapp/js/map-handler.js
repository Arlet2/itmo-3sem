const coefficientX = $('canvas.map')[0].width/2.32;
const coefficientY = $('canvas.map')[0].height/2.32;
const circleSize = $('canvas.map')[0].width/100;

loadPoints();

function loadPoints() {
    let canvas = $('canvas.map')[0];
    let ctx = canvas.getContext('2d');

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    let points = parseHTML();

    if (points.length == 0)
        return;

    console.log(points[0].x, points[0].y, points[0].r, points[0].color);

    for (let point of points) {
        drawCircle(ctx, point.x*coefficientX/point.r+canvas.width/2,
                        -point.y*coefficientY/point.r+canvas.height/2,
                        circleSize, point.color);
    }

}

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
        "x": x.toFixed(2),
        "y": y.toFixed(2)
    };
}

function sendCoordinatesByMap(x, y) {
    let coordinates = createCoordinates(x, y, $('input[name="r"]:checked').val());
    ajaxSend(coordinates, setResponseOnMap);
}

function setResponseOnMap(response, coordinates) {
    coordinates.x *= (coefficientX/coordinates.r);
    coordinates.y *= -(coefficientY/coordinates.r);

    let color;

    setResponseOnTable(response, coordinates);

    loadPoints();
}

function drawCircle(context, x, y, radius, color) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x, y, radius, 0, Math.PI*2);
    context.stroke();

    context.fill();
}

function parseHTML() {
    let coordinates = $('td.dataRow.coordinates');
    let hitStatuses = $('td.dataRow.hitStatus');

    let points = [];

    for (let i = 0; i < coordinates.length;i++) {
        points.push(createPoint(coordinates[i].innerHTML, hitStatuses[i].innerHTML));
    }

    return points;
}

function createPoint(coordinatesText, hitStatusText) {
    let color;
    if (hitStatusText == "hit!")
        color = "green";
    else
        color = "red";

    return {
            "x": coordinatesText.match(/(?<=X: )[-\d.*,]*/)[0],
            "y": coordinatesText.match(/(?<=Y: )[-\d.*,]*/)[0],
            "r": coordinatesText.match(/(?<=R: )[\d.*,]*/)[0],
            "color": color
    };
}

