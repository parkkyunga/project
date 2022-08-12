package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MemberJoin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원가입처리
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(request.getParameter("memberPassword"));
		vo.setMemberName(request.getParameter("memberName"));
		vo.setMemberAuthor("USER");
		int n = memberDao.memberInsert(vo);
		if(n != 0) {
			request.setAttribute("message", "회원가입이 성공적으로 처리되었습니다.");
		}else {
			request.setAttribute("message", "회원가입 실패.");	
		}
		return "member/memberJoin"; //가입후 목록으로간다 근데멤버는이렇게가면안됨.게시판같은경우 이렇게함
	}

}
