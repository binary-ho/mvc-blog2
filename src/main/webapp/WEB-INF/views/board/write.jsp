<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%--    <form action="<c:url value="/board/write" />" method="POST">--%>
    <form:form commandName="boardVO" method="POST">
        <table border="1">
            <tr>
                <th>제목</th>
                <td><input name="title"></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><input name="content"></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input name="writer"><</td>
            </tr>
            <tr>
                <th><form:label path="password"> 비밀번호 </form:label> </th>
                <td><form:input path="password" />
                    <form:errors path="password" /></td>
            </tr>
        </table>
        <div>
            <input type="submit" value="등록">
            <a href="<c:url value="/board/list" />">목록</a>
        </div>
    </form:form>
</body>
</html>