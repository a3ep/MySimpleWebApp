/**
 * Created by Azeral on 23.11.2015.
 */
$(document).ready(function() {

    function displayError(message) {
        $('#register-button-response').removeClass('bg-success');
        $('#register-button-response').addClass('bg-danger');
        $('#register-button-response').text(message);
    }

    function displaySuccess() {
        $('#register-button-response').removeClass('bg-danger');
        $('#register-button-response').addClass('bg-success');
        $('#register-button-response').text('Contact saved successful:)');
    }

    $('#register-button').click(function(){
        var first_name = $('#first-name').text();
        var last_name = $('#last-name').text();
        var birth_date = $('#birth-date').text();

        $.ajax({
            type: "POST",
            url: '/contact',
            contextType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"firstName": "' + first_name + '", "lastName": "' + last_name + '", "birthDate": "' + birth_date + '"}',
            success: function(data) {
                if (data.status === 'OK'){
                    displaySuccess()
                }else{
                    displayError(data.errorMessage);
                }
            }
        });
    });

});