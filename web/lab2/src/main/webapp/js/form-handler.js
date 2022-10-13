const TABLE_MODE = 0;

function sendCoordinatesByForm() {

    let x = $('input[name="x"]:checked').val();
    let y = $('input[name="y"]').val();
    let r = $('input[name="r"]:checked').val();

    if (isXEmpty(x)) {
        alert("Выберете координату x");
        return;
    }

    if (isYEmpty(y)) {
        alert("Выберете координату y");
        return;
    }

    if (isREmpty(r)) {
        alert("Выберете радиус");
        return;
    }

    x = clearSpacesAndChangeCommaToPoint(x);
    y = clearSpacesAndChangeCommaToPoint(y);
    r = clearSpacesAndChangeCommaToPoint(r);

    let coordinates = createCoordinates(
        $('input[name="x"]:checked').val(),
        $('input[name="y"]').val(),
        $('input[name="r"]:checked').val()
    );

    ajaxSend(coordinates, TABLE_MODE, setResponseOnTable);
}

function isXEmpty(x) {
    return x === undefined;
}

function isYEmpty(y) {
    return y === undefined;
}

function isREmpty(r) {
    return r === undefined;
}

function clearSpacesAndChangeCommaToPoint(sendingValue) {
    return sendingValue.replace(',', '.').replace(' ', "");
}

function setResponseOnTable(response, coordinates) {
    document.getElementById("receivingData").innerHTML = response;
}