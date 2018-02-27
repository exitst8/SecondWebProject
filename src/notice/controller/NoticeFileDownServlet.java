package notice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDownServlet
 */
@WebServlet("/nfdown")
public class NoticeFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 다운로드 서블릿 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=utf-8");
		
		String fileName = request.getParameter("path");		
		
		//저장된 폴더 경로 지정
		String saveFolder = request.getSession().getServletContext().getRealPath("/nuploadFiles");
		
		//파일 내보내기(다운로드)용 출력 스트림 객체 선언 및 생성
		ServletOutputStream downOut = response.getOutputStream();
		response.addHeader("Content-Disposition", 
				"attachment; filename=\"" 
		+ new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
				
		File downFile = new File(saveFolder + "/" + fileName);
		response.setContentLength((int)downFile.length());
		
		//폴더에서 파일 읽기용 스트림 객체 선언
		BufferedInputStream bin = 
			new BufferedInputStream(
				new FileInputStream(downFile));
		int readData = 0;
		while((readData = bin.read()) != -1){
			downOut.write(readData);
		}
		
		downOut.close();
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
