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
                    $('h1').text("Добро пожаловать " + result.contactDto.firstName + "!");
                    displaySuccess()
                }else{
                    displayError(data.errorMessage);
                }
            }
        });
    });

    $('#saveHobbyBtn').click(function(){
        var hobbyTitle = $('#hobbyTitleInput').val();
        var hobbyDescription = $('#hobbyDescriptionInput').val();

        $.ajax({
            type: "POST",
            url: '/saveHobby',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"title": "' + hobbyTitle +'", "description": "' + hobbyDescription + '"}',
            success: function (result) {
                if (result.status === 'OK'){
                    displaySuccess()
                }else{
                    displayError(data.errorMessage);
                }
            }
        });
    });

});