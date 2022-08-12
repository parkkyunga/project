package co.micol.prj.member.service;

import java.util.List;

import co.micol.prj.member.vo.MemberVO;

public interface MemberService {
	List<MemberVO> memberSelectList(); // 멤버전체조회 R
	MemberVO memberSelectOne(MemberVO vo);// 멤버조회 R

	int memberInsert(MemberVO vo); // C
	int memberUpdate(MemberVO vo); // U
	int memberDelete(MemberVO vo); // D
	boolean isMemberIdCheck(String id); // id중복체크-이미아이디존재하면false - id사용못해~
	MemberVO memberLogin(MemberVO vo); // 로그인체크
}
