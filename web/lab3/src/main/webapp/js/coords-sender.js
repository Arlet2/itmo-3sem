function ajaxSend(mode, handleRequest) {
    $.ajax({
        type: "GET",
        url: "map-handler",
        data: {
            "mode": mode
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
            404: function () {
                alert("File not found.");
            },
            410: function () {
                alert("Content was removed.");
            },
            500: function () {
                alert("Server error");
            },
            502: function () {
                alert("Bad gateway");
            }
        }
    });

}