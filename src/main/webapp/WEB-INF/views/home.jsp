<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/my-style.css">

    <%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
    <%--<jsp:useBean id="contact" scope="request" type="net.bondar.web.model.Contact"/>--%>
</head>
<body>
<div class="container">
    <div class="page-header text-center">
        <h1>Добро пожаловать!</h1>
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
                                                          data-toggle="tab">Профиль</a>
                </li>
                <li role=presentation><a href="#friends-panel" role="tab" data-toggle="tab">Друзья</a></li>
                <li role=presentation><a href="#hobbies-panel" role="tab" data-toggle="tab">Хобби</a></li>
                <li role=presentation><a href="#places-panel" role="tab" data-toggle="tab">Места</a></li>
            </ul>

            <div class="tab-content col-lg-10">

                <%--TAB USER-PROFILE--%>
                <div role="tabpanel" class="tab-pane active" id="user-info-panel">
                    <%--<h3 class="text-index">Имя Фамилия</h3>--%>
                    <img src="../../resources/img/no-photo.png" alt="Фото" class="img-rounded col-lg-3" width="200"
                         height="200" align="left">

                    <div class="col-lg-offset-2 col-lg-6" style="margin-top: 30px">
                        <table class="table table-hover">
                            <tr>
                                <td>Имя</td>
                                <td id="user-first-name" style="text-align: center">Имя</td>
                            </tr>

                            <tr>
                                <td>Фамилия</td>
                                <td id="user-last-name" style="text-align: center">Фамилия</td>
                            </tr>
                            <tr>
                                <td>День рождения</td>
                                <td id="user-birth-date" style="text-align: center">11.11.1111</td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-lg-1 top-buffer-for-change-botton">
                        <button class="btn btn-success" style="margin-bottom: 10px" id="change-user-first-name"><span
                                class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                        <button class="btn btn-success" style="margin-bottom: 10px" id="change-user-last-name"><span
                                class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                        <button class="btn btn-success" style="margin-bottom: 10px" id="change-user-birth-date"><span
                                class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                    </div>
                </div>

                <%--TAB FRIENDS--%>
                <div role="tabpanel" class="tab-pane fade in" id="friends-panel">
                    <div class="col-lg-10">
                        <table class="table table-hover-my">
                            <thead>
                            <tr>
                                <th style="text-align: center">Фото</th>
                                <th style="text-align: center">Фамилия</th>
                                <th style="text-align: center">Имя</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td id="friend-photo" style="text-align:center; vertical-align:middle"><img
                                        src="../../resources/img/no-photo.png" alt="Фото" class="img-rounded" width="30"
                                        height="30"></td>
                                <td id="friend-last-name" style="text-align:center; vertical-align:middle">Фамилия</td>
                                <td id="friend-first-name" style="text-align:center; vertical-align:middle">Имя</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-2">
                        <div class="btn-group" style="margin-top: 45px">
                            <button class="btn btn-success" id="send-message"><span class="glyphicon glyphicon-envelope"
                                                                                    aria-hidden="true"></span></button>
                            <button class="btn btn-info" id="send-post"><span class="glyphicon glyphicon-comment"
                                                                              aria-hidden="true"></span></button>
                            <button class="btn btn-danger" id="remove-friend"><span class="glyphicon glyphicon-trash"
                                                                                    aria-hidden="true"></span></button>
                        </div>
                    </div>
                </div>

                <%--TAB HOBBY--%>
                <div role="tabpanel" class="tab-pane fade in" id="hobbies-panel">
                    <div class="col-lg-10">
                        <table class="table table-hover-my">
                            <thead>
                            <tr>
                                <th style="text-align: center">Название</th>
                                <th style="text-align: center">Описание</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td id="hobby-title" style="text-align:center; vertical-align:middle">Название</td>
                                <td id="hobby-description" style="text-align:center; vertical-align:middle">Описание
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-2">
                        <div class="btn-group" style="margin-top: 45px">
                            <button class="btn btn-info" id="edit-hobby"><span class="glyphicon glyphicon-pencil"
                                                                               aria-hidden="true"></span></button>
                            <button class="btn btn-danger" id="delete-hobby"><span class="glyphicon glyphicon-trash"
                                                                                   aria-hidden="true"></span></button>
                        </div>
                    </div>
                </div>

                <%--TAB PLACES--%>
                <div role="tabpanel" class="tab-pane fade in" id="places-panel">
                    <div class="col-lg-10">
                        <table class="table table-hover-my">
                            <thead>
                            <tr>
                                <th style="text-align: center">Фото</th>
                                <th style="text-align: center">Название</th>
                                <th style="text-align: center">Описание</th>
                                <th style="text-align: center">Широта</th>
                                <th style="text-align: center">Долгота</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td id="place-photo" style="text-align:center; vertical-align:middle"><img
                                        src="../../resources/img/no-photo.png" alt="Фото" class="img-rounded" width="30"
                                        height="30"></td>
                                <td id="place-title" style="text-align:center; vertical-align:middle">Название</td>
                                <td id="place-description" style="text-align:center; vertical-align:middle">Описание
                                </td>
                                <td id="place-latitude" style="text-align:center; vertical-align:middle">0.0</td>
                                <td id="place-longitude" style="text-align:center; vertical-align:middle">0.0</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-2">
                        <div class="btn-group" style="margin-top: 45px">
                            <button class="btn btn-info" id="edit-place"><span class="glyphicon glyphicon-pencil"
                                                                               aria-hidden="true"></span></button>
                            <button class="btn btn-danger" id="delete-place"><span class="glyphicon glyphicon-trash"
                                                                                   aria-hidden="true"></span></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../resources/js/my-script.js"></script>
</body>
</html>
