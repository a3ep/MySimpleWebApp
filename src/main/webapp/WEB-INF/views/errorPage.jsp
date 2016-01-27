<%--
  Created by IntelliJ IDEA.
  User: AzeraL
  Date: 27.01.2016
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<h1>Empty Session!</h1>
<p>Please relogin -> <a href="#" onclick="redirectToLogin()">Login page</a></p>

<script type="text/javascript">
  function redirectToLogin(){
    var url = document.url + "/";
    document.location.replace(url);
  }
</script>
</body>
</html>
