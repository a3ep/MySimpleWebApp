$(document).ready(function() {

    function displayError(message) {
        $('#response').removeClass('bg-success');
        $('#response').addClass('bg-danger');
        $('#response').text(message);
    }

    function displaySuccess() {
        $('#response').removeClass('bg-danger');
        $('#response').addClass('bg-success');
        $('#response').text('Done:)');
    }

    $('#edit-profile-btn').click(function () {
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var birthDate = $('#birthDate').val();

        $.ajax({
            type: "POST",
            url: '/edit/profile',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"firstName": "' + firstName +'", "lastName": "' + lastName + '", "birthDate": "' + birthDate + '"}',
            success: function (result) {
                if (result.status === 'OK'){
                    $('h1').text("Добро пожаловать " + result.cft.firstName + "!");
                    displaySuccess()
                }else{
                    displayError(data.errorMessage);
                }
            }
        });
    });

});