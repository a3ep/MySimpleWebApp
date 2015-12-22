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
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/my-style.css">
    <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../resources/js/home-script.js"></script>
    <script type="text/javascript" src="../../resources/js/post.js"></script>
    <script type="text/javascript" src="webjars/jquery.lazyload/1.9.3/jquery.lazyload.js"></script>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
</head>
<body style="background-color: lightgray">
<div class="container" style="background-color: white">
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <div class="page-header text-center">
        <h1 id="h1" datatype="utf-8">Добро пожаловать ${user.firstName}!</h1>
    </div>

    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="input-group col-lg-offset-8">
                <input type="text" class="form-control" placeholder="Искать...">
                <span class="input-group-btn">
                    <button class="btn btn-default btn-margin-and-height" type="button">Найти</button>
                </span>
            </div>
        </div>
    </div>

    <div class="row top-buffer">
        <div class="tabbable tabs-left">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#user-info-panel" role="tab"
                                                          data-toggle="tab">Профиль</a></li>
                <li role=presentation class="lazy"><a href="#friends-panel" role="tab" data-toggle="tab">Друзья</a></li>
                <li role=presentation class="lazy"><a href="#hobbies-panel" role="tab" data-toggle="tab">Хобби</a></li>
                <li role=presentation class="lazy"><a href="#places-panel" role="tab" data-toggle="tab">Места</a></li>
            </ul>

            <div class="tab-content col-lg-10">

                <%--TAB USER-PROFILE--%>
                <div role="tabpanel" class="tab-pane active" id="user-info-panel">
                    <div class="row col-lg-12">
                        <img src="${user.photo}" alt="../../resources/img/no-photo.png" class="img-rounded" height="200" align="left">

                        <div class="col-lg-6 col-lg-offset-2" style="margin-top: 30px">
                            <table class="table table-hover">
                                <tr>
                                    <td class="col-lg-10">Имя</td>
                                    <td>
                                        <input id="firstName" style="text-align: center; border: none"
                                               value="${user.firstName}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Фамилия</td>
                                    <td>
                                        <input id="lastName" style="text-align: center; border: none"
                                               value="${user.lastName}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>День рождения</td>
                                    <td>
                                        <input id="birthDate" style="text-align: center; border: none"
                                               value="<fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd"/>"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-lg-1 top-buffer-for-change-botton">
                            <button class="btn btn-success" style="margin-bottom: 5px" id="edit-profile-btn"><span
                                    class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                        </div>
                        <div class="row top-buffer">
                            <div class="col-lg-12 top-buffer">
                                <p id="edit-btn-response" class="text-centred"></p>
                            </div>
                        </div>
                    </div>
                </div>

                <%--TAB FRIENDS--%>
                <div role="tabpanel" class="tab-pane fade in" id="friends-panel">
                    <div class="col-lg-12">
                        <table class="table table-hover-my">
                            <thead>
                            <tr>
                                <th class="col-lg-3" style="text-align: center">Фото</th>
                                <th class="col-lg-3" style="text-align: center">Фамилия</th>
                                <th class="col-lg-3" style="text-align: center">Имя</th>
                                <th class="col-lg-2"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${user.friendList}" var="friend">
                                <tr>
                                    <td id="friend-photo" style="text-align:center; vertical-align:middle"><img
                                            src="${friend.photo}" alt="../img/no-photo.png" class="img-rounded"
                                            width="30" height="30"></td>
                                    <td id="friend-last-name"
                                        style="text-align:center; vertical-align:middle">${friend.lastName}</td>
                                    <td id="friend-first-name"
                                        style="text-align:center; vertical-align:middle">${friend.firstName}</td>
                                    <td>
                                        <spring:url value="/friends/${friend.id}/message" var="messageUrl"/>
                                        <spring:url value="/friends/${friend.id}/post" var="postUrl"/>
                                        <spring:url value="/friends/${friend.id}/delete" var="deleteUrl"/>
                                        <div class="btn-group">
                                            <button class="btn btn-success" onclick="location.href='${messageUrl}'"><span
                                                    class="glyphicon glyphicon-envelope"
                                                    aria-hidden="true"></span></button>
                                            <button class="btn btn-info" onclick="location.href='${postUrl}'"><span
                                                    class="glyphicon glyphicon-comment"
                                                    aria-hidden="true"></span></button>
                                            <button class="btn btn-danger" onclick="post('${deleteUrl}')"><span
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

                <%--TAB HOBBY--%>
                <div role="tabpanel" class="tab-pane fade in" id="hobbies-panel">
                    <div class="col-lg-offset-1 col-lg-10">
                        <table class="table table-hover-my">
                            <thead>
                            <tr>
                                <th class="col-lg-3" style="text-align: center">Название</th>
                                <th class="col-lg-3" style="text-align: center">Описание</th>
                                <th class="col-lg-1"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${user.hobbies}" var="hobby">
                                <tr>
                                    <td id="hobby-title"
                                        style="text-align:center; vertical-align:middle">${hobby.title}</td>
                                    <td id="hobby-description"
                                        style="text-align:center; vertical-align:middle">${hobby.description}</td>
                                    <td>
                                        <spring:url value="/hobies/${hobby.id}/edit" var="editUrl"/>
                                        <spring:url value="/hobies/${hobby.id}/delete" var="deleteUrl"/>
                                        <div class="btn-group" style=>
                                            <button class="btn btn-info" onclick="post(${editUrl})"
                                                    id="edit-hobby"><span class="glyphicon glyphicon-pencil"
                                                                          aria-hidden="true"></span></button>
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

                <%--TAB PLACES--%>
                <div role="tabpanel" class="tab-pane fade in" id="places-panel">
                    <div class="col-lg-offset-1 col-lg-10">
                        <table class="table table-hover-my">
                            <thead>
                            <tr>
                                <th class="col-lg-1" style="text-align: center">Фото</th>
                                <th class="col-lg-2" style="text-align: center">Название</th>
                                <th class="col-lg-5" style="text-align: center">Описание</th>
                                <th class="col-lg-1" style="text-align: center">Широта</th>
                                <th class="col-lg-1" style="text-align: center">Долгота</th>
                                <th></th>
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
                                    <td>
                                        <spring:url value="/places/${place.id}/edit" var="editUrl"/>
                                        <spring:url value="/places/${place.id}/delete" var="deleteUrl"/>
                                        <div class="btn-group">
                                            <button class="btn btn-info" onclick="post(${editUrl})"
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

<script>
    //код Javascript, предназначенный для того активировать вкладку по хэшу (#tab_tab1) в адресной строке
    var hash = document.location.hash;
    var prefix = "tab_";
    if (hash) {
        $('.nav-tabs a[href=' + hash.replace(prefix, "") + ']').tab('show');
    }
    //Изменить хэш при перезагрузки страницы
    $('.nav-tabs a').on('shown', function(e) {
        window.location.hash = e.target.hash.replace("#", "#" + prefix);
    });
</script>

</body>
</html>
