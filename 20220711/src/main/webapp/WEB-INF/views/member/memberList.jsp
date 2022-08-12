<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<div><h1>회원목록</h1></div>
<table border="1">
<tr>
	<th>아이디</th>
	<th>이 름</th>
	<th>권 한</th>
</tr>
<c:forEach items="${list }" var="m">
<tr>
	<td>${m.memberId }</td>
	<td>${m.memberName }</td>
	<td>${m.memberAuthor }</td>
</tr>	
</c:forEach>
</table>
</div>
</body>
</html>