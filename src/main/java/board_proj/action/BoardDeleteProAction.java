package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.service.BoardDeleteProService;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// boardDeletePro.do?board_num=37
		// hiddenpage=1
		// BOARD_PASS=aaaa

		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		String pass = request.getParameter("BOARD_PASS");

		BoardDeleteProService service = new BoardDeleteProService();
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);

		ActionForward forward = null;
		if (!isArticleWriter) {
			getMessage(response, "삭제할 권한이 없습니다.");
			return forward;
		}
		boolean isDeleteSuccess = service.removeArticle(board_num);
		if (!isDeleteSuccess) {
			getMessage(response, "삭제실패");
			return forward;
		}
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardList.do?page=" + page);
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
