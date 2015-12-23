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
    <script type="text/javascript" src="../../resources/js/post.js"></script>
    <script type="text/javascript" src="webjars/jquery.lazyload/1.9.3/jquery.lazyload.js"></script>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
</head>
<body>
<div class="container-home">
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong style=" text-align: center">${msg}</strong>
            <strong id="response" style="text-align: center"></strong>
        </div>
    </c:if>

    <div class="header-home text-center">
        <h1 id="h1" datatype="utf-8">Добро пожаловать ${user.firstName}!</h1>
    </div>

    <div class="row">
        <div class="input-group search-block">
            <input type="text" class="form-control" placeholder="Искать...">
                <span class="input-group-btn">
                    <button class="btn btn-primary btn-margin-and-height" type="button">Найти</button>
                </span>
        </div>
    </div>

    <div class="mainRow">
        <div class="tabbable tabs-left">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#user-info-panel" role="tab"
                                                          data-toggle="tab">Профиль</a></li>
                <li role=presentation class="lazy"><a href="#friends-panel" role="tab" data-toggle="tab">Друзья</a></li>
                <li role=presentation class="lazy"><a href="#hobbies-panel" role="tab" data-toggle="tab">Хобби</a></li>
                <li role=presentation class="lazy"><a href="#places-panel" role="tab" data-toggle="tab">Места</a></li>
            </ul>

            <div id="content" class="tab-content">

                <%--TAB USER-PROFILE--%>
                <div role="tabpanel" class="tab-pane active" id="user-info-panel">
                    <div class="row tab-home">
                        <div class="row row-home">
                            <div id="profilePhoto">
                                <img src="${user.photo}" alt="Фото" class="img-rounded"
                                     height="200">
                            </div>
                            <div id="profileChangeBtn">
                                <button class="btn btn-primary" id="edit-profile-btn"><span
                                        class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                            </div>
                            <div id="profileInfo">
                                <table class="table table-hover">
                                    <tr>
                                        <td id="profileFirstCol" class="fontBold">Имя</td>
                                        <td id="profileLastCol">
                                            <input id="firstName" style="text-align: center; border: none"
                                                   value="${user.firstName}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="fontBold">Фамилия</td>
                                        <td>
                                            <input id="lastName" style="text-align: center; border: none"
                                                   value="${user.lastName}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fontBold">День рождения</td>
                                        <td>
                                            <input id="birthDate" style="text-align: center; border: none"
                                                   value="<fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd"/>"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div id="postRow" class="row">
                            <div id="postArea">
                                <div class="popover-home">
                                    <div class="popover right postMessage">
                                        <div class="arrow"></div>
                                        <h3 style="background-color: #337AB7; color: #ffffff" class="popover-title">
                                            <span style="text-align: left">Имя Фамилия</span> <span
                                                style="text-align:right">00:00</span></h3>

                                        <div style="background-color:#EFEFEF" class="popover-content">
                                            Сообщение сообщение сообщение сообщение сообщение сообщение сообщение
                                            сообщение
                                        </div>
                                    </div>
                                </div>
                                <div class="popover-home">
                                    <div class="popover right postMessage">
                                        <div class="arrow"></div>
                                        <h3 style="background-color: #337AB7; color: #ffffff" class="popover-title">
                                            <span style="text-align: left">Имя Фамилия</span> <span
                                                style="text-align:right">00:00</span></h3>

                                        <div style="background-color:#EFEFEF" class="popover-content">
                                            Сообщение сообщение сообщение сообщение сообщение сообщение сообщение
                                            сообщение
                                        </div>
                                    </div>
                                </div>
                                <div class="popover-home">
                                    <div class="popover right postMessage">
                                        <div class="arrow"></div>
                                        <h3 style="background-color: #337AB7; color: #ffffff" class="popover-title">
                                            <span style="text-align: left">Имя Фамилия</span> <span
                                                style="text-align:right">00:00</span></h3>

                                        <div style="background-color:#EFEFEF" class="popover-content">
                                            Сообщение сообщение сообщение сообщение сообщение сообщение сообщение
                                            сообщение
                                        </div>
                                    </div>
                                </div>
                                <%--<div class="panel panel-primary postMessage">--%>
                                <%--<div class="panel-heading">--%>
                                <%--<h3 class="panel-title"><span style="text-align: left;">Имя Фамилия</span> <span--%>
                                <%--style="text-align: right">00:00</span></h3>--%>
                                <%--</div>--%>
                                <%--<div class="panel-body">--%>
                                <%--Сообщение сообщение сообщение сообщение сообщение сообщение сообщение сообщение--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="panel panel-primary postMessage">--%>
                                <%--<div class="panel-heading">--%>
                                <%--<h3 class="panel-title"><span style="text-align: left;">Имя Фамилия</span> <span--%>
                                <%--style="text-align: right">00:00</span></h3>--%>
                                <%--</div>--%>
                                <%--<div class="panel-body">--%>
                                <%--Сообщение сообщение сообщение сообщение сообщение сообщение сообщение сообщение--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="panel panel-primary postMessage">--%>
                                <%--<div class="panel-heading">--%>
                                <%--<h3 class="panel-title"><span style="text-align: left;">Имя Фамилия</span> <span--%>
                                <%--style="text-align: right">00:00</span></h3>--%>
                                <%--</div>--%>
                                <%--<div class="panel-body">--%>
                                <%--Сообщение сообщение сообщение сообщение сообщение сообщение сообщение сообщение--%>
                                <%--</div>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                    </div>
                </div>

                <%--TAB FRIENDS--%>
                <div role="tabpanel" class="tab-pane fade in" id="friends-panel">
                    <div class="row tab-home">
                        <div class="row row-home">
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
                                            <td id="friend-photo" style="text-align:center; vertical-align:middle"><img
                                                    src="${friend.photo}" alt="../img/no-photo.png" class="img-rounded"
                                                    <%--width="30" --%>height="30"></td>
                                            <td id="friend-last-name"
                                                style="text-align:center; vertical-align:middle">${friend.lastName}</td>
                                            <td id="friend-first-name"
                                                style="text-align:center; vertical-align:middle">${friend.firstName}</td>
                                            <td id="friendCellBtn">
                                                <spring:url value="/friends/${friend.id}/message" var="messageUrl"/>
                                                <spring:url value="/friends/${friend.id}/post" var="postUrl"/>
                                                <spring:url value="/friends/${friend.id}/delete" var="deleteUrl"/>
                                                <div class="btn-group">
                                                    <button id="messageBtn" class="btn btn-success" data-toggle="modal"
                                                            data-target="#modalMessage"><span
                                                            class="glyphicon glyphicon-envelope"
                                                            aria-hidden="true"></span></button>
                                                    <button class="btn btn-info"
                                                            onclick="location.href='${postUrl}'"><span
                                                            class="glyphicon glyphicon-comment"
                                                            aria-hidden="true"></span></button>
                                                    <button class="btn btn-danger" onclick="post('${deleteUrl}')"><span
                                                            class="glyphicon glyphicon-trash"
                                                            aria-hidden="true"></span></button>
                                                </div>
                                                    <%----------------MODAL---------------%>
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

                                                                <h4 id="modalMessageLabel" class="modal-title">Отправить
                                                                    сообщение</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <label class="label label-primary" style="margin-bottom:10px"
                                                                       for="friendName">Кому</label>
                                                                <input class="form-control" style="width:660px; margin-bottom:15px"
                                                                       id="friendName" type="text"
                                                                       name="friendName"><br>
                                                                <label class="label label-primary" style="margin-bottom:10px" for="messageArea">Введите
                                                                    сообщение</label><br>
                                                                <textarea class="form-control" style="width:660px" id=messageArea
                                                                          name="message"></textarea>
                                                            </div>
                                                            <div class="modal-footer" style="background-color: #337AB7">
                                                                <button class="btn btn-success" type="button">Отправить
                                                                    сообщение
                                                                </button>
                                                                <button class="btn btn-danger" data-dismiss="modal"
                                                                        type="button">Закрыть
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
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

                <%--TAB HOBBY--%>
                <div role="tabpanel" class="tab-pane fade in" id="hobbies-panel">
                    <div class="row tab-home">
                        <div class="row row-home">
                            <div class="table-home">
                                <table class="table table-hover-my">
                                    <thead>
                                    <tr>
                                        <th id="hobbyTitleTh">Название</th>
                                        <th id="hobbyDescriptionTh">Описание</th>
                                        <th id="hobbyBtnTh"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${user.hobbies}" var="hobby">
                                        <tr>
                                            <td id="hobby-title"
                                                style="text-align:center; vertical-align:middle">${hobby.title}</td>
                                            <td id="hobby-description"
                                                style="text-align:center; vertical-align:middle">${hobby.description}</td>
                                            <td id="hobbyCellBtn">
                                                <spring:url value="/hobies/${hobby.id}/edit" var="editUrl"/>
                                                <spring:url value="/hobies/${hobby.id}/delete" var="deleteUrl"/>
                                                <div class="btn-group" style=>
                                                    <button class="btn btn-primary" onclick="post(${editUrl})"
                                                            id="edit-hobby"><span class="glyphicon glyphicon-pencil"
                                                                                  aria-hidden="true"></span>
                                                    </button>
                                                    <button class="btn btn-danger" onclick="post(${deleteUrl})"
                                                            id="delete-hobby"><span
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

                <%--TAB PLACES--%>
                <div role="tabpanel" class="tab-pane fade in" id="places-panel">
                    <div class="row tab-home">
                        <div class="row row-home">
                            <div class="table-home">
                                <table class="table table-hover-my">
                                    <thead>
                                    <tr>
                                        <th id="postPhotoTh">Фото</th>
                                        <th id="postTitleTh">Название</th>
                                        <th id="postDescriptionTh">Описание</th>
                                        <th id="postLatitudeTh">Широта</th>
                                        <th id="postLongitudeTh">Долгота</th>
                                        <th id="postBtnTh"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${user.places}" var="place">
                                        <tr>
                                            <td id="place-photo" style="text-align:center; vertical-align:middle"><img
                                                    src=${place.photo} alt="../../resources/img/no-photo.png"
                                                    class="img-rounded"
                                                    width="30"
                                                    height="30"></td>
                                            <td id="place-title"
                                                style="text-align:center; vertical-align:middle">${place.title}</td>
                                            <td id="place-description"
                                                style="text-align:center; vertical-align:middle">${place.description}</td>
                                            <td id="place-latitude"
                                                style="text-align:center; vertical-align:middle">${place.latitude}</td>
                                            <td id="place-longitude"
                                                style="text-align:center; vertical-align:middle">${place.longitude}</td>
                                            <td id="postCellBtn">
                                                <spring:url value="/places/${place.id}/edit" var="editUrl"/>
                                                <spring:url value="/places/${place.id}/delete" var="deleteUrl"/>
                                                <div class="btn-group">
                                                    <button class="btn btn-primary" onclick="post(${editUrl})"
                                                            id="edit-place"><span
                                                            class="glyphicon glyphicon-pencil"
                                                            aria-hidden="true"></span></button>
                                                    <button class="btn btn-danger" onclick="post(${deleteUrl})"
                                                            id="delete-place"><span
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
