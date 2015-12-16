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
        $('#register-button-response').text('Done:)');
    }

    //$('#register-button').click(function(){
    //    var firstName = $('#firstName').val();
    //    var lastName = $('#lastName').val();
    //    var birthDate = ($('#year').val()+"-"+$('#month').val()+"-"+$('#day').val());
    //    var userName = $('#userName').val();
    //    var password = $('#password').val();
    //    var confirmPassword = $('#confirmPassword').val();
    //
    //    $.ajax({
    //        type: "POST",
    //        url: '/saveContact',
    //        contentType: "application/json; charset=utf-8",
    //        dataType: "json",
    //        data: '{"firstName": "' + firstName + '", "lastName": "' + lastName + '", "birthDate": "' + birthDate + '",' +
    //        ' "userName": "' + userName + '", "password": "' + password + '", "confirmPassword": "' + confirmPassword + '"}',
    //        success: function(data) {
    //            if (data.status === 'OK'){
    //                //displaySuccess()
    //            }else{
    //                //displayError(data.errorMessage);
    //            }
    //        }
    //    });
    //});
});
