package board_proj.ds;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiDS {
	private static DataSource ds;

	public JndiDS() {}
	
	//static 생성자 호출전 먼저 실행
	static {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/board_proj");
			System.out.println("ds : "+ ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
