package notice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.*;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	public NoticeService(){}
	
	public ArrayList<Notice> selectList(){
		Connection con = getConnection();
		ArrayList<Notice> list = 
				new NoticeDao().selectList(con);
		close(con);
		return list;
	}
	
	public HashMap<Integer, Notice> selectMap(){
		Connection con = getConnection();
		HashMap<Integer, Notice> listMap = 
				new NoticeDao().selectMap(con);
		close(con);
		return listMap;
	}
	
	public Notice selectOne(int noticeNo){
		Connection con = getConnection();
		Notice n = new NoticeDao().selectOne(con, noticeNo);
		close(con);
		return n;
	}
	
	public ArrayList<Notice> selectTitle(String keyword){
		Connection con = getConnection();
		ArrayList<Notice> list = 
				new NoticeDao().selectTitle(con, keyword);
		close(con);
		return list;
	}
	
	public int insertNotice(Notice n){
		Connection con = getConnection();
		int result = new NoticeDao().insertNotice(con, n);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		
		close(con);
		return result;
	}
	
	public int updateNotice(Notice n){
		Connection con = getConnection();
		int result = new NoticeDao().updateNotice(con, n);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		
		close(con);
		return result;
	}
	
	public int deleteNotice(int noticeNo){
		Connection con = getConnection();
		int result = new NoticeDao().deleteNotice(con, noticeNo);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		
		close(con);
		return result;
	}
}
