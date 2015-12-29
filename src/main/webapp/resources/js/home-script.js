$(document).ready(function() {

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
                    $('#firstName').text(result.contactDto.firstName);
                    $('#lastName').text(result.contactDto.lastName);
                    $('#birthDate').text(result.contactDto.birthDate);
                    $('#alert').addClass("alert-success");
                    $('#alert-message').text("Success!");
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                        $("#alert").hide();
                    });
                }else{
                    $('#alert').addClass("alert-danger");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                        $("#alert").alert('close');
                    });
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
                    $('#alert').addClass("alert-success");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                        $("#alert").hide();
                    });
                }else{
                    $('#alert').addClass("alert-danger");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                        $("#alert").alert('close');
                    });
                }
            }
        });
    });
    $('#alert').hide();

});

function removeFriend(friendId, element){
    var url ="/friends/" + friendId +"/remove/";

    $.ajax({
        type: "POST",
        url:url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK'){
                $(element).remove();
                $('#alert').addClass("alert-success");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").hide();
                });
            }else{
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function editHobby(hobbyId){
    var url = "/hobbies/" + hobbyId + "/edit";
    var title = $('#hobbyTitle').val();
    var description = $('#hobbyDescription').val();

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"title": "' + title +'", "description": "' + description + '"}',
        success: function (result) {
            if (result.status === 'OK'){
                $('#hobbyTitle').text(result.hobby.title);
                $('#hobbyDescription').text(result.hobby.description);
                $('#alert').addClass("alert-success");
                $('#alert-message').text("Success! Hobby \"" + result.hobby.title + "\" updated:)");
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").hide();
                });
            }else{
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").alert('close');
                });
            }
        }
    });
}


function removeHobby(hobbyId, element){
    var url = "/hobbies/" + hobbyId + "/remove";

    $.ajax({
        type: "POST",
        url:url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK'){
                $(element).remove();
                $('#alert').addClass("alert-success");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").hide();
                });
            }else{
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function editPlace(placeId){
    var url = "/places/" + placeId + "/edit";
    var title = $('#placeTitle').val();
    var description = $('#placeDescription').val();
    var latitude = $('#placeLatitude').val();
    var longitude = $('#placeLongitude').val();

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"title": "' + title +'", "description": "' + description + '", "latitude": "' + latitude + '", "longitude": "' + longitude + '"}',
        success: function (result) {
            if (result.status === 'OK'){
                $('#placeTitle').text(result.place.title);
                $('#placeDescription').text(result.place.description);
                $('#placeLatitude').text(result.place.description);
                $('#placeLongitude').text(result.place.description);
                $('#alert').addClass("alert-success");
                $('#alert-message').text("Success! Place \"" + result.place.title + "\" updated:)");
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").hide();
                });
            }else{
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").alert('close');
                });
            }
        }
    });
}
function removePlace(placeId, element){
    var url = "/places/" + placeId + "/remove";

    $.ajax({
        type: "POST",
        url:url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK'){
                $(element).remove();
                $('#alert').addClass("alert-success");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").hide();
                });
            }else{
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function() {
                    $("#alert").alert('close');
                });
            }
        }
    });
}