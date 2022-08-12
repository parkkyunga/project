<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div><h1>회 원 가 입</h1></div>
		<div>
			<form action="memberJoin.do " id="frm" method="post" onsubmit="return formCheck()">
				<div>
					<table border="1">
						<tr>
							<th width="150">아이디</th>
							<td width="300">
								<input type="text" id="memberId" name="memberId" size="20" required="required">&nbsp;
								<input type="hidden" id="checkId" value="No" >
								<button type="button" id="btn" onclick="idCheck()">중복체크</button>
							</td>
						</tr>
						<tr>
							<th width="150">비밀번호</th>
							<td width="300">
								<input type="password" id="memberPassword" name="memberPassword" size="20">&nbsp;
							</td>
						</tr>
						<tr>
							<th width="150">비밀번호확인</th>
							<td width="300">
								<input type="password" id="password" size="20">&nbsp;
							</td>
						</tr>
						<tr>
							<th width="150">이름</th>
							<td width="300">
								<input type="text" id="memberName" name="memberName" size="20">&nbsp;
							</td>
						</tr>
					</table>
				</div><br>
				<div>
					<input type="submit" value="회원가입">&nbsp;&nbsp;&nbsp;
					<input type="reset" value=" 취 소 ">&nbsp;&nbsp;&nbsp;
					<input type="button" value="홈 가기" onclick="location.href='main.do'">
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript">
	function formCheck(){
/* 		if(frm.memberId.value == ""){
			alert("아이디를 입력하세요.");
			frm.memberId.focus();
			return false;
		} 
*/
		
		if(frm.checkId.value == "No"){
			alert("아이디 중복체크 하세요");
			return false;
		}
		
		if(frm.memberPassword.value != frm.password.value){
			alert("비밀번호가 일치하지 않습니다.");
			frm.memberPassword.value = "";
			frm.password.value = "";
			frm.memberPassword.focus();
			return false;
		}
		
		if(frm.memberName.value == ""){
			alert("이름을 입력하세요");
			frm.memberName.focus();
			return false;
		}
		return true;
	}
	
	function idCheck(){
		let id = frm.memberId.value;
		if(id == ""){
			alert("아이디 입력 후 중복체크하세요.");
			frm.memberId.focus();
		}else{
			//ajax이용하여 아이디중복체크
			const xhttp = new XMLHttpRequest(); //ajax객체생성
			xhttp.onload = function(){
				if (this.readyState == 4 && this.status == 200) {
					htmlConvertAjax(this.responseText); //아작스호출성공했을때					
				} else {
					errorAjaxCall();//호출실패했을때
				}
			}
			xhttp.open("get","ajaxMemberIdCheck.do?id="+id); // 호출할 주소와 방식설정
			xhttp.send(); // 호출
		}
	}
	
	function htmlConvertAjax(str){
		if(str == "Used"){
			alert("사용가능한 아이디 입니다.")
			frm.checkId.value = "Yes";
			frm.btn.disabled = true;
			frm.memberPassword.focus();
		}else{
			alert("이미 사용중이거나 사용할 수 없는 아이디입니다.")
			frm.memberId.value = "";
			frm.memberId.focus();
		}
	}
	
	function errorAjaxCall(){
		alert("네트워크 통신 장애로 인해 처리할 수 없음. \n 잠시후 다시 시도하세요.")
	}
</script>	
</body>
</html>