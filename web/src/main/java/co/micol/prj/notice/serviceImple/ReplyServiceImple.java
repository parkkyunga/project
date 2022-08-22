package co.micol.prj.notice.serviceImple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.comm.DataSource;
import co.micol.prj.notice.service.ReplyService;
import co.micol.prj.notice.vo.NoticeVO;
import co.micol.prj.notice.vo.ReplyVO;
public class ReplyServiceImple implements ReplyService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<ReplyVO> replyList() {
		//리플전체목록
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO vo;
		String sql = "select * from reply order by no";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new ReplyVO();
				vo.setNo(rs.getInt("no"));
				vo.setWriter(rs.getString("writer"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setContent(rs.getString("content"));
				vo.setNoticeId(rs.getInt("notice_id"));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public int replyInsert(ReplyVO vo) {
		// 댓글등록
		int n = 0;
		String sql = "insert into reply values(reply_seq.nextval,?,sysdate,?,?)";
		try {	
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getWriter());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getNoticeId());
			n = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return n;
	}


	@Override
	public int replyUpdate(ReplyVO vo) {
		String sql ="update reply set content = ?,wdate=sysdate where no=?";
		int n = 0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getContent());
			psmt.setInt(2, vo.getNo());
			n = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}


	@Override
	public int replyDelete(ReplyVO vo) {
		String sql = "DELETE FROM reply WHERE no=?";
		int r =0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNo());
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return r;
	}
	



	private void close() {
		try {
			if (rs != null) rs.close();
			if (psmt != null) psmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
