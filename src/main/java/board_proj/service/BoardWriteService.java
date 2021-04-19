package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDTO;

public class BoardWriteService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardWriteService() {
		dao.setCon(con);
	}
	
	public boolean registerArticle(BoardDTO boardDto) {
		return dao.insertArticle(boardDto) == 1 ? true : false;
	}
	
}
