package co.micol.prj.notice.service;

import java.util.List;

import co.micol.prj.notice.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList();
	
	int replyInsert(ReplyVO vo);

	int replyUpdate(ReplyVO vo);
	
	int replyDelete(ReplyVO vo);
}
