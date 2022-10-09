<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%--    <form name="deleteForm" action="<c:url value="/board/delete" />" method="POST">--%>
    <form:form commandName="boardVO" method="POST">
        <form:input path="seq" />
        비빌번호 <input name="pwd"/>
        <input type="submit">
        <a href="<c:url value="/board/read/${boardVO.seq}"/>">취소</a>
    </form:form>
    <div>${msg}</div>
</body>
</html>