package notice.model.dao;

import static common.JDBCTemplate.*;
import java.sql.*;
import java.util.*;
import notice.model.vo.Notice;

public class NoticeDao {
	public NoticeDao(){}
	
	public ArrayList<Notice> selectList(Connection con){
		ArrayList<Notice> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice "
				+ "order by notice_no desc";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset != null){
				list = new ArrayList<Notice>();
				
				while(rset.next()){
					list.add(new Notice(rset.getInt("notice_no"),
							rset.getString("notice_title"),
							rset.getString("notice_writer"),
							rset.getDate("notice_date"),
							rset.getString("notice_content"),
							rset.getString("file_path")));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		
		return list;
	}
	
	public HashMap<Integer, Notice> selectMap(
			Connection con){
		HashMap<Integer, Notice> listMap = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice "
				+ "order by notice_no desc";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset != null){
				listMap = new HashMap<Integer, Notice>();
				
				while(rset.next()){
					listMap.put(rset.getInt("notice_no"),
						new Notice(rset.getInt("notice_no"),
							rset.getString("notice_title"),
							rset.getString("notice_writer"),
							rset.getDate("notice_date"),
							rset.getString("notice_content"),
							rset.getString("file_path")));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		
		return listMap;
	}
	
	public Notice selectOne(Connection con, int noticeNo){
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice "
				+ "where notice_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				notice = new Notice(noticeNo,
						rset.getString("notice_title"),
						rset.getString("notice_writer"),
						rset.getDate("notice_date"),
						rset.getString("notice_content"),
						rset.getString("file_path"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}
	
	public ArrayList<Notice> selectTitle(
			Connection con, String keyword){		
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice "
				+ "where notice_title like ? "
				+ "order by notice_no desc";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			if(rset != null){
				list = new ArrayList<Notice>();
				
				while(rset.next()){
					list.add(new Notice(rset.getInt("notice_no"),
							rset.getString("notice_title"),
							rset.getString("notice_writer"),
							rset.getDate("notice_date"),
							rset.getString("notice_content"),
							rset.getString("file_path")));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertNotice(Connection con, Notice n){
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into notice values ("
				+ "(select max(notice_no) from notice) + 1, "
				+ "?, ?, sysdate, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateNotice(Connection con, Notice n){
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update notice "
				+ "set notice_title = ?, notice_content = ?, "
				+ "file_path = ? "
				+ "where notice_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getFilePath());
			pstmt.setInt(4, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteNotice(Connection con, int noticeNo){
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from notice "
				+ "where notice_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
}







