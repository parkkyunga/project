<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지임당</title>
</head>
<body>
<div align="center">
<div><h1>♡♡♡로 그 인♡♡♡</h1></div>
<div>
	<form action="memberLogin.do" id="frm" method="post">
		<div>
			<table border="1">
				<tr>
				<th width="150"> 아 이 디 </th>
				<td width="200">
					<input type="text" id="memberId" name="memberId" required="required" placeholder="Enter Your Id..">
				</td>
				</tr>
				<tr>
				<th width="150">비 밀 번 호</th>
				<td width="200">
					<input type="password" id="memberPassword" name="memberPassword" required="required" placeholder="Enter Your password..">
				</td>
				</tr>
			</table>
		</div><br>
		<div>
			<input type="submit" value="로그인">&nbsp;&nbsp;&nbsp;
			<input type="reset" value="취 소">&nbsp;&nbsp;&nbsp;
			<input type="button" value="홈 가기" onclick="location.href='main.do'">
		</div>
	</form>
</div>

</div>
</body>
</html>