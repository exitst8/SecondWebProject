package notice.model.dao;

import static common.JDBCTemplate.*;
import java.sql.*;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

import notice.model.vo.Notice;

public class NoticeDao {
	public NoticeDao(){}
	
	public ArrayList<Notice> selectList(SqlSession session){
		
		ArrayList<Notice> list 
				= (ArrayList)session.selectList("Notice.selectList");
			// selectList() 는 mybatis 에서 제공하는 메소드
		
		System.out.println(list);
		
		return list;
	}
	
	
	
	/*
	
	
	public HashMap<Integer, Notice> selectMap(
			SqlSession session){
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
	}*/
	
	
	
	
	
	public Notice selectOne(SqlSession session, int noticeNo){
		Notice notice = null;
		
		notice = session.selectOne("Notice.selectOne",noticeNo);
		
		
		return notice;
	}
	
	
	
	public ArrayList<Notice> selectTitle(
			SqlSession session, String keyword){		
		
		ArrayList<Notice> list
				= new ArrayList<Notice>(session.selectList("Notice.selectTitle",keyword));
		
		return list;
	}
	
	
	
	public int insertNotice(SqlSession session, Notice n){
		int result = 0;
		
		result = session.insert("Notice.insertNotice",n);
		
		return result;
	}
	
	public int updateNotice(SqlSession session, Notice n){
		int result = 0;
		
		result = session.update("Notice.updateNotice",n);
		
		return result;
	}
	
	
	
	public int deleteNotice(SqlSession session, int noticeNo){
		int result = 0;
		
		result = session.delete("Notice.deleteNotice",noticeNo);
		
		return result;
	}
}







