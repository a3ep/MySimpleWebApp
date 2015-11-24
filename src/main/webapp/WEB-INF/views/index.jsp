<%--
  Created by IntelliJ IDEA.
  User: Azeral
  Date: 24.11.2015
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="utf-8">
  <title>Авторизация/регистрация</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Le styles -->
  <link href="/wp-content/themes/clear-theme/styles/bootstrap.css" rel="stylesheet">
  <style>
    body {
      padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
    }
  </style>
  <link href="/wp-content/themes/clear-theme/styles/bootstrap-responsive.css" rel="stylesheet">

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="/wp-content/themes/clear-theme/js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/wp-content/themes/clear-theme/img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/wp-content/themes/clear-theme/img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/wp-content/themes/clear-theme/img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="/wp-content/themes/clear-theme/img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="/wp-content/themes/clear-theme/img/favicon.png">
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top">

  <div class="navbar-inner">

    <div class="container">

      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

      <a class="brand" href="#">MySimpleWebApp</a>

      <div class="nav-collapse collapse">
        <ul class="nav">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </div><!--/.nav-collapse -->

    </div>

  </div>

</div>

<div class="container">
    <h3 id="pageLabel">Добро пожаловать!</h3>
    <ul class="nav nav-tabs" id="login-or-register">
      <li><a href="#register" data-toggle="tab">Регистрация</a> </li>
      <li class="active"><a href="#profile" data-toggle="tab">Авторизация</a></li>
    </ul>

    <div class="tab-content">

      <div class="tab-pane" id="register">

        <form action="#">

          <div class="row">
            <h3>У вас еще нет логина?</h3>
            <p>Зарегистрируйтесь, пожалуйста.</p>
          </div>

          <div class="row">
            <label for="login">Введите логин</label>
            <input id="login" name="login" placeholder="Логин" value=""/>
            <span class="required">*</span>
          </div>

          <div class="row">
            <label for="password">Введите пароль</label>
            <input id="password" name="password" placeholder="Пароль" value=""/>
            <span class="required">*</span>
          </div>

          <div class="row">
            <label for="password-repeat">Повторите пароль</label>
            <input id="password-repeat" name="password-repeat" placeholder="Повторите пароль" value=""/>
            <span class="required">*</span>
          </div>

          <div class="row">
            <span class="required">*</span> - поля, обязательные для заполнения.
          </div>

          <div class="row">
            <input type="submit" id="register_button" class="register_button" value="Зарегистрироваться"/>
          </div>

        </form>

      </div>

      <div class="tab-pane active" id="profile">

        <form action="#">

          <div class="row">
            <h3>Уже зарегистрировались?</h3>
            <p>Авторизируйтесьб пожалуйстаю</p>
          </div>

          <div class="row">
            <label for="user_login">Введите логин</label>
            <input id="user_login" name="user_login" placeholder="Логин" value=""/>
            <span class="required">*</span>
          </div>

          <div class="row">
            <label for="user_password">Введите пароль</label>
            <input id="user_password" name="user_password" placeholder="Пароль" value=""/>
            <span class="required">*</span>
          </div>

          <div class="row">
            <span class="required">*</span> - поляб обязательные для заполнения.
          </div>

          <div class="row">
            <input type="checkbox" id="remember-me"/><label for="remember-me">Запомнить меня?</label>
          </div>

          <div class="row">
            <input type="submit" id="login_button" class="login_button" value="Войти"/>
          </div>

        </form>

      </div>

    </div>

  </div>

</div> <!-- /container -->

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/wp-content/themes/clear-theme/js/jquery.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-transition.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-alert.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-modal.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-dropdown.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-scrollspy.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-tab.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-tooltip.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-popover.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-button.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-collapse.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-carousel.js"></script>
<script src="/wp-content/themes/clear-theme/js/bootstrap-typeahead.js"></script>



</body>
