$('input[name="coordinatesForm:radius"]').attr("readonly", true); // lock r area

hideAntFields();
hideSpiderFields();

$('input[name="coordinatesForm:pointType"]').on("change", function () {
    console.log(this);
    if (this.defaultValue === "spider" && this.checked) {
        hideAntFields();

        showSpiderFields();
    } else if (this.defaultValue === "ant" && this.checked) {
        hideSpiderFields();

        showAntFields();
    }
});

function hideAntFields() {
    $('div#mustacheLengthField').hide();
    $('input[name="coordinatesForm:mustacheLength"]').prop("disabled", true);

}

function hideSpiderFields() {
    $('div#legCountField').hide();
    $('input[name="coordinatesForm:legCount"]').prop("disabled", true);
}

function showAntFields() {
    $('div#mustacheLengthField').show();
    $('input[name="coordinatesForm:mustacheLength"]').prop("disabled", false);
}

function showSpiderFields() {
    $('div#legCountField').show();
    $('input[name="coordinatesForm:legCount"]').prop("disabled", false);
}

function isLegCountCorrect() {
    let value = $('input[name="coordinatesForm:legCount"]').value;

    if (value % 1 != 0 || value < 0 || value > 8) {
        alert("Количество ног введено некорректно");
        return false;
    }
    return true;
}

function isMustacheLengthCorrect() {
    let value = $('input[name="coordinatesForm:mustacheLength"]').value;

    if (value < 0 || value > 5) {
        alert("Длина усов введена некорректно");
        return false;
    }
    return true;
}