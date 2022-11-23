$('input[name="coordinatesForm:radius"]').attr("readonly", true); // lock r area

hide_ant_fields();
hide_spider_fields();

$('input[name="coordinatesForm:pointType"]').on("change", function () {
    console.log(this);
    if (this.defaultValue === "spider" && this.checked) {
        hide_ant_fields();

        show_spider_fields();
    } else if (this.defaultValue === "ant" && this.checked) {
        hide_spider_fields();

        show_ant_fields();
    }
});

function hide_ant_fields() {
    $('div#mustacheLengthField').hide();
    $('input[name="coordinatesForm:mustacheLength"]').prop("disabled", true);

}

function hide_spider_fields() {
    $('div#legCountField').hide();
    $('input[name="coordinatesForm:legCount"]').prop("disabled", true);
}

function show_ant_fields() {
    $('div#mustacheLengthField').show();
    $('input[name="coordinatesForm:mustacheLength"]').prop("disabled", false);
}

function show_spider_fields() {
    $('div#legCountField').show();
    $('input[name="coordinatesForm:legCount"]').prop("disabled", false);
}