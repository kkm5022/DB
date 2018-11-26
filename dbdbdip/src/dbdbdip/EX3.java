package dbdbdip;

import java.sql.*;

public class EX3 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL에 연결하기 위한 접근자
		String query = "Select OrderID, CustomerID, EmployeeID from orders"; // SQL서버에서 수행할 질의
		Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
		Statement stmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 Statement객체

		try {
			con = DriverManager.getConnection(url, "20145106", "s145106@hallym"); // 드라이버매니저를통한연결
			con.setCatalog("Northwind"); // 사용할 db를 지정가능
			System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // 커서 속성 지정
			ResultSet rs = stmt.executeQuery(query); // 쿼리저장
			rs.afterLast(); // 커서를 가장 마지막 행의 다음 커서로 이동

			if (rs.isAfterLast() == true) { // 마지막 행의 다음행이라면
				while (rs.previous()) { //커서를 이전행으로 이동 첫 행보다 앞이면 false반환
					int col1 = rs.getInt(1);
					String col2 = rs.getString(2);
					int col3 = rs.getInt(3);
					System.out.println("   " + col1 + " : " + col2 + " : " + col3);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
}
