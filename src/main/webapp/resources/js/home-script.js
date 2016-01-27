$(document).ready(function () {

    $('#edit-profile-btn').click(function () {
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var birthDate = $('#birthDate').val();
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
                    $('#birthDate').text(result.contactDto.birthDate);
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

function logout(){
    $.ajax({
        type: "GET",
        url: "/logout",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(result){
        }
    });
}

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

function buildModal(friend, messages){
    for (var i = 0; i < messages.length; i++) {
        var message = messages[i];

        if (message.from.id === friend.id) {
            var divPopoverHome = document.createElement('div');
            divPopoverHome.className("popover-home");

            var divPopoverRightMessage = document.createElement('div');
            divPopoverRightMessage.className("popover right message");

            var divArrow = document.createElement('div');
            divArrow.className("arrow");

            var h3PopoverTitle = document.createElement('h3');
            h3PopoverTitle.style("background-color: #337AB7; color: #ffffff; text-align: right");
            h3PopoverTitle.className("popover-title");

            var innerSpan = document.createElement('span');
            innerSpan.innerHTML(message.date);

            h3PopoverTitle.appendChild(innerSpan);
            divPopoverRightMessage.appendChild(divArrow);
            divPopoverRightMessage.appendChild(h3PopoverTitle);

            var divPopoverContent = document.createElement('div');
            divPopoverContent.style("background-color:#EFEFEF");
            divPopoverContent.className("popover-content");
            divPopoverContent.innerHTML(message.content);

            divPopoverRightMessage.appendChild(divPopoverContent);
            divPopoverHome.appendChild(divPopoverRightMessage);

            var parentElem = document.getElementById("messagesInModal");
            parentElem.appendChild(divPopoverHome);
        } else {
            var divPopoverHome = document.createElement('div');
            divPopoverHome.className("popover-home");

            var divPopoverLeftMessage = document.createElement('div');
            divPopoverLeftMessage.className("popover left message");

            var divArrow = document.createElement('div');
            divArrow.className("arrow");

            var h3PopoverTitle = document.createElement('h3');
            h3PopoverTitle.style("background-color: #5CB85C/*; color: #ffffff; text-align: right*/");
            h3PopoverTitle.className("popover-title");

            var innerSpan = document.createElement('span');
            innerSpan.innerHTML(message.date);

            h3PopoverTitle.appendChild(innerSpan);
            divPopoverLeftMessage.appendChild(divArrow);
            divPopoverLeftMessage.appendChild(h3PopoverTitle);

            var divPopoverContent = document.createElement('div');
            divPopoverContent.style("background-color:#EFEFEF");
            divPopoverContent.className("popover-content");
            divPopoverContent.innerHTML(message.content);

            divPopoverLeftMessage.appendChild(divPopoverContent);
            divPopoverHome.appendChild(divPopoverLeftMessage);

            var parentElem = document.getElementById("messagesInModal");
            parentElem.appendChild(divPopoverHome);
        }
    }
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