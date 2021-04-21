package board_proj.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import board_proj.JdbcUtil;
import board_proj.dao.impl.BoardDaoImpl;
import board_proj.dto.BoardDTO;

public class BoardDAOTest {
	private static Connection con =JdbcUtil.getConnection();
	private static BoardDaoImpl dao = BoardDaoImpl.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}


	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Test
	public void testNextBoardNum() {
		System.out.println("testNextBoardNum()");
		int res = dao.nextBoardNum();
		Assert.assertNotEquals(0, res);
		System.out.println(res);
	}

	@Test
	public void testSelectListCount() {
		System.out.println("testSelectListCount()");
		int res = dao.selectListCount();
		
		Assert.assertNotEquals(-1, res);
		System.out.println("ListCount >> " + res);
		
	}

	@Test
	public void testSelectArticleList() {
		System.out.println("testSelectArticleList()");
		ArrayList<BoardDTO> list = dao.selectArticleList(1, 10);
		Assert.assertNotNull(list);
		for(BoardDTO b : list) {
			System.out.println(b);
		}
		
				
	}

	@Test
	public void testSelectArticle() {
		System.out.println("testSelectArticle()");
		BoardDTO board = dao.selectArticle(1);
		Assert.assertNotNull(board);
		System.out.println(board);
	}

	@Test
	public void testInsertArticle() {
		System.out.println("testInsertArticle");
		BoardDTO article = new BoardDTO(
				"김경연",
				"1234",
				"5시퇴근은 가능할까?",
				"불가능함",
				"test.txt"
				);
		int res = dao.insertArticle(article);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testInsertReplyArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void test08UpdateArticle() {
		System.out.println();
		int board_num=22;
		BoardDTO board = dao.selectArticle(board_num);
		int res = dao.updateArticle(board);
		Assert.assertEquals(1, res);
		
		System.out.println("res>>"+res);
	}

	@Test
	public void test08DeleteArticle() {
		System.out.println("test09DeleteArticle()");
		int board_num = dao.nextBoardNum() -1;
		int res = dao.deleteArticle(board_num);
		Assert.assertEquals(1, res);
		System.out.println("res >>" +res);
	}

	@Test
	public void testUpdateReadCount() {
		System.out.println("testUpdateReadCount()");
		int res  = dao.updateReadCount(1);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test07IsArticleBoardWriter() {
		System.out.println("isarticleboardWriter");
		int board_num = 22;
		boolean res = dao.isArticleBoardWriter(board_num, "sdf");
		Assert.assertEquals(true, res);
		System.out.println("res >> " + res);
	}

}
