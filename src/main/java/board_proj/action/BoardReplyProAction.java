package board_proj.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardReplyProService;

public class BoardReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		String nowPage = request.getParameter("page");
		BoardDTO article = new BoardDTO();
		article.setBoard_num(Integer.parseInt(request.getParameter("BOARD_NUM")));
		article.setBoard_name(request.getParameter("BOARD_NAME"));
		article.setBoard_pass(request.getParameter("BOARD_PASS"));
		article.setBoard_subject(request.getParameter("BOARD_SUBJECT"));
		article.setBoard_content(request.getParameter("BOARD_CONTENT"));
		article.setBoard_re_ref(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		article.setBoard_re_lev(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
		article.setBoard_re_seq(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
		System.out.println(article);
		BoardReplyProService boardReplyProService = new BoardReplyProService();
		boolean isReplySuccess = boardReplyProService.replyArticle(article);

		if (isReplySuccess) {
			forward = new ActionForward();
//			forward = new ActionForward("boardList.do?page="+nowPage, true);
			forward.setRedirect(true);
			forward.setPath("boardList.do?page=" + nowPage);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답장실패')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}
}
