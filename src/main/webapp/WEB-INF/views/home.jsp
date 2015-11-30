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
                <li role="presentation"><a href="#register-panel" aria-controls="register" role="tab" data-toggle="tab">Регистрация</a>
                </li>
                <li role=presentation class="active"><a href="#profile-panel" aria-controls="profile" role="tab"
                                                        data-toggle="tab">Авторизация</a></li>
            </ul>

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane" id="register-panel">
                    <div class="row top-buffer">
                        <div class="col-lg-10 col-lg-offset-1">

                            <form>
                                <div class="form-group">
                                    <h3>У вас еще нет логина?</h3>
                                    <br/>
                                    <p>Зарегистрируйтесь, пожалуйста.</p>
                                </div>

                                <div class="form-group">
                                    <label for="first-name">Введите имя</label>
                                    <input id="first-name" name="first-name" placeholder="Имя" value=""/>
                                    <span class="required text-danger">*</span>
                                </div>

                                <div class="form-group">
                                    <label for="last-name">Введите фамилию</label>
                                    <input id="last-name" name="last-name" placeholder="Фамилия" value=""/>
                                    <span class="required text-danger">*</span>
                                </div>

                                <div class="form-group">
                                    <label for="birth_date">Введите дату рождения</label>
                                    <input id="birth_date" name="birth_date" placeholder="Дата рождения" value=""/>
                                    <span class="required text-danger">*</span>
                                </div>

                                <div class="form-group">
                                    <span class="required text-danger">*</span> - поля, обязательные для заполнения.
                                </div>

                                <div class="form-group">
                                    <input type="submit" id="register-button" class="register_button"
                                               value="Зарегистрироваться"/>
                                </div>
                            </form>
                            <div class="row top-buffer">
                                <div class="col-lg-offset-3 col-lg-6">
                                    <p id="register-button-response" class="text-centred"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div role=tabpanel class="tab-pane active" id="profile-panel">

                    <div class="row top-buffer">
                        <div class="col-lg-10 col-lg-offset-1">
                            <div class="row">
                                <h3>Уже зарегистрировались?</h3>
                                <br/>

                                <p>Авторизируйтесь, пожалуйста.</p>
                            </div>

                            <div class="row">
                                <label for="user_login">Введите логин</label>
                                <input id="user_login" name="user_login" placeholder="Логин" value=""/>
                                <span class="required text-danger">*</span>
                            </div>
                            <br/>

                            <div class="row">
                                <label for="user_password">Введите пароль</label>
                                <input id="user_password" name="user_password" placeholder="Пароль" value=""/>
                                <span class="required text-danger">*</span>
                            </div>
                            <br/>

                            <div class="row">
                                <span class="required text-danger">*</span> - поля, обязательные для заполнения.
                            </div>
                            <br/>

                            <div class="row">
                                <input type="checkbox" id="remember-me"/><label for="remember-me">Запомнить
                                меня?</label>
                            </div>
                            <br/>

                            <div class="row">
                                <input type="submit" id="login_button" class="login_button" value="Войти"/>
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
