$(document).ready(function () {
    function displayError(message) {
        $('#edit-btn-response').removeClass('bg-success');
        $('#edit-btn-response').addClass('bg-danger');
        $('#edit-btn-response').text(message);
    }

    function displaySuccess() {
        $('#edit-btn-response').removeClass('bg-danger');
        $('#edit-btn-response').addClass('bg-success');
        $('#edit-btn-response').text('Done:)');
    }

    $('#edit-profile-btn').click(function () {
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var birthDate = $('#birthDate').val();

        $.ajax({
            type: "POST",
            url: '/edit/profile',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"firstName": "' + firstName + '", "lastName": "' + lastName + '", "birthDate": "' + birthDate + '"}',
            success: function (data) {
                if (data.status === 'OK') {
                    displaySuccess();
                    $('#h1').text("Добро пожаловать " + data.contact.firstName + "!");
                } else {
                    displayError(data.errorMessage);
                }
            }
        });
    });
});
