package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardTopListServlet
 */
@WebServlet("/toplist")
public class BoardTopListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardTopListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				ArrayList<Board> list = new ArrayList<Board>();
				list = new BoardService().selectList();
				
				//전송할 최종 json 객체
				JSONObject json = new JSONObject();
				JSONArray jarr = new JSONArray();
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				for(Board b : list){
					//한 사람의 정보를 저장할 json 객체
					JSONObject board = new JSONObject();
					board.put("bnum", b.getBoardNum());
					board.put("title", URLEncoder.encode(b.getBoardTitle(), "UTF-8"));
					//json에서 한글 깨짐을 막으려면, java.net.URLEncoder 클래스의 encode() 메소드로 인코딩 처리
					board.put("writer", b.getBoardWriter());
					board.put("date", df.format(b.getBoardDate()));
					board.put("cnt",b.getBoardReadCount());
					board.put("file", b.getBoardOriginalFileName());	
					
					jarr.add(board);
				}
				
				json.put("list", jarr);
				//System.out.println(json.toJSONString());
				response.setContentType("application/json"); 
				PrintWriter out = response.getWriter();
				out.print(json.toJSONString());
				out.flush();
				out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
