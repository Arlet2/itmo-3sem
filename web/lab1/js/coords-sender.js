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
            try {
                if (httpRequest.readyState == 4) {
                    if (httpRequest.status == 200) {
                        handleRequest(response);
                    }
    
                    else if (httpRequest.status == 0) {
                        alert("Нет соединения с сервером");
                    }
    
                    else {
                        console.print("ERROR: " + httpRequest.status);
                        alert("Нет соединения с сервером");
                    }
                }
            } catch (e) {
                alert("Проблемы с ответом от сервера: " + e);
            }
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