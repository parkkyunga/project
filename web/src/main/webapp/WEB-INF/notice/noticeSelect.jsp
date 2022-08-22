<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{
	border-collapse: collapse;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

</head>
<body>
<div align="center">
	<div><h1>게시글 상세보기</h1></div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>글번호</td>
					<td>글쓴이</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${vo.noticeId }</td>
					<td>${vo.noticeWriter }</td>
					<td>${vo.noticeDate }</td>
					<td>${vo.noticeHit }</td>
				</tr>
				<tr>
					<td colspan="4" width="760">제목 : ${vo.noticeTitle }</td>
				</tr>
				<tr>
					<td colspan="4">내용</td>
				</tr>
				<tr>
					<td colspan="4" height="200">${vo.noticeSubject }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>
	<div>
		<form action="replyInsert.do" id="frm" method="post">
			<label>댓글 </label>
			<input type="hidden" id="noticeId" name="noticeId" value="${vo.noticeId}">
			<input type="hidden" id="writer" name="writer" value="park">
			<input type="hidden" id="wdate" name="wdate" value="sysdate">
			
			<input type="text" id="content" name="content" width="100%">
			<input type="submit" id="rInsertBtn" value="등록">
		</form>
	</div>
	<br>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th width="70">순번</th>
					<th width="90">작성자</th>
					<th width="360">내용</th>
					<th width="100">작성일자</th>
					<th width="120"></th>								
				</tr>
			</thead>
			<tbody id="tbody">
				<c:choose>
					<c:when test="${not empty rList}">
						<c:forEach items="${rList }" var="b">
							<c:if test="${b.noticeId eq vo.noticeId }">
								<tr class="colored">
									<td>${b.no }</td>
									<td>${b.writer }</td>
									<td>${b.content }</td>
									<td>${b.wdate }</td>
									<td align="center"> 
										<button type="button" id="replyUpdate">수정</button>
										<button type="button" id="replyDelete">삭제</button>
									</td>
								</tr>
							</c:if>	
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6" align="center">
								댓글이 존재하지 않습니다
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div> 
<script type="text/javascript">
//등록
rInsertBtn.addEventListener("click",function(e){
    //e.preventDefault()
	let nId = document.querySelector('[name="noticeId"]').value;
	let ct = document.querySelector('[name="content"]').value;
	let wr = document.querySelector('[name="writer"]').value;
	let wd = document.querySelector('[name="wdate"]').value;
	console.log(nId)
	console.log(ct)
	console.log(wr)
	let param = `content=${ct}&writer=${wr}&noticeId=${nId}`
		let url = "replyInsert.do"
		fetch(url,{
			method : "post",
			headers : {'Content-Type': 'application/x-www-form-urlencoded',},
			body : param 
		})
		.then(res=>res.json())
		.then(obj=>{
			//tbody.innerHTML += 
				`<tr class="colored">
					<td>${obj.no }</td>
					<td>${obj.writer }</td>
					<td>${ct}</td>
					<td>${wd }</td>
					<td align="center">
						<button type="button" id="replyUpdate">수정</button>
						<button type="button" id="replyDelete">삭제</button>
					</td>
				</tr>`
			frm.reset();
			location.href='noticeSelect.do?id='+nId
		})
})
	
//수정
$("#tbody").on("click",'#replyUpdate',function(e){
    e.preventDefault()
	let nContent = $(this).closest("tr").find("td").eq(2)
	nContent.text("")
	let nInput = `<input type='text' id='newConVal' name='newConVal'>`
	nContent.append(nInput)
	
	
	$(this).text("수정완료").on("click",()=>{
		let conNo = $(this).closest("tr").find("td").eq(0).text()
		let newContent = $("#newConVal").val()
		
		$.ajax("replyUpdate.do",{
			method : "post",
			data : {no:conNo,
					content:newContent}
		})
		.done(()=>{
			location.href="noticeSelect.do?id="+$('[name=noticeId]').val()	
		})
	})
})

//삭제 
$("#tbody").on("click",'#replyDelete',function(e){
	let conNo = $(this).closest("tr").find("td").eq(0).text()
		$.ajax("replyDelete.do",{
			method : "post",
			data : {no:conNo}
		})
		.done(()=>{
			location.href="noticeSelect.do?id="+$('[name=noticeId]').val()	
		})
})

</script>
</body>
</html>