package tatanic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sil2 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL에 연결하기 위한 접근자
		String query = "Select age,sex from titanic where Survived = '1' ";
		
		Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
		Statement stmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 Statement객체
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // 드라이버 매니저를통한 연결
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // 커서 속성 지정
			ResultSet rs = stmt.executeQuery(query); // 쿼리저장

			int count1 = 0;
			
			double sum1 = 0;
			
			while(rs.next()){
				
				String col1 = rs.getString(1); // 나이를 받아들인다
				String col2 = rs.getString(2); // 성별을 받아들인다
				if(!col1.equals("null")){ // 나이가 문자 그대로 "null"이 아닐 때
					if(rs.getString(2).equals("female")){ // 성별이 female이라면 
						count1++; // 세어준다
						sum1 += Double.parseDouble(rs.getString(1)); // 평균을 구하기 위해 나이를 더해준다
					}
				}
				
			}
			System.out.println("생존 여성의 수 : " + count1); // 생존 여성의 수 출력
			System.out.println("생존한 여성의 나이 평균 = " + sum1/count1); // 생존 여성의 평균 나이 출력 

			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // 에러나면 프린트
			System.err.println(se.getMessage());
		}
	}

}

