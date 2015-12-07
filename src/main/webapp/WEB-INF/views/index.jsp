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
                <div role="tabpanel" class="tab-pane active" id="register-panel">

                    <div class="row top-buffer">
                        <div class="col-lg-offset-1">

                            <h3>Зарегистрируйтесь, пожалуйста.</h3><br/>

                            <form class="form-horizontal" name="register-form">
                                <div class="form-group">
                                    <label for="first-name" class="col-lg-3 active">Введите имя:</label>

                                    <div class="col-lg-4">
                                        <input type="text" id="first-name" class="form-control" name="first-name" placeholder="Имя"
                                               value="" autofocus required/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                    <%--<span class="label label-important">Важно</span>--%>
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
                                        <select name="day" id="day" class="form-control form-control-padding" required>
                                            <option selected></option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                            <option>13</option>
                                            <option>14</option>
                                            <option>15</option>
                                            <option>16</option>
                                            <option>17</option>
                                            <option>18</option>
                                            <option>19</option>
                                            <option>20</option>
                                            <option>21</option>
                                            <option>22</option>
                                            <option>23</option>
                                            <option>24</option>
                                            <option>25</option>
                                            <option>26</option>
                                            <option>27</option>
                                            <option>28</option>
                                            <option>29</option>
                                            <option>30</option>
                                            <option>31</option>
                                            <script type="text/javascript">
                                                function writeDayOptions(){
                                                    document.write("<option selected></option>");
                                                    for (var dayCounter = 1; dayCounter<32; dayCounter++){
                                                        document.write("<option >" + dayCounter +" </option>");
                                                    }
                                                }
                                            </script>
                                        </select>
                                    </div>
                                    <div class="col-lg-2">
                                        <select name="month" id="month" class="form-control" required>
                                            <option selected></option>
                                            <option value=1>Январь</option>
                                            <option value=2>Февраль</option>
                                            <option value=3>Март</option>
                                            <option value=4>Апрель</option>
                                            <option value=5>Май</option>
                                            <option value=6>Июнь</option>
                                            <option value=7>Июль</option>
                                            <option value=8>Август</option>
                                            <option value=9>Сентябрь</option>
                                            <option value=10>Октябрь</option>
                                            <option value=11>Ноябрь</option>
                                            <option value=12>Декабрь</option>
                                            <script type="text/javascript">
                                                function writeMonthOptions(){
                                                    var Months = ["","Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"];

                                                    document.write("<option value=" + monthCounter + " selected>" + Months[monthCounter] + "</option>");

                                                    for (var monthCounter = 1; monthCounter < Months.length; monthCounter++){
                                                        document.write("<option value="+(monthCounter+1)+">" + Months[monthCounter]+" </option>");
                                                    }
                                                }
                                            </script>
                                        </select>
                                    </div>
                                    <div class="col-lg-1">
                                        <input id="year" class="form-control form-control-padding" name="year"
                                               placeholder="Год" value="" required/>
                                    </div>
                                    <%--<div class="col-lg-4">--%>

                                        <%--<input id="birth-date" class="form-control" name="birth-date"--%>
                                        <%--placeholder="Дата рождения" value=""/>--%>
                                    <%--</div>--%>
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
                                        <input type="password" id="password" class="form-control" name="password" placeholder="Пароль"
                                               value="" required/>
                                    </div>
                                    <span class="required text-danger">*</span>
                                </div>
                            </form>
                            <span class="required text-danger">*</span> - поля, обязательные для заполнения.

                            <p></p>

                            <div class="col-lg-1">
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

                <div role=tabpanel class="tab-pane fade in" id="author-panel">

                    <div class="row top-buffer">
                        <div class="col-lg-offset-1">
                            <h3>Уже зарегистрировались?</h3>

                            <p>Авторизируйтесь, пожалуйста.</p>

                            <form class="form-horizontal" name="author-form">
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
                                        <input type="password" id="user_password" class="form-control" name="user_password"
                                               placeholder="Пароль" value="" required/>
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
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../resources/js/my-script.js"></script>
</body>

</html>
