<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: Azeral
  Date: 24.11.2015
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/my-style.css">

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
</head>

<body>
<div class="container">
    <div class="page-header text-center">
        <h1>Добро пожаловать!</h1>
    </div>

    <div class="top-buffer row">

        <div class="col-lg-12">
            <ul class="nav nav-tabs" id="login-or-register">
                <li role="presentation" class="active"><a href="#register-panel" aria-controls="register" role="tab"
                                                          data-toggle="tab">Регистрация</a></li>
                <li role=presentation><a href="#author-panel" aria-controls="profile" role="tab"
                                         data-toggle="tab">Авторизация</a></li>
            </ul>

            <div class="tab-content col-lg-10">

                <%--TAB REGISTRATION--%>
                <div role="tabpanel" class="tab-pane active" id="register-panel">

                    <div class="row top-buffer">
                        <div class="col-lg-offset-1">

                            <h3>Зарегистрируйтесь, пожалуйста.</h3><br/>

                            <div class="form-group">
                                <label for="first-name" class="col-lg-3 active">Введите имя:</label>

                                <div class="col-lg-4">
                                    <input type="text" id="first-name" class="form-control" name="first-name"
                                           placeholder="Имя"
                                           value="" autofocus required/>
                                </div>
                                <span class="required text-danger">*</span>
                            </div>

                            <div class="form-group">
                                <label for="last-name" class="col-lg-3">Введите фамилию:</label>

                                <div class="col-lg-4">
                                    <input type="text" id="last-name" class="form-control" name="last-name"
                                           placeholder="Фамилия" value="" required/>
                                </div>
                                <span class="required text-danger">*</span>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3">Введите дату рождения:</label>

                                <div class="col-lg-1">
                                    <select name="day" id="day" class="form-control form-control-padding"
                                            style="text-align: center" required>
                                        <option selected>-</option>
                                        <c:forEach var="day" begin="1" end="31">
                                            <option>${day}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-2">
                                    <select name="month" id="month" class="form-control" style="text-align: center"
                                            required>
                                        <option selected>-</option>
                                        <%
                                            String[] months_name = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
                                            int[] month_value = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                                            Map<String, Integer> months = new HashMap<>();
                                            for (int i = 0; i < month_value.length; i++) {
                                                months.put(months_name[i], month_value[i]);
                                            }
                                            pageContext.setAttribute("months", months);
                                        %>
                                        <c:forEach items="${months}" var="month">
                                            <option value="${month.value}">${month.key}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-1">
                                    <input id="year" class="form-control form-control-padding" name="year"
                                           placeholder="Год" value="" required/>
                                </div>
                                <span class="required text-danger">*</span>
                            </div>

                            <div class="form-group">
                                <label for="login" class="col-lg-3">Введите логин:</label>

                                <div class="col-lg-4">
                                    <input type="text" id="login" class="form-control" name="login" placeholder="Логин"
                                           value="" required/>
                                </div>
                                <span class="required text-danger">*</span>
                            </div>

                            <div class="form-group">
                                <label for="password" class="col-lg-3">Введите пароль:</label>

                                <div class="col-lg-4">
                                    <input type="password" id="password" class="form-control" name="password"
                                           placeholder="Пароль"
                                           value="" required/>
                                </div>
                                <span class="required text-danger">*</span>
                            </div>
                            <span class="required text-danger">*</span> - поля, обязательные для заполнения.

                            <p></p>

                            <div class="col-lg-1">
                                <%--<button class="btn btn-default" id="register-button">Зарегистрироваться</button>--%>
                                <input type="submit" class="btn btn-default" id="register-button"
                                       value="Зарегистрироваться">
                            </div>
                        </div>
                    </div>
                    <div class="row top-buffer">
                        <div class="col-lg-10 col-lg-offset-1">
                            <span id="register-button-response" class="text-centred"></span>
                        </div>
                    </div>
                </div>

                <%--TAB AUTHORISATION--%>
                <div role=tabpanel class="tab-pane fade in" id="author-panel">

                    <div class="row top-buffer">
                        <div class="col-lg-offset-1">
                            <h3>Уже зарегистрировались?</h3>

                            <p>Авторизируйтесь, пожалуйста.</p>

                            <form method="get" action="/author" class="form-horizontal" name="author-form" >
                                <div class="form-group">
                                    <label for="user_login" class="col-lg-2">Введите логин</label>

                                    <div class="col-lg-3">
                                        <input type="text" id="user_login" class="form-control" name="user_login"
                                               placeholder="Логин" value="" autofocus required/>
                                    </div>
                                    <span class="required text-danger">*</span><br/>
                                </div>

                                <div class="form-group">
                                    <label for="user_password" class="col-lg-2">Введите пароль</label>

                                    <div class="col-lg-3">
                                        <input type="password" id="user_password" class="form-control"
                                               name="user_password"
                                               placeholder="Пароль" value="" required/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                </div>

                                <span class="required text-danger">*</span> - поля, обязательные для заполнения.
                                <p></p>
                                <%--<input type="submit" id="login-button" class="login_button" value="Войти"/>--%>
                                <div class="col-lg-3">
                                    <button type="submit" class="btn btn-default" id="login-button">Войти</button>
                                </div>
                            </form>
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
