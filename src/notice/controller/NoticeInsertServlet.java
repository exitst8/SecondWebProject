package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/ninsert")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드 기능이 포함된 공지글 등록 서비스 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//업로드할 파일의 용량 제한 : 10Mbyte로 제한한다면
		int maxSize = 1024 * 1024 * 10;
		
		//enctype="multipart/form-data" 로 전송되었는지 확인
		if(!ServletFileUpload.isMultipartContent(request))
			response.sendRedirect("/second/views/notice/noticeError.jsp");
		
		//해당 컨테이너의 구동중인 웹 애플리케이션의 루트 경로 알아냄
		String root = request.getSession().getServletContext().getRealPath("/");
		//업로드되는 파일이 저장될 폴더명과 경로 연결 처리
		String savePath = root + "nuploadFiles";
		//web/nuploadFiles 로 지정함	
		
		//request 를 MultipartRequest 객체로 변환함
		MultipartRequest mrequest = new MultipartRequest(request,
				savePath, maxSize, "UTF-8", 
				new DefaultFileRenamePolicy());
		
		String title = mrequest.getParameter("title");
		String fileName = mrequest.getFilesystemName("file");
		String content = mrequest.getParameter("content");
		String writer = mrequest.getParameter("writer");
		
		Notice notice = new Notice(title, writer, content, fileName);
		
		if(new NoticeService().insertNotice(notice) > 0){
			response.sendRedirect("/second/nlist");
		}else{
			response.sendRedirect("/second/views/notice/noticeError.jsp");
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
