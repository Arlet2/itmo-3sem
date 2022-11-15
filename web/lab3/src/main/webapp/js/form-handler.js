$('input[name="coordinatesForm:radius"]').attr("readonly", true);
function isRCorrect() {
    let r = $('input[name="coordinatesForm:radius"]').val();

    return 1 <= r && r <= 4 && r%0.25==0;
}