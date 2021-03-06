package tatanic;
import java.sql.*;

public class sil1 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL에 연결하기 위한 접근자
		String query = "Select age,Embarked from titanic where Survived = '1' ";
		
		Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
		Statement stmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 Statement객체
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // 드라이버 매니저를통한 연결
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // 커서 속성 지정
			ResultSet rs = stmt.executeQuery(query); // 쿼리저장

			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;
			
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double sum4 = 0;
			
			while(rs.next()){
				
				String col1 = rs.getString(1); // 나이
				String col2 = rs.getString(2); // 승선위치
				if(!col1.equals("null")){ // 나이가 문자 그자체로 "null"이 아닐때
					if(rs.getString(2).equals("C")){ // 승선위치가 C이면
						count1++; // 평균을 구하기 위해 승선위치가 C인 애들 카운트
						sum1 += Double.parseDouble(rs.getString(1)); // 평균을 구하기 위해 나이의 총합을 더해준다(문자열이기때문에 double형으로변환)
					}else if(rs.getString(2).equals("S")){ // 승선위치가 S이면
						count2++; // 평균을 구하기 위해 승선위치가 S인 애들 카운트
						sum2 += Double.parseDouble(rs.getString(1)); // 평균을 구하기 위해 나이의 총합을 더해준다(문자열이기때문에 double형으로변환)
					}else if(rs.getString(2).equals("Q")){ // 승선위치가 Q이면
						count3++; // 평균을 구하기 위해 승선위치가 Q인 애들 카운트
						sum3 += Double.parseDouble(rs.getString(1)); // 평균을 구하기 위해 나이의 총합을 더해준다(문자열이기때문에 double형으로변환)
					}else{
						count4++; // 평균을 구하기 위해 승선위치가 1인 애들 카운트
						sum4 += Double.parseDouble(rs.getString(1)); // 평균을 구하기 위해 나이의 총합을 더해준다(문자열이기때문에 double형으로변환)
					}
					
				}
				
			}
			System.out.println("C평균 = " + sum1/count1); // 승선위치 C의 평균
			System.out.println("S평균 = " + sum2/count2); // 승선위치 S의 평균
			System.out.println("Q평균 = " + sum3/count3); // 승선위치 Q의 평균
			System.out.println("null평균 = " + sum4/count4); // 승선위치가 나와있지 않은 애들의 평균
			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // 에러나면 프린트
			System.err.println(se.getMessage());
		}
	}

}