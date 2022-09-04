function sendCoordinates () {
    let x = document.forms["coords"]["x"].value;
    let y = document.forms["coords"]["y"].value.replace(',','.').replace(' ',"");
    let r = document.forms["coords"]["r"].value;

    if (!validate(x, y, r)) {
        return;
    }

    sendRequest("POST", "php/table.php", createArgs(x, y, r), handleRequest);
}

function createArgs (x, y, r) {
    return "x="+x+"&y="+y+"&r="+r;
}

function handleRequest (httpRequest) {
    document.getElementById("receivingData").innerHTML = httpRequest.responseText;
}