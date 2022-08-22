package co.micol.prj.reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.ReplyService;
import co.micol.prj.notice.serviceImple.ReplyServiceImple;
import co.micol.prj.notice.vo.ReplyVO;

public class ReplyInsert implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 댓글등록 
		ReplyService replyDao = new ReplyServiceImple();
		ReplyVO vo = new ReplyVO();
		ObjectMapper mapper = new ObjectMapper(); //jackson lib사용
		
		int n = 0;
		try {
			vo.setNoticeId(Integer.parseInt(request.getParameter("noticeId")));	
			vo.setContent(request.getParameter("content"));
			vo.setWriter(request.getParameter("writer"));
			n = replyDao.replyInsert(vo);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		String jsonList = null;
		try {
			jsonList = mapper.writeValueAsString(n);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
				
		return "ajax:" + jsonList;
	}
	

}
