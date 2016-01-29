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
        $('#register-button-response').text('Registration successful:)');
    }

    $('#register-button').click(function(){
        var first_name = $('#first-name').val();
        var last_name = $('#last-name').val();
        var birth_date = ($('#year').val()+"-"+$('#month').val()+"-"+$('#day').val());
        var login = $('#login').val();
        var password = $('#password').val();

        $.ajax({
            type: "POST",
            url: '/saveContact',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"firstName": "' + first_name + '", "lastName": "' + last_name + '", "birthDate": "' + birth_date + '", "login": "' + login + '", "password": "' + password + '"}',
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

function toggleTab(){
    $('#reg').addClass('active');
    $('#aut').removeClass('active');
    $('#register-panel').addClass('active');
    $('#author-panel').removeClass('active');
}
