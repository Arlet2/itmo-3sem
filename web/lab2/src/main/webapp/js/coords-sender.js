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

    ajaxSend(coordinates, 0, setResponseOnTable);
}

function sendCoordinatesByMap(x, y) {
    let coordinates = createCoordinates(x, y, $('input[name="r"]:checked').val());
    ajaxSend(coordinates, 1, setResponseOnMap);
}

function createCoordinates(x, y, r) {
    return {
            "x": x,
            "y", y,
            "r", r
    };
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

function ajaxSend(coordinates, mode, handleRequest) {
    $.ajax({
            type: "GET",
            url: "controller",
            data: {
                'x': coordinates.x,
                'y': coordinates.y,
                'r': coordinates.r,
                'mode': mode
            },
            cache: false,
            dataType: "html",
            success: function (response) {
                try {
                    handleRequest(response);
                } catch (e) {
                    alert("Проблемы с ответом от сервера: " + e);
                }
            },
            statusCode: {
                404: function() {
                    alert("File not found.");
                },
                410: function() {
                    alert("Content was removed.");
                },
                500: function() {
                    alert("Server error");
                },
                502: function() {
                    alert("Bad gateway");
                }
            }
        });
}

function setResponseOnTable(response) {
    document.getElementById("receivingData").innerHTML = response;
}

function setResponseOnMap(response) {

}