<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<div><h1>시작하는 곳</h1></div>
<c:if test="${empty id }">
<div><h3><a href="memberJoinForm.do">회원가입</a></h3></div>
<div><h3><a href="memberLoginForm.do">로 그 인</a></h3></div>
</c:if>
<c:if test="${not empty id }">
<div><h3><a href="memberLogout.do"> 로그아웃 </a></h3></div>
</c:if>
<c:if test="${author eq 'ADMIN' }">
<div><h3><a href="memberList.do"> 회원목록 </a></h3></div>
</c:if>
</div>
</body>
</html>