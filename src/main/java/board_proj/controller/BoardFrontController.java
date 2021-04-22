package board_proj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.NullAction;
import board_proj.dto.ActionForward;

@WebServlet(urlPatterns = { "*.do" }, loadOnStartup = 1, // 무조건 처음 실행
		initParams = { @WebInitParam(name = "configFile", value = "WEB-INF/commandAction.properties") }// 초기화를 넘겨주는
																										// 매게변수}
)
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// hashmap생성
	private Map<String, Action> actionMap = new HashMap<>();

	// ↓이게 먼저 수행됨
	@Override
	public void init(ServletConfig config) throws ServletException {
		// getInitParameter()
		System.out.println("init() - config" + config.getInitParameter("configFile"));
		String configFile =config.getInitParameter("configFile");
		try (InputStream is = config.getServletContext().getResourceAsStream(configFile)) {
			Properties props = new Properties();
			props.load(is);
			Action action =null;
			System.out.println("props>>" + props);
			for(Entry<Object, Object> entry: props.entrySet()) {
				try {
					System.out.println("KEY>>>"+entry.getKey()+":"+"value>>>"+entry.getValue());
					Class<?> cls = Class.forName((String)entry.getValue());
					action = (Action)cls.newInstance();
				} catch (ClassNotFoundException e) {
					action = new NullAction();
					e.printStackTrace();
				}
				actionMap.put((String)entry.getKey(), action);
			}
			//확인
			for(Entry<String, Action> entry :actionMap.entrySet()) {
				System.out.println("KEY>>>"+entry.getKey()+":"+"value>>>"+entry.getValue());
			}
		}catch (IOException|InstantiationException|IllegalAccessException e) {
			// TODO: handle exception
		} 

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String RequestURI = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String command=RequestURI.substring(contextPath.length());   
//		System.out.println(RequestURI + " >> " + contextPath + " >> " + command);

		String command = request.getServletPath(); // 3줄코드를 한번에 압축
		System.out.println(command);

		ActionForward forward = null;
		Action action = null;
		
		//커멘트패턴 적용
		action = actionMap.get(command);
		forward = action.execute(request, response);
/*		
		if (command.equals("/boardWriteForm.do")) {
			action = new BoardWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/boardWritePro.do")) {
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/boardList.do")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardDetail.do")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardDeleteForm.do")) {
			action = new BoardDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardDeletePro.do")) {
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/file_down.do")) {
			action = new FileDownAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyForm.do")) {
			action = new BoardModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyPro.do")) {
			// boardDeletePro.do?board_num=37&page=1&Board_Pass=aaa
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardReplyForm.do")) {
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/boardReplyPro.do")) {
			action = new BoardReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
*/
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}

	}
}