package dbdbdip;
import java.sql.*;

public class Sil1 {
public static void main(String[] args){
		
		String url = "jdbc:sqlserver://210.115.229.77:2433;DatabaseName=Northwind"; // MS-SQL에 연결하기 위한 접근자
		String query = "select ProductName, UnitPrice, UnitsinStock from Products where ProductName LIKE 'C%'"; // SQL서버에서 수행할 질의
		
		Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
		Statement stmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 Statement객체
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // 드라이버 매니저를통한 연결
			con.setCatalog("Northwind"); //사용할 db를 지정 가능
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // 커서 속성 지정
			ResultSet rs = stmt.executeQuery(query); // 쿼리저장
			
			while(rs.next()){
				String col1 = rs.getString(1); // 상품명은 문자열
				int col2 = rs.getInt(2); // 상품가격은 정수형
				int col3 = rs.getInt(3); // 상품 재고는 정수형
				System.out.println("   " + col1 + " : " + col2 + " : " + col3 + " : " + col2*col3); // 가격과 재고를 곱한값 추가해서 출력
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // 에러나면 프린트
			System.err.println(se.getMessage());
		}
	}

}