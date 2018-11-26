package dbdbdip;
import java.sql.*;

public class Ex4 {
	   Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
	   Statement stmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 Statement객체
	   ResultSet rs = null;
	   
	   public static void main(String[] args) {
	      Ex4 exam4 = new Ex4();
	      exam4.Execute();
	      //Ex4 클래스 객체를 사용하여 SQL서버에 접속 및 질의 수행

	   }
	   
	   public Ex4() {
	      try {
	         con = DriverManager.getConnection("jdbc:inetdae7://210.115.229.77:2433", "20145106", "s145106@hallym"); // MS-SQL에 연결하기 위한 접근자
	         con.setCatalog("20145106");
	         System.out.println("Connected...");
	      }
	      catch(SQLException se) {
	         System.err.println(se.getMessage());
	      }
	   }
	   
	   public void Execute() {
	      try {
	         stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); // 커서 속성 지정
	         rs = stmt.executeQuery("select * from score");
	         
	         rs.absolute(4); // 4번째 줄
	         rs.deleteRow(); // 줄삭제
	         rs.moveToInsertRow(); // 줄 이동?
	         rs.updateString("s_id", "954522"); // 업데이트
	         rs.updateInt("korean", 75); //업데이트
	         rs.updateInt("english", 95); //업데이트
	         rs.updateInt("math", 100); //업데이트
	         rs.insertRow(); // 줄삽입
	         rs.absolute(2); // 2번째 줄로 이동
	         rs.updateInt("korean", 95); //업데이트
	         rs.updateRow(); // 줄 업데이트
	         rs.refreshRow(); // 줄 새로고침?
	         
	         System.out.println("s_id     Korean English Math");
	         
	         if(rs.first()) {
	            do {
	               String s_id = rs.getString(1);
	               int korean = rs.getInt(2);
	               int english = rs.getInt(3);
	               int math = rs.getInt(4);
	               System.out.println(s_id + "   " + korean + "\t" + english + "\t" + math);
	            }while(rs.next());
	         }
	      }
	      catch(SQLException se) {
	         System.err.println(se.getMessage());
	      }
	   }
	   
	   public void Close() {
	      try {
	         con.close();
	         stmt.close();
	         rs.close();
	      }
	      catch(SQLException se) {
	         System.err.println(se.getMessage());
	      }
	      System.out.println("Disconnected...");
	   }
	}

