package notice.model.service;

import static common.JDBCTemplate.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	public NoticeService(){}
	
	
	 private SqlSessionFactory getSessionFactory() {
	      SqlSessionFactory factory = null;
	      
	      try {
	         InputStream stream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
	         
	         SqlSessionFactoryBuilder builder 
	         					= new SqlSessionFactoryBuilder();
	         
	         factory = builder.build(stream);
	         
	      } catch (IOException e) {
	    	  
	         e.printStackTrace();
	      }
	      
	      return factory;
	   }   
	
	
	
	public ArrayList<Notice> selectList(){
		SqlSession session = getSessionFactory().openSession(false);
		
		ArrayList<Notice> list = 
				new NoticeDao().selectList(session);

		session.close();
		
		return list;
	}
	
	/*public HashMap<Integer, Notice> selectMap(){
		Connection con = getConnection();
		HashMap<Integer, Notice> listMap = 
				new NoticeDao().selectMap(con);
		close(con);
		return listMap;
	}*/
	
	public Notice selectOne(int noticeNo){
		
		SqlSession session = getSessionFactory().openSession(false);
		
		Notice n = new NoticeDao().selectOne(session, noticeNo);

		session.close();
		
		return n;
	}
	
	public ArrayList<Notice> selectTitle(String keyword){
		
		SqlSession session = getSessionFactory().openSession(false);
		
		ArrayList<Notice> list = 
				new NoticeDao().selectTitle(session, keyword);
		
		session.close();
		
		return list;
	}
	
	public int insertNotice(Notice n){
		
		SqlSession session = getSessionFactory().openSession(false);
		
		int result = new NoticeDao().insertNotice(session, n);
		
		if(result > 0)
			session.commit();
		else
			session.rollback();
		
		session.close();
		
		return result;
	}
	
	public int updateNotice(Notice n){
		
		SqlSession session = getSessionFactory().openSession(false);
		
		int result = new NoticeDao().updateNotice(session, n);
		
		if(result > 0)
			session.commit();
		else
			session.rollback();
		
		session.close();
		
		return result;
	}
	
	public int deleteNotice(int noticeNo){
		
		SqlSession session = getSessionFactory().openSession(false);
		
		int result = new NoticeDao().deleteNotice(session, noticeNo);
		
		if(result > 0)
			session.commit();
		else
			session.rollback();
		
		session.close();
		
		return result;
	}
}
