package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardDeleteProService;
import board_proj.service.BoardModifyProService;
import board_proj.service.BoardWriteService;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int board_num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		String pass = request.getParameter("BOARD_PASS");
		int page = Integer.parseInt(request.getParameter("page"));

		BoardDTO article = new BoardDTO();
		BoardModifyProService service = new BoardModifyProService();

		boolean isModifySuccess = false;
		boolean isRightUser = service.isArticleWriter(board_num, pass);

		ActionForward forward = null;

		if (!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			getMessage(response,"수정할 권한이 없습니다.");
		} else {
			article.setBoard_num(board_num);
			article.setBoard_subject(request.getParameter("BOARD_SUBJECT"));
			article.setBoard_content(request.getParameter("BOARD_CONTENT"));
			isModifySuccess = service.modifyArticle(article);
			
			if (!isModifySuccess) {
//				response.setContentType("text/html;charset=UTF-8");
				getMessage(response,"수정할 권한이 없습니다.");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.do?board_num=" + article.getBoard_num()+"&page="+page);
			}

		}

		return forward;

	}

	private void getMessage(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "')");
		out.println("history.back();");
		out.println("</script>");
	}

}
