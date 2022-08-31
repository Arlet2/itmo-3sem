function sendRequest (method, url, args, handlingFunction) {
    let httpRequest = createRequest();

    if (!httpRequest) {
        return;
    }

    httpRequest.onreadystatechange = function() {
        try {
            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {
                    handlingFunction(httpRequest);
                }
                else {
                    console.print("ERROR: "+httpRequest.status);
                    alert("Нет соединения с сервером");
                }
            }
        } catch (e) {
            alert("Нет соединения с сервером");
        }
    }

    if (method.toLowerCase() == "post") {
        sendByPost (httpRequest, url, args);
    }

    else if (method.toLowerCase() == "get") {
        sendByGet (httpRequest, url, args);
    }

    else {
        throw new Error("Incorrect method");
    }

}

function createRequest () {

    let request;

    if (window.XMLHttpRequest) {
        // Cool browsers
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        // Internet explorer
        try {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (CatchException) {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }

    if (!request) {
        throw new Error("AJAX не поддерживается");
    }

    return request;
}

function sendByPost (httpRequest, url, args) {
    httpRequest.open("POST", url);
    httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
    httpRequest.send(args);
}

function sendByGet (httpRequest, url, args) {
    httpRequest.open("GET", url+"?"+args);
    httpRequest.send(null);
}