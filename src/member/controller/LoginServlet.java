package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("전송방식 get 으로 연결");
		//System.out.println("로그인 서블릿 구동...");
		
		//1. 전송값에 한글이 있을 경우, 문자셋 인코딩처리함
		//  응답 보낼 값에도 한글이 있을 경우 인코딩처리함
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//2. 전송값 꺼내서 변수에 기록하기
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		//System.out.println(userId + ", " + userPwd);
		
		//3. 비즈니스 로직 처리용 모델 객체 생성하고,
		//필요한 메소드 구동하고 처리결과 받는다.
		Member member = new MemberService()
				.selectMember(userId, userPwd);
		
		//4. 전송값에 따라 성공/실패에 대한 
		//뷰를 선택해서 응답처리함
		if(member != null){ //로그인 성공시
			//System.out.println(member);
			HttpSession session = request.getSession();
			//세션객체 자동 제거시간 설정
			//session.setMaxInactiveInterval(1800);
			
			//System.out.println(session.getId());
			session.setAttribute("member", member);			
			response.sendRedirect("index.jsp");
		}else{ //로그인 실패시
			response.sendRedirect("views/member/memberError.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("전송방식 post 와 연결");
		doGet(request, response);
	}

}
