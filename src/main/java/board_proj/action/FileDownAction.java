package board_proj.action;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;

public class FileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		String fileName = request.getParameter("downFile");
		String savePath = "boardUpload";
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		String sFilePath = sDownloadPath + "\\" + fileName;
		byte b[] = new byte[4096];
		
		FileInputStream in = new FileInputStream(sFilePath);
		String sMimeType = request.getServletContext().getMimeType(sFilePath);
		
		if(sMimeType == null)
			sMimeType = "application/octet-stream";
		
		response.setContentType(sMimeType);
		String agent = request.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident")> -1);
		
		if(ieBrowser) {
			fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		}else {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		
		ServletOutputStream out2 = response.getOutputStream();
		int numRead;
		
		while((numRead = in.read(b,0,b.length))!= -1) {
			out2.write(b,0,numRead);
		}
		out2.flush();
		out2.close();
		in.close();
		return null;
	}

}
