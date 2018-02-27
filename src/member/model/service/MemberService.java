package member.model.service;

import static common.JDBCTemplate.*;
import java.sql.*;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	public MemberService(){}
	
	public Member selectMember(String userId, String userPwd){
		Connection con = getConnection();
		Member loginMember = 
			new MemberDao().selectMember(con, userId, userPwd);
		close(con);
		return loginMember;
	}

	public int insertMember(Member m) {
		Connection con = getConnection();
		int result = new MemberDao().insertMember(con, m);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		
		close(con);		
		return result;
	}

	public int updateMember(Member m) {
		Connection con = getConnection();
		int result = new MemberDao().updateMember(con, m);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		
		close(con);		
		return result;
	}

	public int deleteMember(String userId) {
		Connection con = getConnection();
		int result = new MemberDao().deleteMember(con, userId);
		
		if(result > 0)
			commit(con);
		else
			rollback(con);
		
		close(con);		
		return result;
	}

	public Member checkIdDup(String userId){
		Connection con = getConnection();
		Member chkMemberId = 
			new MemberDao().selectMember(con, userId);
		close(con);
		return chkMemberId;
	}
}






