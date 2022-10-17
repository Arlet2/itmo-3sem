$('input[name="clearButton"]').on("click", function (e) {
    localStorage.circles = null;
    let canvas = $('canvas.map')[0];

    canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
});