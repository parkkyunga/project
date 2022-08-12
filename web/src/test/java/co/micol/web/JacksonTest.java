package co.micol.web;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.notice.serviceImple.NoticeServiceImple;
import co.micol.prj.notice.vo.NoticeVO;

public class JacksonTest {
	//@Test
	public void toJson() throws JsonProcessingException {
		NoticeServiceImple service = new NoticeServiceImple();
		List<NoticeVO> list = service.noticeSelectList();
		
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(list);
		System.out.println(result);
	}
	@Test
	public void toObject() throws JsonMappingException, JsonProcessingException {
		String str = "{\"noticeId\":3,\"noticeWriter\":\"김나무\",\"noticeTitle\":\"공지여요\",\"noticeSubject\":null,\"noticeDate\":\"2022-06-28\",\"noticeHit\":0,\"noticeAttech\":null,\"noticeAttechDir\":null}";

		ObjectMapper mapper = new ObjectMapper();
		NoticeVO vo = mapper.readValue(str, NoticeVO.class);
		System.out.println(vo.getNoticeTitle());
	}

}
