function sendCoordinates() {
    //let x = document.forms["coords"]["x"].value;
    //let y = document.forms["coords"]["y"].value.replace(',','.').replace(' ',"");
    //let r = document.forms["coords"]["r"].value;

    let x = clearSpacesAndChangeCommaToPoint($('input[name="x"]:checked').val());
    let y = clearSpacesAndChangeCommaToPoint($('input[name="y"]').val());
    let r = clearSpacesAndChangeCommaToPoint($('select[name="r"]').val());

    //if (!validate(x, y, r)) {
    //    return;
    //}

    //sendRequest("POST", "php/table.php", createArgs(x, y, r), handleRequest);
    $.ajax({
        type: "POST",
        url: "php/table.php",
        data: {
            'x': $('input[name="x"]:checked').val(),
            'y': $('input[name="y"]').val(),
            'r': $('select[name="r"]').val()
        },
        cache: false,
        dataType: "html",
        beforeSend: function () {
            if (!validate(x, y, r)) {
                return false;
            }
        },
        success: function (response) {
            handleRequest(response);
        }
    });
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