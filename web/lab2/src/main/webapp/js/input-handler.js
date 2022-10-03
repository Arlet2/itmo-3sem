$('input[name="x"]').on('change', function() {
    $('input[name="x"]').prop('checked', false);
    $(this).prop('checked', true);
});

$('input[name="r"]').on('change', function() {
    $('input[name="r"]').prop('checked', false);
    $(this).prop('checked', true);
});