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
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.css">
    <link rel="stylesheet" href="../../resources/my-style.css">
    <link rel="stylesheet" href="../../resources/bootstrap_2.1.1.css">
</head>
<body>
<div class="container">
    <div class="page-header text-center">
        <h1>Добро пожаловать!</h1>
    </div>

    <form class="form-search">
        <div class="input-append">
            <input type="text" class="span2 search-query">
            <button type="submit" class="btn">Search</button>
        </div>

        <div class="col-lg-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="#user-info" role="tab" data-toggle="tab">Профиль</a>
                    </li>
                    <li role=presentation><a href="#friends" role="tab" data-toggle="tab">Друзья</a></li>
                    <li role=presentation><a href="#places" role="tab" data-toggle="tab">Места</a></li>
                    <li role=presentation><a href="#hobbies" role="tab" data-toggle="tab">Хобби</a></li>
                </ul>
                <div class="tab-content">
                    ...
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
<script type="text/javascript" src="../../resources/my-script.js"></script>
</body>
</html>
