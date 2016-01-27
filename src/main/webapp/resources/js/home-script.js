$(document).ready(function () {

    $('#saveProfileChangesBtn').click(function () {
        var firstName = $('#firstNameInput').val();
        if(firstName.length==0){
            firstName=$('#firstName').text();
        }
        var lastName = $('#lastNameInput').val();
        if(lastName.length==0){
            lastName=$('#lastName').text();
        }
        var birthDate = $('#birthDateInput').val();
        if(birthDate.length==0){
            birthDate=$('#birthDate').text();
        }
        var newDate = birthDate.substring(6,10)+"-"+birthDate.substring(3,5)+"-"+birthDate.substring(0,2);

        $.ajax({
            type: "POST",
            url: '/edit/profile',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"firstName": "' + firstName + '", "lastName": "' + lastName + '", "birthDate": "' + newDate + '"}',
            success: function (result) {
                if (result.status === 'OK') {
                    $('h1').text("Добро пожаловать " + result.contactDto.firstName + "!");
                    $('#firstName').text(result.contactDto.firstName);
                    $('#lastName').text(result.contactDto.lastName);
                    $('#birthDate').text(birthDate);
                    $('#firstNameInput').val("");
                    $('#lastNameInput').val("");
                    $('#birthDateInput').val("");
                    hideProfileEditFields();
                    $('#alert').addClass("alert-success");
                    $('#alert-message').text("Success!");
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                        $("#alert").hide();
                    });
                } else {
                    $('#alert').addClass("alert-danger");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                        $("#alert").alert('close');
                    });
                }
            }
        });
    });

    $('#saveHobbyBtn').click(function () {
        var hobbyTitle = $('#hobbyTitleInput').val();
        var hobbyDescription = $('#hobbyDescriptionInput').val();

        $.ajax({
            type: "POST",
            url: 'hobbies/saveHobby',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"title": "' + hobbyTitle + '", "description": "' + hobbyDescription + '"}',
            success: function (result) {
                if (result.status === 'OK') {
                    $('#hobbyTitleInput').val("");
                    $('#hobbyDescriptionInput').val("");
                    $('#myHobbyPanelBody').load(document.URL + ' #myHobbyPanelBody');
                    $('#alert').addClass("alert-success");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                        $("#alert").hide();
                    });
                } else {
                    $('#hobbyTitleInput').val("");
                    $('#hobbyDescriptionInput').val("");
                    $('#alert').addClass("alert-danger");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                        $("#alert").alert('close');
                    });
                }
            }
        });
    });

    $('#savePlaceBtn').click(function(){
        var placeTitle = $('#placeTitleInput').val();
        var placeDescription = $('#placeDescriptionInput').val();
        var placeLatitude = $('#placeLatitudeInput').val();
        var placeLongitude = $('#placeLongitudeInput').val();

        $.ajax({
            type: "POST",
            url: 'places/savePlace',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: '{"title": "' + placeTitle + '", "description": "' + placeDescription + '", "latitude": "' + placeLatitude + '", "longitude": "' + placeLongitude + '"}',
            success: function (result) {
                if (result.status === 'OK') {
                    $('#placeTitleInput').val("");
                    $('#placeDescriptionInput').val("");
                    $('#placeLatitudeInput').val("");
                    $('#placeLongitudeInput').val("");
                    $('#myPlacePanelBody').load(document.URL + ' #myPlacePanelBody');
                    $('#alert').addClass("alert-success");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                        $("#alert").hide();
                    });
                } else {
                    $('#hobbyTitleInput').val("");
                    $('#hobbyDescriptionInput').val("");
                    $('#alert').addClass("alert-danger");
                    $('#alert-message').text(result.message);
                    $('#alert').alert();
                    $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                        $("#alert").alert('close');
                    });
                }
            }
        });
    });

    $('#alert').hide();

});

function showTooltip(elemenId){
    var id = "#"+elemenId;
    $(id).tooltip('show');
}

function hideTooltip(elemenId){
    var id = "#"+elemenId;
    $(id).tooltip('hide');
}

function showProfileEditFields(){
    $('#editFirtNameInput').removeClass('hideElement');
    $('#editLastNameInput').removeClass('hideElement');
    $('#editBirthDateInput').removeClass('hideElement');
    $('#editFirtNameInput').addClass('showElement');
    $('#editLastNameInput').addClass('showElement');
    $('#editBirthDateInput').addClass('showElement');

    $('#saveProfileChangesBtn').removeClass('hideElement');
    $('#saveProfileChangesBtn').addClass('showElement');
    $('#edit-profile-btn').removeClass('showElement')
    $('#edit-profile-btn').addClass('hideElement');
}

function hideProfileEditFields(){
    $('#editFirtNameInput').removeClass('showElement');
    $('#editLastNameInput').removeClass('showElement');
    $('#editBirthDateInput').removeClass('showElement');
    $('#editFirtNameInput').addClass('hideElement');
    $('#editLastNameInput').addClass('hideElement');
    $('#editBirthDateInput').addClass('hideElement');

    $('#saveProfileChangesBtn').removeClass('showElement');
    $('#saveProfileChangesBtn').addClass('hideElement');
    $('#edit-profile-btn').removeClass('hideElement')
    $('#edit-profile-btn').addClass('showElement');
}

function showResponsePost(postId){
    var id = "#"+postId+"responseArea";
    $(id).removeClass('hideElement');
    $(id).addClass('showElement');
}

function hideResponsePost(postId){
    var id = "#"+postId+"responseArea";
    $(id).removeClass('showElement');
    $(id).addClass('hideElement');
}

function deletePost(postId){
    var url = "/posts/" + postId + "/delete/";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK') {
                $('#postArea').load(document.URL + ' #postArea');
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

//function sendResponsePost(postId){
//    var id = "#"+postId+"textArea";
//    var url = "/posts/" + postId + "/addResponse/";
//    var message = $(id).val().replace(/\s+/g,' ');
//
//    $.ajax({
//        type: "POST",
//        url: url,
//        contentType: "application/json; charset=utf-8",
//        dataType: "json",
//        data: '{"message": "' + message + '"}',
//        success: function (result){
//            $(id).val("");
//            $('#comments').load(document.URL + ' #comments');
//            hideResponsePost(postId);
//        }
//    });
//
//}

function invokeMessage(friendId) {
    var url = "/friends/" + friendId + "/invokeMessage";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            invokeModalMessage(result.contactDto, result.renderedHtml);
        }
    });
}

function invokeModalMessage(friend, message) {
    var friendName = " " + friend.firstName + " " + friend.lastName;
    $('#modalBodyLabel').text(friendName);
    $('#modalMessageFriendId').text(friend.id);
    $('#messagesInModal').html(message);
    $('#modalMessage').modal('show');
}

function sendMessage(){
    var message = $('#messageArea').val().replace(/\s+/g,' ');
    var friendId = $('#modalMessageFriendId').text();
    var url = "/friends/" + friendId + "/sendMessage/";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"message": "' + message + '"}',
        success: function (result){
                $('#messageArea').val("");
                $('#messagesInModal').html(result.message);
        }
    });
}

function invokePost(friendId){
    var url = "/friends/" + friendId + "/invokePost";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            invokeModalPost(result.contactDto);
        }
    });
}

function invokeModalPost(friend) {
    $('#modalPostBodyLabel').text(" " + friend.firstName + " " + friend.lastName);
    $('#modalPostFriendId').text(friend.id);
    $('#modalPost').modal('show');
}

function sendPost(){
    var message = $('#postTextArea').val();
    var friendId = $('#modalPostFriendId').text();
    var url = "/friends/" + friendId + "/sendPost/";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"message": "' + message + '"}',
        success: function (result) {
            if(result.status==='OK'){
                $('#postTextArea').val("");
            }else {
                $('#postTextArea').val("");
                $('#modalPost').modal('hide');
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function removeFriend(friendId, element) {
    var url = "/friends/" + friendId + "/remove/";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK') {
                $(element).remove();
                $('#alert').addClass("alert-success");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").hide();
                });
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function editHobby(hobbyId) {
    var url = "/hobbies/" + hobbyId + "/edit";
    var title = $('#hobbyTitle').val();
    var description = $('#hobbyDescription').val();

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"title": "' + title + '", "description": "' + description + '"}',
        success: function (result) {
            if (result.status === 'OK') {
                $('#hobbyTitle').text(result.hobby.title);
                $('#hobbyDescription').text(result.hobby.description);
                $('#alert').addClass("alert-success");
                $('#alert-message').text("Success! Hobby \"" + result.hobby.title + "\" updated:)");
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").hide();
                });
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function removeHobby(hobbyId, element) {
    var url = "/hobbies/" + hobbyId + "/remove";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK') {
                $(element).remove();
                $('#alert').addClass("alert-success");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").hide();
                });
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function editPlace(placeId) {
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
        data: '{"title": "' + title + '", "description": "' + description + '", "latitude": "' + latitude + '", "longitude": "' + longitude + '"}',
        success: function (result) {
            if (result.status === 'OK') {
                $('#placeTitle').text(result.place.title);
                $('#placeDescription').text(result.place.description);
                $('#placeLatitude').text(result.place.description);
                $('#placeLongitude').text(result.place.description);
                $('#alert').addClass("alert-success");
                $('#alert-message').text("Success! Place \"" + result.place.title + "\" updated:)");
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").hide();
                });
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function removePlace(placeId, element) {
    var url = "/places/" + placeId + "/remove";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK') {
                $(element).remove();
                $('#alert').addClass("alert-success");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").hide();
                });
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function addFriend(friendId){
    var url = "/friends/" + friendId + "/add/";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK') {
                $('#myFriendBodyPanel').load(document.URL + ' #myFriendBodyPanel');
                $('#alert').addClass("alert-success");
                $('#alert-message').text("Success! " + result.contactDto.firstName + " " + result.contactDto.lastName + " added to friend list:)");
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").hide();
                });
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function showPeople(){
    var url = "people/show";
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK') {
                $('#myPeoplePanelBody').load(document.URL + ' #myPeoplePanelBody');
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function filter(){
    var url = "/people/filter/add";
    var selectNumber = $('#selectFilter').val();
    var textFilter = $('#filterInput').val();

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"selectNumber": "' + selectNumber + '", "textFilter": "' + textFilter + '"}',
        success: function (result) {
            if (result.status === 'OK') {
                $('#myPeoplePanelBody').load(document.URL + ' #myPeoplePanelBody');
                //$('#alert').addClass("alert-success");
                //$('#alert-message').text("Success!");
                //$('#alert').alert();
                //$("#alert").fadeTo(5000, 500).slideUp(500, function () {
                //    $("#alert").hide();
                //});
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}

function removeFilter(){
    var url = "/people/filter/remove";

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {
            if (result.status === 'OK') {
                $('#myPeoplePanelBody').load(document.URL + ' #myPeoplePanelBody');
            } else {
                $('#alert').addClass("alert-danger");
                $('#alert-message').text(result.message);
                $('#alert').alert();
                $("#alert").fadeTo(5000, 500).slideUp(500, function () {
                    $("#alert").alert('close');
                });
            }
        }
    });
}