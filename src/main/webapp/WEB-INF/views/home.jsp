<%--
  Created by IntelliJ IDEA.
  User: Azeral
  Date: 24.11.2015
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MySimpleWebApp</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resourses/my-style.css">
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
                <li role=presentation><a href="#profile-panel" aria-controls="profile" role="tab"
                                         data-toggle="tab">Авторизация</a></li>
            </ul>

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="register-panel">

                    <div class="row top-buffer">
                        <div class="col-lg-10 col-lg-offset-1">

                            <h3>Зарегистрируйтесь, пожалуйста.</h3><br/>

                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label for="first-name" class="col-lg-2">Введите имя</label>

                                    <div class="col-lg-3">
                                        <input id="first-name" class="form-control" name="first-name" placeholder="Имя"
                                               value=""/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                    <%--<span class="label label-important">Важно</span>--%>
                                </div>

                                <div class="form-group">
                                    <label for="last-name" class="col-lg-2">Введите фамилию</label>

                                    <div class="col-lg-3">
                                        <input id="last-name" class="form-control" name="last-name"
                                               placeholder="Фамилия" value=""/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                </div>

                                <div class="form-group">
                                    <label for="birth-date" class="col-lg-2">Введите дату рождения</label>

                                    <div class="col-lg-3">
                                        <input id="birth-date" class="form-control" name="birth-date"
                                               placeholder="Дата рождения" value=""/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                </div>

                                <div class="form-group">
                                    <label for="login" class="col-lg-2">Введите логин</label>

                                    <div class="col-lg-3">
                                        <input id="login" class="form-control" name="login" placeholder="Логин"
                                               value=""/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-lg-2">Введите пароль</label>

                                    <div class="col-lg-3">
                                        <input id="password" class="form-control" name="password" placeholder="Пароль"
                                               value=""/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                </div>
                            </form>
                            <span class="required text-danger">*</span> - поля, обязательные для заполнения.
                            <%--<input type="submit" id="register-button" class="register_button"--%>
                            <%--value="Зарегистрироваться"/>--%>
                            <p></p>

                            <div class="col-lg-3">
                                <button class="btn btn-default" id="register-button">Зарегистрироваться</button>
                            </div>
                        </div>
                    </div>
                    <div class="row top-buffer">
                        <div class="col-lg-10 col-lg-offset-1">
                            <span id="register-button-response" class="text-centred"></span>
                        </div>
                    </div>
                </div>

                <div role=tabpanel class="tab-pane fade" id="profile-panel">

                    <div class="row top-buffer">
                        <div class="col-lg-10 col-lg-offset-1">
                            <h3>Уже зарегистрировались?</h3>

                            <p>Авторизируйтесь, пожалуйста.</p>

                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label for="user_login" class="col-lg-2">Введите логин</label>

                                    <div class="col-lg-3">
                                        <input id="user_login" class="form-control" name="user_login"
                                               placeholder="Логин" value=""/>
                                    </div>
                                    <span class="required text-danger">*</span><br/>
                                </div>

                                <div class="form-group">
                                    <label for="user_password" class="col-lg-2">Введите пароль</label>

                                    <div class="col-lg-3">
                                        <input id="user_password" class="form-control" name="user_password"
                                               placeholder="Пароль" value=""/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                </div>

                                <span class="required text-danger">*</span> - поля, обязательные для заполнения.

                                <div class="form-group">
                                    <div class="col-lg-3">
                                        <input type="checkbox" id="remember-me"/><label for="remember-me">Запомнить
                                        меня?</label>
                                    </div>
                                </div>
                            </form>
                            <p></p>
                            <%--<input type="submit" id="login-button" class="login_button" value="Войти"/>--%>
                            <div class="col-lg-3">
                                <button class="btn btn-default" id="login-button">Войти</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resourses/my-script.js"></script>


</body>

</html>
