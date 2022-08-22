package co.micol.prj.reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.ReplyService;
import co.micol.prj.notice.serviceImple.ReplyServiceImple;
import co.micol.prj.notice.vo.ReplyVO;

public class ReplyUpdate implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ReplyService replydao = new ReplyServiceImple();
		ReplyVO vo = new ReplyVO();
		vo.setNo(Integer.parseInt(request.getParameter("no")));
		vo.setContent(request.getParameter("content"));
		int n = replydao.replyUpdate(vo);
		System.out.println(request.getParameter("no"));
		System.out.println(request.getParameter("content"));
		String a = "0";
		
		if(n>0) {
			System.out.println("수정성공");
			a = "1";
		}else {
			System.out.println("수정실패");
		}
		return "ajax:"+a;
	}

}
