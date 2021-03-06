<%--
  Created by IntelliJ IDEA.
  User: AzeraL
  Date: 25.01.2016
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${messages}" var="message">
  <c:if test="${message.userFrom.id==user.id}">
  <div class="popover-home" style="width: 650px; margin: 0 auto">
    <div class="popover left message">
      <div class="arrow"></div>
      <h3 style="background-color: #D9D9D9; color: #333333; height: 30px" class="popover-title">
        <span style="float: left">${message.userFrom.firstName} ${message.userFrom.lastName}</span>
         <span style="float:right"><fmt:formatDate value="${message.date}" pattern="HH:mm"/></span>
      </h3>
      <div style="background-color:#EFEFEF"
           class="popover-content">${message.content}</div>
    </div>
  </div>
  </c:if>
  <c:if test="${message.userFrom.id!=user.id}">
    <div class="popover-home" style="width: 650px; margin: 0 auto">
      <div class="popover right message">
        <div class="arrow"></div>
        <h3 style="background-color: #337AB7; color: #ffffff; height: 30px" class="popover-title">
          <span style="float: left">${message.userFrom.firstName} ${message.userFrom.lastName}</span>
          <span style="float:right"><fmt:formatDate value="${message.date}" pattern="HH:mm"/></span>
        </h3>
        <div style="background-color:#EFEFEF"
             class="popover-content">${message.content}</div>
      </div>
    </div>
  </c:if>
</c:forEach>

