<%--
  Created by IntelliJ IDEA.
  User: Azeral
  Date: 24.11.2015
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title></title>
  <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/my-style.css">
</head>
<body>
<!-- Button to trigger modal -->
<button type="button" data-toggle="modal" data-target="#myModal"> Авторизация/Регистрация </button>
<!-- Modal -->
<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Добро пожаловать на наш сайт!</h3>
  </div>
  <div class="modal-body">
    <ul class="nav nav-tabs" id="login-or-register">
      <li ><a href="#register" data-toggle="tab">Регистрация</a></li>
      <li class="active"><a href="#profile" data-toggle="tab">Авторизация</a></li>
    </ul>
    <div class="tab-content">
      <div class="tab-pane" id="register">
        <form action="#">
          <div class="row">
            <h3>У вас еще нет логина на нашем сайте?</h3>
            <p>Зарегистрируйтесь, пожалуйста.</p>
          </div>
          <div class="row">
            <label for="login">Введите Ваш логин</label>
            <input id="login" name="login" value="" />
            <span class="required">*</span>
          </div>
          <div class="row">
            <label for="password">Введите Ваш пароль</label>
            <input id="password" name="password" value="" />
            <span class="required">*</span>
          </div>
          <div class="row">
            <label for="password_repeat">Повторите Ваш пароль</label>
            <input id="password_repeat" name="password_repeat" value="" />
            <span class="required">*</span>
          </div>
          <div class="row">
            Поля, помеченные <span class="required">*</span>, обязательны для заполнения.
          </div>
          <div class="row">
            <input type="submit" id="register_button" class="register_button" value="Зарегистрироваться" />
          </div>
        </form>
      </div>
      <div class="tab-pane active" id="profile">
        <form action="#">
          <div class="row">
            <h3>Уже есть логин?</h3>
            <p>Авторизируйтесь, пожалуйста.</p>
          </div>
          <div class="row">
            <label for="user_login">Введите Ваш логин</label>
            <input id="user_login" name="login" value="" />
            <span class="required">*</span>
          </div>
          <div class="row">
            <label for="user_password">Введите Ваш пароль</label>
            <input id="user_password" name="password" value="" />
            <span class="required">*</span>
          </div>
          <div class="row">
            Поля, помеченные <span class="required">*</span>, обязательны для заполнения.
          </div>
          <div class="row">
            <input type="checkbox" id="remember-me" /><label for="remember-me">Запомнить меня?</label>
          </div>
          <div class="row">
            <input type="submit" id="login_button" class="login_button" value="Войти" />
          </div>
        </form>
      </div>
    </div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Закрыть</button>
  </div>
</div>




<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
