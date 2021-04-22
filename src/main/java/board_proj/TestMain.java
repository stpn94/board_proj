package board_proj;

public class TestMain {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//클레스 이름
		String className = "board_proj.Sum";
		//클래스 이름 가져오기
		Class<?> cls = Class.forName(className);
		//생성
		Sum s = (Sum) cls.newInstance();
		
		//원래 우리가 했던 생성
		Sum s2=new Sum();
		
		s.add(5,3);
		s2.add(5, 2);
	}

}
