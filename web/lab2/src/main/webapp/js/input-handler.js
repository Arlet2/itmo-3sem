$('input[name="x"]').on('change', function() {
    let isChecked = $(this).prop('checked');
    $('input[name="x"]').prop('checked', false);
    if (isChecked)
        $(this).prop('checked', true);
});

$('input[name="r"]').on('change', function() {
    let isChecked = $(this).prop('checked');
    $('input[name="r"]').prop('checked', false);
    if (isChecked)
        $(this).prop('checked', true);
});