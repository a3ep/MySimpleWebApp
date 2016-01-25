<%--
  Created by IntelliJ IDEA.
  User: Azeral
  Date: 24.11.2015
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Hello!</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/login.css">

    <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../resources/js/login-script.js"></script>

    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
</head>

<body>
<div class="container-login">
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert" style="margin-bottom: 0px; border-radius: 0px">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <div style="margin: 0 auto; width: 420px; text-align: center">
                <span id="alert-message" class="fontBold">${msg}</span>
            </div>
        </div>
    </c:if>
    <div class="header-login">
        <h1 class="fontBold">Добро пожаловать!</h1>
    </div>

    <div style="width: 880px; margin: 0 auto">

        <div class="mainRow">
            <ul class="nav nav-tabs" id="login-or-register">
                <li id="reg" role="presentation"><a href="#register-panel" aria-controls="register" role="tab"
                                                    data-toggle="tab">Регистрация</a></li>
                <li id="aut" role="presentation" class="active"><a href="#author-panel" aria-controls="profile"
                                                                   role="tab"
                                                                   data-toggle="tab">Авторизация</a></li>
            </ul>

            <div id=content class="tab-content">

                <%--TAB REGISTRATION--%>
                <div role="tabpanel" class="tab-pane fade in" id="register-panel">

                    <div id="registerPage" class="row tab-login">
                        <div style="width: 800px; margin: 0 auto">
                            <h3 style="text-align: center">Зарегистрируйтесь, пожалуйста</h3><br/>
                        </div>
                        <div class="row-login">
                            <%----------------------------------------------------------------------------------------------                          --%>
                            <table class="table" style="width: 555px; margin: 0 auto">
                                <form:form class="form-horizontal" modelAttribute="userForm" method="post"
                                           action="/saveContact">
                                <tr>
                                    <spring:bind path="firstName">
                                        <td>
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label class="label-login control-label active">Введите
                                                    имя:</label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-login ${status.error ? 'has-error' : ''}">
                                                <form:input path="firstName" type="text" class="form-control"
                                                            id="firstName"
                                                            placeholder="Имя"/>
                                                <form:errors path="firstName" class="control-label"/>
                                            </div>
                                            <div class="span-login">
                                                <span class="required text-danger">*</span>
                                            </div>
                                        </td>
                                    </spring:bind>
                                </tr>
                                <tr>
                                    <spring:bind path="lastName">
                                        <td class="fontBold">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label class="label-login control-label">Введите
                                                    фамилию:</label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-login ${status.error ? 'has-error' : ''}">
                                                <form:input path="lastName" type="text" class="form-control"
                                                            id="lastName"
                                                            placeholder="Фамилия"/>
                                                <form:errors path="lastName" class="control-label"/>
                                            </div>
                                            <div class="span-login">
                                                <span class="required text-danger">*</span>
                                            </div>
                                        </td>
                                    </spring:bind>
                                </tr>
                                <tr>
                                    <spring:bind path="birthDate">
                                        <td class="fontBold">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label class="label-login control-label">Введите дату
                                                    рождения:</label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-login ${status.error ? 'has-error' : ''}">
                                                <form:input path="birthDate" type="date" class="form-control "
                                                            id="birthDate" placeholder="dd.MM.yyyy"/>
                                                <form:errors path="birthDate" class="control-label"/>
                                            </div>
                                                <%--<div>--%>
                                                <%--<div class="col-lg-1">--%>
                                                <%--<form:select path="birthDate" name="day" id="day"--%>
                                                <%--class="form-control form-control-padding"--%>
                                                <%--style="text-align: center">--%>
                                                <%--<option selected>-</option>--%>
                                                <%--<c:forEach var="day" begin="1" end="31">--%>
                                                <%--<option>${day}</option>--%>
                                                <%--</c:forEach>--%>
                                                <%--</form:select>--%>
                                                <%--</div>--%>
                                                <%--<div class="col-lg-2">--%>
                                                <%--<form:select path="birthDate" name="month" id="month" class="form-control"--%>
                                                <%--style="text-align: center">--%>
                                                <%--<option selected>-</option>--%>
                                                <%--<%--%>
                                                <%--String[] months_name = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};--%>
                                                <%--int[] month_value = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};--%>
                                                <%--Map<String, Integer> months = new HashMap<>();--%>
                                                <%--for (int i = 0; i < month_value.length; i++) {--%>
                                                <%--months.put(months_name[i], month_value[i]);--%>
                                                <%--}--%>
                                                <%--pageContext.setAttribute("months", months);--%>
                                                <%--%>--%>
                                                <%--<c:forEach items="${months}" var="month">--%>
                                                <%--<option value="${month.value}">${month.key}</option>--%>
                                                <%--</c:forEach>--%>
                                                <%--</form:select>--%>
                                                <%--</div>--%>
                                                <%--<div class="col-lg-1">--%>
                                                <%--<form:input path="birthDate" id="year"--%>
                                                <%--class="form-control form-control-padding"--%>
                                                <%--name="year"--%>
                                                <%--placeholder="Год" value=""/>--%>
                                                <%--</div>--%>
                                                <%--<form:errors path="birthDate" class="control-label"/>--%>
                                                <%--</div>--%>
                                            <div class="span-login">
                                                <span class="required text-danger">*</span>
                                            </div>
                                        </td>
                                    </spring:bind>
                                </tr>
                                    <%--<tr>--%>
                                    <%--<spring:bind path="photo">--%>
                                    <%--<td class="fontBold">--%>
                                    <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                                    <%--<label>Выберите фото:</label>--%>
                                    <%--</div>--%>
                                    <%--</td>--%>
                                    <%--<td>--%>
                                    <%--<div class="input-login ${status.error ? 'has-error' : ''}">--%>
                                    <%--<form:input path="photo" type="file" class="form-control "--%>
                                    <%--id="photo"/>--%>
                                    <%--<form:errors path="photo" class="control-label"/>--%>
                                    <%--</div>--%>
                                    <%--<span class="required text-danger">*</span>--%>
                                    <%--</td>--%>
                                    <%--</spring:bind>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                <spring:bind path="userName">
                                    <td class="fontBold">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <label class="label-login control-label">Введите логин:</label>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-login ${status.error ? 'has-error' : ''}">
                                            <form:input path="userName" type="text" class="form-control "
                                                        id="userName"
                                                        placeholder="Логин"
                                                        value=""/>
                                            <form:errors path="userName" class="control-label"/>
                                        </div>
                                        <div class="span-login">
                                            <span class="required text-danger">*</span>
                                        </div>
                                    </td>
                                </spring:bind>
                                </tr>
                                <tr>
                                    <spring:bind path="password">
                                        <td class="fontBold">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label class="label-login control-label">Введите пароль:</label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-login ${status.error ? 'has-error' : ''}">
                                                <form:input path="password" type="password" class="form-control "
                                                            id="password"
                                                            placeholder="Пароль"
                                                            value=""/>
                                                <form:errors path="password" class="control-label"/>
                                            </div>
                                            <div class="span-login">
                                                <span class="required text-danger">*</span>
                                            </div>
                                        </td>
                                    </spring:bind>
                                </tr>
                                <tr>
                                    <spring:bind path="confirmPassword">

                                        <td class="fontBold">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label class="label-login control-label">Подтвердите пароль:</label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-login ${status.error ? 'has-error' : ''}">
                                                <form:input path="confirmPassword" type="password"
                                                            class="form-control "
                                                            id="confirmPassword" placeholder="Пароль"
                                                            value=""/>
                                                <form:errors path="confirmPassword" class="control-label"/>
                                            </div>
                                            <div class="span-login">
                                                <span class="required text-danger">*</span>
                                            </div>
                                        </td>
                                    </spring:bind>
                                </tr>
                            </table>
                            <div style="width: 325px; margin: 20px auto">
                                <span class="required text-danger">*</span> - поля, обязательные для заполнения.
                            </div>
                            <div style="width: 190px; margin: 0 auto">
                                <input type="submit" class="btn btn-primary fontBold" id="register-button"
                                       value="Зарегистрироваться">
                            </div>
                            </form:form>
                            <%----------------------------------------------------------------------------------------------                          --%>
                        </div>
                    </div>
                    <div class="row top-buffer">
                        <div>
                            <p id="register-button-response" class="text-centred"></p>
                        </div>
                    </div>
                </div>

                <%--TAB AUTHORISATION--%>
                <div role=tabpanel class="tab-pane fade in active" id="author-panel">
                    <div class="row tab-login">
                        <div style="width: 500px; margin: 0 auto">
                            <h3 style="text-align:center">Уже зарегистрировались?</h3>

                            <p class="fontBold" style="text-align:center">Авторизируйтесь, пожалуйста.</p>
                        </div>
                        <div class="row-login" style="margin-top: 20px">
                            <table class="table" style="width: 500px; margin: 0 auto">
                                <%----------------------------------------------------------------------------------------------                          --%>
                                <form:form class="form-horizontal" modelAttribute="userForm" method="get"
                                      action="/author">
                                    <tr>

                                        <td>
                                            <div class="form-group">
                                                <label class="label-login control-label active">Введите
                                                    логин</label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-login">
                                                <form:input path="userName" type="text" class="form-control"
                                                       id="userName"
                                                       placeholder="Логин"/>
                                            </div>
                                            <div class="span-login">
                                                <span class="required text-danger">*</span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fontBold">
                                            <div class="form-group">
                                                <label class="label-login control-label active">Введите
                                                    пароль</label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-login">
                                                <form:input path="password" type="password" class="form-control"
                                                       id="password"
                                                       placeholder="Пароль"/>
                                            </div>
                                            <div class="span-login">
                                                <span class="required text-danger">*</span>
                                            </div>
                                        </td>
                                    </tr>

                            </table>
                            <div style="width: 325px; margin: 20px auto">
                                <span class="required text-danger">*</span> - поля, обязательные для
                                заполнения.
                            </div>
                            <p></p>

                            <div style="width: 190px; margin: 0 auto">
                                <input style="width: 190px" type="submit" class="btn btn-primary fontBold"
                                       id="login-button"
                                       value="Войти">
                            </div>
                            </form:form>

                            <%--<spring:url value="/author" var="userAuthUrl"/>--%>
                            <%--<form:form class="form-horizontal" method="get" modelAttribute="userForm"--%>
                            <%--action="${userAuthUrl}">--%>

                            <%--<spring:bind path="userName">--%>
                            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<label class="col-lg-2 control-label">Введите логин:</label>--%>

                            <%--<div class="col-lg-6">--%>
                            <%--<form:input path="userName" type="text" class="form-control" id="userName"--%>
                            <%--placeholder="Логин"/>--%>
                            <%--<form:errors path="userName" class="control-label"/>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--</spring:bind>--%>
                            <%--<spring:bind path="password">--%>
                            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<label class="col-lg-2 control-label">Введите пароль:</label>--%>

                            <%--<div class="col-lg-6">--%>
                            <%--<form:input path="password" class="form-control"--%>
                            <%--id="password" placeholder="Пароль"/>--%>
                            <%--<form:errors path="password" class="control-label"/>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--</spring:bind>--%>
                            <%--<div class="form-group">--%>
                            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                            <%--<button type="submit" class="btn-lg btn-default pull-right">Войти--%>
                            <%--</button>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--</form:form>--%>
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
