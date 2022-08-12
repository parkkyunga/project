<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<style type="text/css">
tr.colored:hover td{
  background-color:#F7FE2E !important;
  color:#303f39 !important;
}
</style>
</head>
<body>
	<div align="center">
		<div><h1>게시글 목록</h1></div>
		<div> 
			<form id="frm" method="post">
				<select id="key" name="key">
				  	<option value="notice_title">제목</option>
				  	<option value="notice_subject">내용</option>
				  	<option value="notice_writer">작성자</option>			  
				</select> &nbsp;
				<input type="text" id="val" name="val">&nbsp;&nbsp;
				<input type="button" value="검색" onclick="noticeSearch()">
			</form>
		</div><br>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th width="70">순번</th>
						<th width="130">작성자</th>
						<th width="200">제목</th>
						<th width="130">작성일자</th>
						<th width="180">첨부파일</th>
						<th width="70">조회수</th>
						<th width="70">삭제</th>								
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="b">
								<tr class="colored" onclick="noticeSelect(${b.noticeId })">
									<td>${b.noticeId }</td>
									<td>${b.noticeWriter }</td>
									<td>${b.noticeTitle }</td>
									<td>${b.noticeDate }</td>
									<td>${b.noticeAttech }</td>
									<td>${b.noticeHit }</td>
									<td align="center">
										<button type="button" onclick="delNotice(this)">삭제</button>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">
									게시글이 존재하지 않습니다
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div><br>
		<div>
		<c:if test="${author == 'ADMIN'}">
			<button type="button" onclick="location.href='noticeForm.do'">글등록</button>
		</c:if>
		</div>
	</div>
	
	<script type="text/javascript">
/* 		function noticeSearch() {
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "ajaxNoticeSearche.do",
				type : "post",
				data : {key : key, val : val},
				dataType : "json",
				success : function(result){
					if(result.length > 0) {
						jsonHtmlConvert(result);
					}else {
						alert("검색한 결과가 없습니다.");
					}
				},
				error : function() {
					
				}
			})
		} */
		
		function jsonHtmlConvert(data) {
			$('tbody').remove();
			var tbody = $("<tbody />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").text(item.noticeId),
							$("<td />").text(item.noticeWriter),
							$("<td />").text(item.noticeTitle),
							$("<td />").text(item.noticeDate),
							$("<td />").text(item.noticeAttech),
							$("<td />").text(item.noticeHit),
							$("<td />").append($("<button onclick=delNotice(this) /> ").text("삭제"))
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
		
		function noticeSearch() {  //XMLHttpRequest() 호출
			const ajax = new XMLHttpRequest();
			let key = document.getElementById('key').value;
			let val = document.getElementById('val').value;
			const url = "ajaxNoticeSearche.do";
			const data = {"key" : key,"val" : val};
			ajax.onload = function(){
				if(ajax.status >= 200 && ajax.status < 300){
					jsonHtmlConvert(ajax.response);
				}else {
					errorCallback(new Error(ajax.stautsText));
				}
			};
			
			ajax.onerror = errorCallback;
			ajax.open('POST',url,true);
			ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			ajax.responseType='json';
			ajax.send(Object.keys(data).map(key => key+"="+data[key]).join('&')); //  
		}
		
		function errorCallback(err){
			console.log('error : '+err.message);
		}
		
		function delNotice(obj){	
			let row = $(obj).parent().parent().get(0);
			let td = row.cells[0];
			let id = $(td).html();		
			
 			const xhr = new XMLHttpRequest();
			const url = "ajaxNoticeDelte.do?id="+id;
			xhr.onload = function(){
				if(xhr.status >= 200 && xhr.status < 300){
					if(xhr.response == 1) {
						alert("데이터가 삭제되었습니다.");
						$(row).remove();
					}else {
						alert("삭제 할 수 없습니다.");
					};
				}else {
					errorCallback(new Error(xhr.stautsText));
				}
			};
		
			xhr.open('GET',url);
			xhr.send(); 
		}
		
		function noticeSelect(id) {  //get방식 안전하지 않음
			location.href='noticeSelect.do?id='+id;			
		}
	</script>
</body>
</html>