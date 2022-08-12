package co.micol.prj.notice.serviceImple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.comm.DataSource;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.vo.NoticeVO;

public class NoticeServiceImple implements NoticeService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<NoticeVO> noticeSelectList() {
		// 전체목록
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE ORDER BY NOTICE_ID DESC";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new NoticeVO();
				vo.setNoticeId(rs.getInt("notice_id"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeDate(rs.getDate("notice_date"));
				vo.setNoticeAttech(rs.getString("notice_attech"));
				vo.setNoticeHit(rs.getInt("notice_hit"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		// 글상세보기
		String sql = "SELECT * FROM NOTICE WHERE NOTICE_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNoticeId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setNoticeId(rs.getInt("notice_id"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeSubject(rs.getString("notice_subject"));
				vo.setNoticeDate(rs.getDate("notice_date"));
				vo.setNoticeAttech(rs.getString("notice_attech"));
				vo.setNoticeHit(rs.getInt("notice_hit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		// 글등록
		int n = 0;
		String sql = "INSERT INTO NOTICE VALUES (notice_seq.nextval,?,?,?,?,0,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, vo.getNoticeId());
			psmt.setString(1, vo.getNoticeWriter());
			psmt.setString(2, vo.getNoticeTitle());
			psmt.setString(3, vo.getNoticeSubject());
			psmt.setDate(4, vo.getNoticeDate());
//			psmt.setInt(5, vo.getNoticeHit());
			psmt.setString(5, vo.getNoticeAttech());
			psmt.setString(6, vo.getNoticeAttechDir());

			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		// 수정
		int n = 0;

		return n;
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		// 삭제
		int n = 0;

		return n;
	}

	@Override
	public List<NoticeVO> noticeSearchList(String key, String val) {
		// 검색
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE WHERE "+key+" LIKE ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//psmt.setString(1, key);
			psmt.setString(1, "%"+val+"%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new NoticeVO();
				vo.setNoticeId(rs.getInt("notice_id"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeSubject(rs.getString("notice_subject"));
				vo.setNoticeDate(rs.getDate("notice_date"));
				vo.setNoticeHit(rs.getInt("notice_hit"));
				vo.setNoticeAttech(rs.getString("notice_attech"));
//				vo.setNoticeAttechDir(rs.getString("notice_attech_dir"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
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
