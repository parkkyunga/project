package co.micol.prj.reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.ReplyService;
import co.micol.prj.notice.serviceImple.ReplyServiceImple;
import co.micol.prj.notice.vo.ReplyVO;

public class ReplyDelete implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ReplyService dao = new ReplyServiceImple();
		ReplyVO vo = new ReplyVO();
		
		vo.setNo(Integer.parseInt(request.getParameter("no")));
		int n = dao.replyDelete(vo);
		String jsonList = "0";
		if(n !=0) {
			jsonList ="1";
		}
		return "ajax:"+jsonList;
	}

}
