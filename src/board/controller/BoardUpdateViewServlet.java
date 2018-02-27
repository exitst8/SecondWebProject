package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardUpdateViewServlet
 */
@WebServlet("/bupview")
public class BoardUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 원글 추가 등록 처리용 컨트롤러
				response.setContentType("text/html; charset=utf-8");
				// 게시글 상세보기 처리용

				int boardNum = Integer.parseInt(request.getParameter("bnum"));
				int currentPage = Integer.parseInt(request.getParameter("page"));
						
				BoardService bservice = new BoardService();

				bservice.addReadCount(boardNum); 
				// 해당 게시글의 조회수 1 증가 처리
				Board board = bservice.selectBoard(boardNum);
				
				RequestDispatcher view = null;
				if(board != null){
					view = request.getRequestDispatcher("views/board/boardUpdateForm.jsp");
					request.setAttribute("board", board);
					request.setAttribute("currentPage", currentPage);
					view.forward(request, response);
				}else{
					view = request.getRequestDispatcher("views/board/boardError.jsp");
					request.setAttribute("message", "게시글 상세조회 실패!");
					view.forward(request, response);
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
