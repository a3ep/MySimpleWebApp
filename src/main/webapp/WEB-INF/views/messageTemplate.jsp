<%--
  Created by IntelliJ IDEA.
  User: AzeraL
  Date: 25.01.2016
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${messages}" var="message">
  <div class="popover-home">
    <div class="popover right message">
      <div class="arrow"></div>
      <h3 style="background-color: #337AB7; color: #ffffff; text-align: right"
          class="popover-title">${message.from.firstName} ${message.from.lastName}<span> ${message.date}</span>
      </h3>

      <div style="background-color:#EFEFEF"
           class="popover-content">${message.content}</div>
    </div>
  </div>
</c:forEach>

