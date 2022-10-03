function sendCoordinates() {

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

    $.ajax({
        type: "GET",
        url: "controller",
        data: {
            'x': $('input[name="x"]:checked').val(),
            'y': $('input[name="y"]').val(),
            'r': $('input[name="r"]:checked').val(),
            'mode': 0
        },
        cache: false,
        dataType: "html",
        beforeSend: function () {
            if (!validate(x, y, r)) {
                return false;
            }
        },
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

function createArgs(x, y, r) {
    return "x=" + x + "&y=" + y + "&r=" + r;
}

function handleRequest(response) {
    document.getElementById("receivingData").innerHTML = response;
}