package co.micol.prj.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private int no;
	private String writer;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date wdate;
	private String content;
	private int noticeId;

}
