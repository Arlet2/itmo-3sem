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
                    handleRequest(response, coordinates);
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

function createCoordinates(x, y, r) {
    return {
            "x": x,
            "y": y,
            "r": r
    };
}