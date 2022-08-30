function sendRequest (method, url) {
    if (!validate()) {
        return;
    }
    let httpRequest = createRequest();

    if (!httpRequest) {
        return;
    }

    httpRequest.onreadystatechange = function() {
        try {
            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {
                    handleRequest(httpRequest);
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
        sendByPost (httpRequest, url, createArgsString());
    }

    else if (method.toLowerCase() == "get") {
        sendByGet (httpRequest, url, createArgsString());
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

function handleRequest (httpRequest) {
    alert(httpRequest.responseText); // поменять на изменение таблицы
}

function createArgsString () {
    let x = document.forms["coords"]["x"].value;
    let y = document.forms["coords"]["y"].value.replace(',','.').replace(' ',"");
    let r = document.forms["coords"]["r"].value;

    return "x="+x+"&y="+y+"&r="+r;
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