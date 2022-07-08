package co.micol.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.MainCommand;
import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MemberLogout;

@WebServlet("*.do") // 모든 .do 요청은 여기서 처리
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<>(); // 요청과 실행문을 매핑하기위해

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 초기화하는 메소드(매핑하는부분작성)
		map.put("/main.do", new MainCommand()); // 처음접속하는페이지
		map.put("/memberLoginForm.do", new MemberLoginForm());//로그인폼호풀
		map.put("/memberLogin.do", new MemberLogin());//로그인처리
		map.put("/memberLogout.do", new MemberLogout());//로그아웃
		map.put("/memberList.do", new MemberList());//회원목록
		
		map.put("/memberJoinForm.do", new MemberJoinForm());//회원가입화면
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck());//ajax를 이용한 아이디중복체크
		map.put("/memberJoin.do", new MemberJoin());//회원가입처리
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 실행하는 서비스 메소드
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI(); // 요청된 uri 확인
		String contextPath = request.getContextPath();// 요청된 URL로부터 contextPath를 확인한다
		String page = uri.substring(contextPath.length()); // 실제요청된페이지찾음

		Command command = map.get(page); // 실제수행할command찾음 new MainCommand();
		String viewPage = command.exec(request, response); // 요청Command를 수행하고 결과를 받음

		//viewResolve,보여줄(돌려줄)페이지
		if (!viewPage.endsWith(".do") && !viewPage.equals(null)) {
			//1.ajax를 처리하는 뷰 리졸브
			if(viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			//2.보통페이지
			viewPage = "WEB-INF/views/" + viewPage + ".jsp"; //시스템에서 접근 가능한 폴더릴 더해주고
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);//원하는 페이지 호출해서 전달함
		} else {
			//3.do요청
			response.sendRedirect(viewPage); //.do로 권한 위임 처리
		}

	}

}
