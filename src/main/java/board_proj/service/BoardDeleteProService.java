package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDTO;

public class BoardDeleteProService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardDeleteProService() {
		dao.setCon(con);
	}
	
	public boolean isArticleWriter(int board_num, String pass) {
		return dao.isArticleBoardWriter(board_num, pass);
	}
	
	public boolean removeArticle(int board_num) {
		return dao.deleteArticle(board_num)== 1 ? true : false;
	}
	
}
