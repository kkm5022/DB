package dbdbdip;
import java.sql.*;
import java.util.Scanner;

public class Sil2 {
	public static void main(String[] args){
		
		String url = "jdbc:sqlserver://210.115.229.77:2433;DatabaseName=Northwind"; // MS-SQL에 연결하기 위한 접근자
		String query = "select CompanyName, ContactName, Phone from Customers where Country = ?"; // SQL서버에서 수행할 질의
		
		Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
		PreparedStatement pstmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 PreparedStatement객체
		
		Scanner s = new Scanner(System.in); // 입력받기위해
		String str; // 입력받을 문자열
		
		System.out.print("검색할 나라 입력 : ");
		str = s.nextLine(); // 입력
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // 드라이버 매니저를통한 연결
			pstmt = con.prepareStatement(query); //쿼리문 사용
			pstmt.setString(1, str); // 입력한 문자열 사용
			ResultSet rs = pstmt.executeQuery(); // 결과
			
			while(rs.next()){
				String col1 = rs.getString(1); // 회사이름은 문자열
				String col2 = rs.getString(2); // 연결이름은 문자열
				String col3 = rs.getString(3); // 핸드폰번호는 문자열(길고, - 떄문에)
				System.out.println("   " + col1 + " : " + col2 + " : " + col3);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(SQLException se){ // 에러나면 출력
			System.err.println(se.getMessage());
		}
	}

}