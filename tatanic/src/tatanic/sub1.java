package tatanic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sub1 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL에 연결하기 위한 접근자
		String query = "Select sex from titanic where Survived = '0' "; // 사망자의 성별을 출력하는 쿼리
		
		Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
		Statement stmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 Statement객체
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // 드라이버 매니저를통한 연결
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // 커서 속성 지정
			ResultSet rs = stmt.executeQuery(query); // 쿼리저장

			double count1 = 0;
			double count2 = 0;
			double count3 = 0;
			
			while(rs.next()){
				String col1 = rs.getString(1); // 전체 사망자
				//System.out.println(rs.getString(1));
				
				count1++; // 전체 사망자 수 카운트
				
				if(col1.equals("male")){ // 남자라면
					count2++; // 남자 카운트
				}else if(col1.equals("female")){ //여자라면
					count3++; // 여자 카운트
				}
				
				
			}
			
			System.out.println("전체 사망자 : " + count1); // 전체 사망자를 세어준거 출력
			System.out.println("사망자 중 남자 : " + count2); // 사망자 중 남자 카운트 출력
			System.out.println("사망자 중 여자 : " + count3); // 사망자 중 여자 카운트 출력
			double rst = (count2/count1)*100; // 전체 사망자 중 남자의 비율
			double rst2 = (count3/count1)*100; // 전체 사망자 중 여자의 비율 
			System.out.println("전체 사망자 중 남자의 비율 : " + rst + "%"); // 전체 사망자 중 남자의 비율 출력
			System.out.println("전체 사망자 중 여자의 비율 : " + rst2 + "%"); // 전체 사망자 중 여자의 비율 출력
			
			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // 에러나면 프린트
			System.err.println(se.getMessage());
		}
	}

}
