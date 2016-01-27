<%--
  Created by IntelliJ IDEA.
  User: AzeraL
  Date: 03.12.2015
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta charset="utf-8">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.css">
    <link rel="stylesheet" href="../../resources/css/home.css">
    <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
    <script type="text/javascript" src="../../resources/js/home-script.js"></script>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
</head>
<body>
<div class="container-home">
    <div class="header-home text-center">
        <div id="alert" class="alert alert-dismissible fade in" role="alert" style="margin: 0">
            <span id="alert-message" style="text-align:center; font-weight: bold"></span>
        </div>
        <form:form class="form-horizontal" method="get" action="/logout">
            <button id="logoutBtn" style="float: right" class="btn btn-danger" <%--onclick="logout()"--%>
                    onmouseover="showTooltip(this.id)" onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                    data-placement="bottom"
                    type="submit" data-original-title="Выход"><span class="glyphicon glyphicon-log-out"
                                                                    aria-hidden="true"></span></button>
        </form:form>
        <h1 id="h1" datatype="utf-8"><strong>Добро пожаловать, ${user.firstName}!</strong></h1>

    </div>

    <%--<div class="row">--%>
    <%--<div class="input-group search-block">--%>
    <%--<input type="text" class="form-control" placeholder="Искать...">--%>
    <%--<span class="input-group-btn">--%>
    <%--<button class="btn btn-primary btn-margin-and-height" type="button">Найти</button>--%>
    <%--</span>--%>
    <%--</div>--%>
    <%--</div>--%>

    <div class="mainRow">
        <div class="tabbable tabs-left">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#user-info-panel" role="tab"
                                                          data-toggle="tab"><strong>Профиль</strong></a></li>
                <li role=presentation><a href="#friends-panel" role="tab"
                                                              data-toggle="tab"><strong>Друзья</strong></a></li>
                <li role=presentation><a href="#hobbies-panel" role="tab"
                                                              data-toggle="tab"><strong>Хобби</strong></a></li>
                <li role=presentation><a href="#places-panel" role="tab"
                                                              data-toggle="tab"><strong>Места</strong></a></li>
                <li role="presentation"><a href="#people-panel" role="tab" data-toggle="tab" onclick="showPeople()"><strong>Люди</strong></a></li>
            </ul>

            <div id="content" class="tab-content">
                <%----------------MODAL MESSAGE---------------%>
                <div id="modalMessage" class="modal fade in"
                     aria-labelledby="modalMessageLabel" role="dialog" tabindex="-1">
                    <div class="modal-dialog" role="document"
                         style="width: 700px; margin-top: 110px">
                        <div class="modal-content">
                            <div class="modal-header"
                                 style="background-color: #337AB7; color: #ffffff">
                                <button class="close" aria-label="Close"
                                        data-dismiss="modal" type="button">
                                    <span aria-hidden="false">×</span>
                                </button>

                                <h4 id="modalMessageLabel" class="modal-title"
                                    style="text-align: center"><span
                                        class="glyphicon glyphicon-envelope"
                                        style="font-size: 30px"> Чат</span></h4>
                            </div>
                            <div class="modal-body">
                                <label id="modalMessageBodyLabel" class="label label-primary"
                                       style="margin-bottom:10px"
                                        >Кому -> <span
                                        class="glyphicon glyphicon-user"></span> <span
                                        id="modalBodyLabel"></span> <span id="modalMessageFriendId"
                                                                          style="visibility:hidden"></span>
                                </label>

                                <div class="panel panel-primary">
                                    <div class="panel-body" style="padding: 0px; max-height: 300px; overflow: auto">
                                        <div id="messagesInModal">
                                            <c:forEach items="${messages}" var="message">
                                                <div class="popover-home">
                                                    <div class="popover right message">
                                                        <div class="arrow"></div>
                                                        <h3 style="background-color: #337AB7; color: #ffffff; text-align: right"
                                                            class="popover-title">${message.from.firstName} ${message.from.lastName}<span> ${message.date}</span>
                                                        </h3>

                                                        <div style="background-color:#EFEFEF"
                                                             class="popover-content">${message.content}</div>
                                                    </div>
                                                </div>
                                                <%--<div class="popover-home">--%>
                                                <%--<div class="popover left message">--%>
                                                <%--<div class="arrow"></div>--%>
                                                <%--<h3 style="background-color: #5CB85C; color: #ffffff; text-align: right"--%>
                                                <%--class="popover-title">--%>
                                                <%--<span>00:00</span></h3>--%>

                                                <%--<div style="background-color:#EFEFEF"--%>
                                                <%--class="popover-content">--%>
                                                <%--Сообщение сообщение сообщение сообщение--%>
                                                <%--сообщение сообщение сообщение--%>
                                                <%--сообщение--%>
                                                <%--</div>--%>
                                                <%--</div>--%>
                                                <%--</div>--%>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer" style="background-color: #337AB7">
                                <div style="width: 135px">
                                    <label class="label label-default"
                                           style="margin-bottom:10px; text-align: left"
                                           for="messageArea">Введите сообщение</label><br>
                                </div>
                                                                <textarea class="form-control"
                                                                          style="width:660px;max-width: 660px; margin-bottom: 20px"
                                                                          id=messageArea
                                                                          name="message"></textarea>
                                <button id="sendMessage" class="btn btn-success" type="button"
                                        onclick="sendMessage()">Отправить
                                </button>
                                <button class="btn btn-danger" data-dismiss="modal"
                                        type="button">Закрыть
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <%------------------MODAL POST----------------%>
                <div id="modalPost" class="modal fade in"
                     aria-labelledby="modalPostLabel" role="dialog" tabindex="-1">
                    <div class="modal-dialog" role="document"
                         style="width: 700px; margin-top: 110px">
                        <div class="modal-content">
                            <div class="modal-header"
                                 style="background-color: #337AB7; color: #ffffff">
                                <button class="close" aria-label="Close"
                                        data-dismiss="modal" type="button">
                                    <span aria-hidden="false">×</span>
                                </button>

                                <h4 id="modalPostLabel" class="modal-title"
                                    style="text-align: center"><span
                                        class="glyphicon glyphicon-comment"
                                        style="font-size: 30px"> Стена</span></h4>
                            </div>
                            <div class="modal-body">
                                <div style="margin-bottom: 10px">
                                    <label class="label label-primary">Кому -> <span
                                            class="glyphicon glyphicon-user"></span> <span
                                            id="modalPostBodyLabel"></span> <span id="modalPostFriendId"
                                                                                  style="visibility:hidden"></span>
                                    </label>
                                </div>


                                <div style="width: 135px">
                                    <label class="label label-default"
                                           style="margin-bottom:10px; text-align: left"
                                           for="postTextArea">Введите сообщение</label><br>
                                </div>
                                                                <textarea class="form-control"
                                                                          style="width:660px;max-width: 660px; margin-bottom: 20px"
                                                                          id="postTextArea" name="post"></textarea>
                            </div>
                            <div class="modal-footer" style="background-color: #337AB7">
                                <button class="btn btn-success" type="button" onclick="sendPost()">Отправить
                                </button>
                                <button class="btn btn-danger" data-dismiss="modal"
                                        type="button">Закрыть
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--TAB USER-PROFILE--%>
                <div role="tabpanel" class="tab-pane active" id="user-info-panel">
                    <div class="row tab-home" style="margin-left: 100px">
                        <div id="profilePhoto">
                            <img src="${user.photo}" alt="Фото" class="img-rounded" style="width: 150px">
                        </div>
                        <div id="profileChangeBtn">
                            <button id="edit-profile-btn" class="btn btn-primary" onmouseover="showTooltip(this.id)"
                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip" data-placement="bottom"
                                    type="button" data-original-title="Редактировать профиль"><span
                                    class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                        </div>
                        <div id="profileInfo">
                            <table class="table table-hover-my">
                                <tr>
                                    <td id="profileFirstCol" class="fontBold">Имя</td>
                                    <td id="profileLastCol">
                                        <input id="firstName"
                                               style="text-align: center; border: none; background-color: transparent"
                                               value="${user.firstName}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fontBold">Фамилия</td>
                                    <td>
                                        <input id="lastName"
                                               style="text-align: center; border: none; background-color: transparent"
                                               value="${user.lastName}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fontBold">День рождения</td>
                                    <td>
                                        <input id="birthDate"
                                               style="text-align: center; border: none; background-color: transparent"
                                               value="<fmt:formatDate value="${user.birthDate}" pattern="dd.MM.yyyy"/>"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div id="postRow" class="row">
                        <div id="postArea">
                            <c:forEach items="${user.posts}" var="post">
                                <c:if test="${post.contactFrom.id==user.id}">
                                    <div class="popover-home">
                                        <div class="popover left postMessage">
                                            <div class="arrow"></div>
                                            <h3 style="background-color: #D9D9D9; color: #333333; height: 30px"
                                                class="popover-title">
                                                <span style="float: left">${post.contactFrom.firstName} ${post.contactFrom.lastName}</span>
                                                <span style="float:right"><fmt:formatDate value="${post.date}"
                                                                                          pattern="HH:mm"/></span>
                                            </h3>

                                            <div style="background-color:#EFEFEF" class="popover-content">
                                                    ${post.content}
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${post.contactFrom.id!=user.id}">
                                    <div class="popover-home">
                                        <div class="popover right postMessage">
                                            <div class="arrow"></div>
                                            <h3 style="background-color: #337AB7; color: #ffffff; height: 30px"
                                                class="popover-title">
                                                <span style="float: left">${post.contactFrom.firstName} ${post.contactFrom.lastName}</span>
                                                <span style="float:right"><fmt:formatDate value="${post.date}"
                                                                                          pattern="HH:mm"/></span>
                                            </h3>

                                            <div style="background-color:#EFEFEF" class="popover-content">
                                                    ${post.content}
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <%--TAB FRIENDS--%>
                <div role="tabpanel" class="tab-pane fade in" id="friends-panel">
                    <div class="row tab-home">
                        <div class="panel panel-primary">
                            <div class="panel-heading" role="tab" id="friendHeading">
                                <h4 class="panel-title">
                                    <a role="button" data-toggle="collapse"
                                       href="#friendCollapse"
                                       aria-expanded="false" aria-controls="friendCollapse">
                                        <strong>Мои друзья</strong>
                                    </a>
                                </h4>
                            </div>
                            <div id="friendCollapse" class="panel-collapse collapse in" role="tabpanel"
                                 aria-labelledby="friendHeading" aria-expanded="true" style="">
                                <div id="myFriendBodyPanel" class="panel-body">
                                    <div class="table-home">
                                        <table class="table table-hover-my">
                                            <thead>
                                            <tr>
                                                <th id="friendPhotoTh">Фото</th>
                                                <th id="friendLastNameTh">Фамилия</th>
                                                <th id="friendFirstNameTh">Имя</th>
                                                <th id="friendBthTh"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${user.friendList}" var="friend">
                                                <tr>
                                                    <td id="friend-photo"
                                                        style="text-align:center; vertical-align:middle"><img
                                                            src="${friend.photo}" alt="../img/no-photo.png"
                                                            class="img-rounded"
                                                            height="30"></td>
                                                    <td id="friend-last-name"
                                                        style="text-align:center; vertical-align:middle">${friend.lastName}</td>
                                                    <td id="friend-first-name"
                                                        style="text-align:center; vertical-align:middle">${friend.firstName}</td>
                                                    <td id="friendCellBtn">
                                                        <spring:url value="${friend.id}messageBtn" var="messageBtn"/>
                                                        <spring:url value="${friend.id}postBtn" var="postBtn"/>
                                                        <spring:url value="${friend.id}removeFriendBtn"
                                                                    var="removeFriendBtn"/>
                                                        <div class="btn-group">
                                                            <button id="${messageBtn}" class="btn btn-success" <%--data-toggle="modal"
                                                            data-target="#modalMessage"--%>
                                                                    onclick="invokeMessage(${friend.id})"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Отправить сообщение в чат"><span
                                                                    class="glyphicon glyphicon-envelope"
                                                                    aria-hidden="true"></span></button>
                                                            <button id="${postBtn}" class="btn btn-info" <%--data-toggle="modal"
                                                            data-target="#modalPost"--%>
                                                                    onclick="invokePost(${friend.id})"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Отправить сообщение на стену"><span
                                                                    class="glyphicon glyphicon-comment"
                                                                    aria-hidden="true"></span></button>
                                                            <button id="${removeFriendBtn}" class="btn btn-danger"
                                                                    onclick="removeFriend(${friend.id}, this.closest('tr'))"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Удалить из друзей"><span
                                                                    class="glyphicon glyphicon-trash"
                                                                    aria-hidden="true"></span></button>
                                                        </div>
                                                    </td>
                                                </tr>

                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%--TAB HOBBY--%>
                <div role="tabpanel" class="tab-pane fade in" id="hobbies-panel">
                    <div class="row tab-home">
                        <div class="panel-group" id="hobbyCollapseGroup" role="tablist"
                             aria-multiselectable="true">
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="hobbyHeadingOne">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse"
                                           data-parent="#<%--hobbyCollapseGroup--%>" href="#hobbyCollapseOne"
                                           aria-expanded="false" aria-controls="hobbyCollapseOne">
                                            <strong>Добавить новое хобби</strong>
                                        </a>
                                    </h4>
                                </div>
                                <div id="hobbyCollapseOne" class="panel-collapse collapse" role="tabpanel"
                                     aria-labelledby="hobbyHeadingOne" aria-expanded="false" style="height: 0px">
                                    <div class="panel-body" style="height: 110px">
                                        <table class="table table-hover-my">
                                            <thead>
                                            <tr>
                                                <th class="hobbyTitleTh">Название</th>
                                                <th class="hobbyDescriptionTh">Описание</th>
                                                <th class="hobbyBtnTh"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td style="text-align:center; vertical-align:middle">
                                                    <input class="form-control" id="hobbyTitleInput"
                                                           type="text">
                                                </td>
                                                <td style="text-align:center; vertical-align:middle">
                                                    <input class="form-control" id="hobbyDescriptionInput"
                                                           type="text">
                                                </td>
                                                <td>
                                                    <button id="saveHobbyBtn" style="width: 160px"
                                                            class="btn btn-success" onmouseover="showTooltip(this.id)"
                                                            onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                            data-placement="top" type="button"
                                                            data-original-title="Добавить хобби"><span
                                                            class="glyphicon glyphicon-save"
                                                            aria-hidden="true"></span>
                                                    </button>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="hobbyHeadingTwo">
                                    <h4 class="panel-title">
                                        <a class="" role="button" data-toggle="collapse"
                                           data-parent="#<%--hobbyCollapseGroup--%>" href="#hobbyCollapseTwo"
                                           aria-expanded="true" aria-controls="hobbyCollapseTwo">
                                            <strong>Мои хобби</strong>
                                        </a>
                                    </h4>
                                </div>
                                <div id="hobbyCollapseTwo" class="panel-collapse collapse in" role="tabpanel"
                                     aria-labelledby="hobbyHeadingTwo" aria-expanded="true" style="">
                                    <div id="myHobbyPanelBody" class="panel-body">
                                        <table class="table table-hover-my">
                                            <thead>
                                            <tr>
                                                <th class="hobbyTitleTh">Название</th>
                                                <th class="hobbyDescriptionTh">Описание</th>
                                                <th class="hobbyBtnTh"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${user.hobbies}" var="hobby">
                                                <tr>
                                                    <td id="hobby-title"
                                                        style="text-align:center; vertical-align:middle">
                                                        <input id="hobbyTitle"
                                                               style="text-align: center; border: none; background-color: transparent"
                                                               value="${hobby.title}"/>
                                                    </td>
                                                    <td id="hobby-description"
                                                        style="text-align:center; vertical-align:middle">
                                                        <input id="hobbyDescription"
                                                               style="text-align: center; border: none; background-color: transparent"
                                                               value="${hobby.description}"/>
                                                    </td>
                                                    <td class="hobbyCellBtn">
                                                        <spring:url value="${hobby.id}editHobbyBtn" var="editHobbyBtn"/>
                                                        <spring:url value="${hobby.id}removeHobbyBtn"
                                                                    var="removeHobbyBtn"/>
                                                        <div class="btn-group">
                                                            <button id="${editHobbyBtn}" class="btn btn-primary"
                                                                    onclick="editHobby(${hobby.id})"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Редактировать"><span
                                                                    class="glyphicon glyphicon-pencil"
                                                                    aria-hidden="true"></span>
                                                            </button>
                                                            <button id="${removeHobbyBtn}" class="btn btn-danger"
                                                                    onclick="removeHobby(${hobby.id}, this.closest('tr'))"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Удалить"><span
                                                                    class="glyphicon glyphicon-trash"
                                                                    aria-hidden="true"></span></button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%--TAB PLACES--%>
                <div role="tabpanel" class="tab-pane fade in" id="places-panel">
                    <div class="row tab-home">
                        <div class="panel-group" id="placeCollapseGroup" role="tablist"
                             aria-multiselectable="true">
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="placeHeadingOne">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse"
                                           data-parent="#<%--placeCollapseGroup--%>" href="#placeCollapseOne"
                                           aria-expanded="false" aria-controls="placeCollapseOne">
                                            <strong>Добавить новое место</strong>
                                        </a>
                                    </h4>
                                </div>
                                <div id="placeCollapseOne" class="panel-collapse collapse" role="tabpanel"
                                     aria-labelledby="placeHeadingOne" aria-expanded="false" style="height: 0px">
                                    <div class="panel-body" style="height: 110px">
                                        <table class="table table-hover-my">
                                            <thead>
                                            <tr>
                                                <%--<th class="placeBtnTh">Фото</th>--%>
                                                <th class="placeTitleTh">Название</th>
                                                <th class="placeDescriptionTh">Описание</th>
                                                <th class="placeLatitudeTh" style="width: 90px">Широта</th>
                                                <th class="placeLongitudeTh">Долгота</th>
                                                <th class="placePhotoTh"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <%--<td style="text-align:center; vertical-align:middle">--%>
                                                <%--<input class="form-control" id="placePhotoInput"--%>
                                                <%--type="text">--%>
                                                <%--</td>--%>
                                                <td style="text-align:center; vertical-align:middle">
                                                    <input class="form-control" id="placeTitleInput"
                                                           type="text">
                                                </td>
                                                <td style="text-align:center; vertical-align:middle">
                                                    <input class="form-control" id="placeDescriptionInput"
                                                           type="text">
                                                </td>
                                                <td style="text-align:center; vertical-align:middle">
                                                    <input class="form-control" id="placeLatitudeInput"
                                                           type="text">
                                                </td>
                                                <td style="text-align:center; vertical-align:middle">
                                                    <input class="form-control" id="placeLongitudeInput"
                                                           type="text">
                                                </td>
                                                <td class="placeCellBtn">
                                                    <button id="savePlaceBtn" class="btn btn-success"
                                                            onmouseover="showTooltip(this.id)" onmouseout="hideTooltip(this.id)"
                                                            data-toggle="tooltip" data-placement="top" type="button"
                                                            data-original-title="Добавить место"><span
                                                            class="glyphicon glyphicon-save"
                                                            aria-hidden="true"></span></button>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading" role="tab" id="placeHeadingTwo">
                                    <h4 class="panel-title">
                                        <a class="" role="button" data-toggle="collapse"
                                           data-parent="#<%--placeCollapseGroup--%>" href="#placeCollapseTwo"
                                           aria-expanded="true" aria-controls="placeCollapseTwo">
                                            <strong>Мои места</strong>
                                        </a>
                                    </h4>
                                </div>
                                <div id="placeCollapseTwo" class="panel-collapse collapse in" role="tabpanel"
                                     aria-labelledby="placeHeadingTwo" aria-expanded="true" style="">
                                    <div id="myPlacePanelBody" class="panel-body">
                                        <table class="table table-hover-my">
                                            <thead>
                                            <tr>
                                                <%--<th class="placePhotoTh">Фото</th>--%>
                                                <th class="placeTitleTh">Название</th>
                                                <th class="placeDescriptionTh">Описание</th>
                                                <th class="placeLatitudeTh">Широта</th>
                                                <th class="placeLongitudeTh">Долгота</th>
                                                <th class="placeBtnTh"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${user.places}" var="place">
                                                <tr>
                                                        <%--<td id="place-photo"--%>
                                                        <%--style="text-align:center; vertical-align:middle"><img--%>
                                                        <%--src="${place.photo}"--%>
                                                        <%--alt="../../resources/img/no-photo.png"--%>
                                                        <%--class="img-rounded"--%>
                                                        <%--height="30"></td>--%>
                                                    <td id="place-title"
                                                        style="text-align:center; vertical-align:middle">
                                                        <input id="placeTitle" class="placeTitleTh"
                                                               style="text-align: center; border: none; background-color: transparent"
                                                               value="${place.title}"/>
                                                    </td>
                                                    <td id="place-description"
                                                        style="text-align:center; vertical-align:middle">
                                                        <input id="placeDescription" class="placeDescriptionTh"
                                                               style="text-align: center; border: none; background-color: transparent"
                                                               value="${place.description}"/>
                                                    </td>
                                                    <td id="place-latitude"
                                                        style="text-align:center; vertical-align:middle">
                                                        <input id="placeLatitude" class="placeLatitudeTh"
                                                               style="text-align: center; border: none; background-color: transparent"
                                                               value="${place.latitude}"/>
                                                    </td>
                                                    <td id="place-longitude"
                                                        style="text-align:center; vertical-align:middle">
                                                        <input id="placeLongitude" class="placeLongitudeTh"
                                                               style="text-align: center; border: none; background-color: transparent"
                                                               value="${place.longitude}"/>
                                                    </td>
                                                    <td class="placeCellBtn">
                                                        <spring:url value="${place.id}editPlaceBtn" var="editPlaceBtn"/>
                                                        <spring:url value="${place.id}removePlaceBtn"
                                                                    var="removePlaceBtn"/>
                                                        <div class="btn-group">
                                                            <button id="${editPlaceBtn}" class="btn btn-primary"
                                                                    onclick="editPlace(${place.id})"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Редактировать"><span
                                                                    class="glyphicon glyphicon-pencil"
                                                                    aria-hidden="true"></span></button>
                                                            <button id="${removePlaceBtn}" class="btn btn-danger"
                                                                    onclick="removePlace(${place.id}, this.closest('tr'))"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="top"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Удалить"><span
                                                                    class="glyphicon glyphicon-trash"
                                                                    aria-hidden="true"></span></button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%--TAB PEOPLE--%>
                <div role="tabpanel" class="tab-pane fade in" id="people-panel">
                    <div class="row tab-home">
                        <div style="margin-bottom: 20px">
                            <label class="labelFilter control-label" style="margin-top: 7px; font-weight: normal">Фильтровать
                                по</label>

                            <div class="selectFilter">
                                <select id="selectFilter" class="form-control">
                                    <option value="1">Хобби</option>
                                    <option value="2">Места</option>
                                </select>
                            </div>
                            <input id="filterInput" type="text" class="form-control inputFilter"/>

                            <div class="userCellBtn">
                                <div class="btn-group" style="padding: 0 15px">
                                    <button id="filterBtn" class="btn btn-primary btnFilter"
                                            onclick="filter()" onmouseover="showTooltip(this.id)"
                                            onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                            data-placement="top" type="button" data-original-title="Фильтр"><span
                                            class="glyphicon glyphicon-filter"
                                            aria-hidden="true"></span></button>
                                    <button id="removeFilterBtn" class="btn btn-danger"
                                            onclick="removeFilter()" onmouseover="showTooltip(this.id)"
                                            onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                            data-placement="top" type="button"
                                            data-original-title="Снять фильтр"><span
                                            class="glyphicon glyphicon-remove"
                                            aria-hidden="true"></span></button>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading" role="tab" id="peopleHeading">
                                <h4 class="panel-title">
                                    <a role="button" data-toggle="collapse"
                                       href="#peopleCollapse"
                                       aria-expanded="false" aria-controls="peopleCollapse">
                                        <strong>Люди</strong>
                                    </a>
                                </h4>
                            </div>
                            <div id="peopleCollapse" class="panel-collapse collapse in" role="tabpanel"
                                 aria-labelledby="peopleHeading" aria-expanded="true" style="">
                                <div id="myPeoplePanelBody" class="panel-body">
                                    <div class="table-home">
                                        <table class="table table-hover-my">
                                            <thead>
                                            <tr>
                                                <th id="userPhotoTh">Фото</th>
                                                <th id="userLastNameTh">Фамилия</th>
                                                <th id="userFirstNameTh">Имя</th>
                                                <th id="userBthTh"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${contacts}" var="user">
                                                <tr>
                                                    <td id="user-photo"
                                                        style="text-align:center; vertical-align:middle"><img
                                                            src="${user.photo}" alt="../img/no-photo.png"
                                                            class="img-rounded"
                                                            height="30"></td>
                                                    <td id="user-last-name"
                                                        style="text-align:center; vertical-align:middle">${user.lastName}</td>
                                                    <td id="user-first-name"
                                                        style="text-align:center; vertical-align:middle">${user.firstName}</td>
                                                    <td class="userCellBtn">
                                                        <spring:url value="${user.id}addFriendBtn" var="addFriendBtn"/>
                                                        <spring:url value="${user.id}peopleSendMessageBtn"
                                                                    var="peopleSendMessageBtn"/>
                                                        <div class="btn-group">
                                                            <button id="${addFriendBtn}" class="btn btn-primary"
                                                                    onclick="addFriend(${user.id})"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Добавить в друзья"><span
                                                                    class="glyphicon glyphicon-plus"
                                                                    aria-hidden="true"></span></button>
                                                            <button id="${peopleSendMessageBtn}" class="btn btn-success"
                                                                    onclick="invokeMessage(${user.id})"
                                                                    onmouseover="showTooltip(this.id)"
                                                                    onmouseout="hideTooltip(this.id)" data-toggle="tooltip"
                                                                    data-placement="top" type="button"
                                                                    data-original-title="Отправить сообщение"><span
                                                                    class="glyphicon glyphicon-envelope"
                                                                    aria-hidden="true"></span></button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    //код Javascript, предназначенный для того активировать вкладку по хэшу (#tab_tab1) в адресной строке
    var hash = document.location.hash;
    var prefix = "tab_";
    if (hash) {
        $('.nav-tabs a[href=' + hash.replace(prefix, "") + ']').tab('show');
    }
    //Изменить хэш при перезагрузки страницы
    $('.nav-tabs a').on('shown', function (e) {
        window.location.hash = e.target.hash.replace("#", "#" + prefix);
    });
</script>

</body>
</html>
