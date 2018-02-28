package member.model.dao;

import org.apache.ibatis.session.SqlSession;

import member.model.vo.Member;

public class MemberDao {
   public MemberDao(){}

   public Member selectMember(SqlSession session, String userId, String userPwd) {
      Member m = null;
      
      m = new Member(userId, userPwd);
      m = session.selectOne("Member.loginMember", m);
      System.out.println(m);

      return m;
   }
 
   public Member checkIdDup(SqlSession session, String userId) {
      Member m = session.selectOne("Member.IdDupCheckMember", userId);
      
      return m;
   }
   
   public int insertMember(SqlSession session, Member m) {
      int result = 0;
      
      result = session.insert("Member.insertMember", m);
      return result;
   }
   
   public int updateMember(SqlSession session, Member m) {
      int result = 0;
      
      result = session.update("Member.updateMember", m);
      return result;
   }
   
   public int deleteMember(SqlSession session, String userId) {
      int result = 0;

      result = session.delete("Member.deleteMember", userId);
      return result;      
   }
   
   /* MyBatis 쓰기전 JSP 문법
   public Member selectMember(Connection con, String userId, String userPwd) {
      Member member = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "select * from member "
            + "where id = ? and passwd = ?";
      
      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userId);
         pstmt.setString(2, userPwd);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()){
            member = new Member();
            
            member.setId(userId);
            member.setPasswd(userPwd);
            member.setName(rset.getString("name"));
            member.setEmail(rset.getString("email"));
            member.setGender(rset.getString("gender"));   
            member.setAge(rset.getInt("age"));
            member.setPhone(rset.getString("phone"));
            member.setAddress(rset.getString("address"));
            member.setEnrollDate(rset.getDate("enroll_date"));            
         }
               
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         close(rset);
         close(pstmt);
      }
      
      return member;
   }   
   
   public Member selectMember(Connection con, String userId) {
      Member member = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "select * from member "
            + "where id = ?";
      
      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userId);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()){
            member = new Member();
            
            member.setId(userId);
            member.setPasswd(rset.getString("passwd"));
            member.setName(rset.getString("name"));
            member.setEmail(rset.getString("email"));
            member.setGender(rset.getString("gender"));   
            member.setAge(rset.getInt("age"));
            member.setPhone(rset.getString("phone"));
            member.setAddress(rset.getString("address"));
            member.setEnrollDate(rset.getDate("enroll_date"));            
         }
               
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         close(rset);
         close(pstmt);
      }
      
      return member;
   }
   public int insertMember(Connection con, Member m) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "insert into member "
            + "values (?, ?, ?, ?, ?, ?, ?, ?, default)";
      
      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, m.getId());
         pstmt.setString(2, m.getPasswd());
         pstmt.setString(3, m.getName());
         pstmt.setString(4, m.getEmail());
         pstmt.setString(5, m.getGender());
         pstmt.setInt(6, m.getAge());
         pstmt.setString(7, m.getPhone());
         pstmt.setString(8, m.getAddress());
         
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         close(pstmt);
      }            
               
      return result;
   }

   public int updateMember(Connection con, Member m) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "update member "
            + "set passwd = ?, email = ?, age = ?, "
            + "phone = ?, address = ? "
            + "where id = ?";
      
      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, m.getPasswd());
         pstmt.setString(2, m.getEmail());
         pstmt.setInt(3, m.getAge());
         pstmt.setString(4, m.getPhone());
         pstmt.setString(5, m.getAddress());
         pstmt.setString(6, m.getId());         
         
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         close(pstmt);
      }            
               
      return result;
   }

   public int deleteMember(Connection con, String userId) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "delete from member where id = ?";
      
      try {
         pstmt = con.prepareStatement(query);         
         pstmt.setString(1, userId);         
         
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         close(pstmt);
      }            
               
      return result;
   }
    */
   
   
}








