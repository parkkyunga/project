package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.MainCommand;
import co.micol.prj.comm.Command;
import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MemberLogout;
import co.micol.prj.notice.command.AjaxNoticeSearch;
import co.micol.prj.notice.command.NoticeForm;
import co.micol.prj.notice.command.NoticeInsert;
import co.micol.prj.notice.command.NoticeList;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> map = new HashMap<>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 요청과 수행할 command연결
		map.put("/main.do", new MainCommand());//처음접근하는곳
		map.put("/memberLoginForm.do", new MemberLoginForm());//로그인폼호풀
		map.put("/memberLogin.do", new MemberLogin());//로그인처리
		map.put("/memberLogout.do", new MemberLogout());//로그아웃
		map.put("/memberList.do", new MemberList());//회원목록
		
		map.put("/memberJoinForm.do", new MemberJoinForm());//회원가입화면
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck());//ajax를 이용한 아이디중복체크
		map.put("/memberJoin.do", new MemberJoin());//회원가입처리
		
		map.put("/noticeList.do", new NoticeList());//게시글목록
		map.put("/noticeForm.do", new NoticeForm());//게스글입력폼호출
		map.put("/noticeInsert.do", new NoticeInsert()); //게시글등록
		
		map.put("/ajaxNoticeSearch.do", new AjaxNoticeSearch());//게시글검색
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청분석하고 실행하고 결과돌려주는 곳
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		// 1.요청수행
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		// 2.결과페이지
		if (!viewPage.endsWith(".do")) {
			if (viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			viewPage = viewPage + ".tiles";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(viewPage);
		}
		
	}

}
