package member.model.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.model.dao.MemberDao;
import member.model.vo.Member;

// DAO단위로 이뤄지던 DB 열고 닫던 행위를 Service 단위로 이뤄지게 변경함
// 메서드에 con을 넘기던 것을 session을 넘긴다
public class MemberService {
   public MemberService(){}

   private SqlSessionFactory getSessionFactory() {
      SqlSessionFactory factory = null;
      
      try {
         InputStream stream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
         SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
         factory = builder.build(stream);
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return factory;
   }   
   
   public Member selectMember(String userId, String userPwd){
      //Connection con = getConnection();
      SqlSession session = getSessionFactory().openSession(false);
      
      Member loginMember = new MemberDao().selectMember(session, userId, userPwd);
      
      //close(con);
      session.close();
      
      return loginMember;
   }

   public Member checkIdDup(String userId){
      //Connection con = getConnection();   
      SqlSession session = getSessionFactory().openSession(false);
      
      Member chkMemberId = new MemberDao().checkIdDup(session, userId);
      
      //close(con);
      session.close();
      
      return chkMemberId;
   }   
   
   public int insertMember(Member m) {
      //Connection con = getConnection();
      SqlSession session = getSessionFactory().openSession(false);
      
      int result = new MemberDao().insertMember(session, m);
      
      /*
      if(result > 0) commit(con);
      else rollback(con);
      */
      // 계산된 행의 결과가 1개 이상 이면 커밋 없으면 롤백
      if(result > 0) session.commit();
      else session.rollback();
      
      //close(con);
      session.close();
      
      return result;
   }

   public int updateMember(Member m) {
      //Connection con = getConnection();
      SqlSession session = getSessionFactory().openSession(false);
      
      int result = new MemberDao().updateMember(session, m);
      
      /*
      if(result > 0) commit(con);
      else rollback(con);
      */
      // 계산된 행의 결과가 1개 이상 이면 커밋 없으면 롤백
      if(result > 0) session.commit();
      else session.rollback();
      
      //close(con);   
      session.close();
      
      return result;
   }

   public int deleteMember(String userId) {
      //Connection con = getConnection();
      SqlSession session = getSessionFactory().openSession(false);
      
      int result = new MemberDao().deleteMember(session, userId);
      
      /*
      if(result > 0) commit(con);
      else rollback(con);
      */
      // 계산된 행의 결과가 1개 이상 이면 커밋 없으면 롤백
      if(result > 0) session.commit();
      else session.rollback();
      
      //close(con);
      session.close();
      return result;
   }

}





