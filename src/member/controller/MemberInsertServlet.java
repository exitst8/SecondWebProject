package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/minsert")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 처리용 컨트롤러
		//1. 전송값에 한글이 있을 경우 문자인코딩 처리
		//응답처리에 컨텐츠타입 지정
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//2. 전송값 꺼내서 변수에 기록하기
		String userId = request.getParameter("userid");
		String userName = request.getParameter("name");
		String userPwd = request.getParameter("userpwd");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		StringBuilder sb = new StringBuilder();
		sb.append(request.getParameter("post") + ",");
		sb.append(request.getParameter("address1") + ",");
		sb.append(request.getParameter("address2"));
		String address = sb.toString();
		
		Member m = new Member(userId, userPwd, 
				userName, email, gender, age, phone,
				address);
		//System.out.println("m : " + m);
		
		//3. 비즈니스로직 처리용 모델 객체 생성과 메소드 호출
		//리턴값 받기
		int result = new MemberService().insertMember(m);
		
		//4. 리턴값을 가지고 성공/실패에 대한 뷰페이지를 내보냄
		if(result > 0){ //가입 성공
			response.sendRedirect("/second/index.jsp");
		}else{ //가입 실패
			response.sendRedirect("views/member/memberError.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
