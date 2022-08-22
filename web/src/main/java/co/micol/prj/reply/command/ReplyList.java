package co.micol.prj.reply.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.ReplyService;
import co.micol.prj.notice.serviceImple.ReplyServiceImple;
import co.micol.prj.notice.vo.ReplyVO;

public class ReplyList implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {


		// 리플목록가져오기
		ReplyService replyDao = new ReplyServiceImple();
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		list = replyDao.replyList();
		request.setAttribute("rList", list);
		
		return "notice/noticeSelect";
	}

}
