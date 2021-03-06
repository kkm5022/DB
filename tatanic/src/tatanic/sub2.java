package tatanic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;


public class sub2 {
	public static void main(String[] args) throws IOException {
		
		File file = new File("C:/Hallym/dd.txt") ;
		FileWriter fw = new FileWriter(file); 
		Scanner s = new Scanner(file);
		String save;
		String save2;
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL에 연결하기 위한
															// 접근자
		String query = "Select * from titanic where Survived = '0' "; // 사망자의 모든 정보 출력하는 쿼리
		
		Connection con = null; // SQL서버에 세션연결을 수행할 COnnection 인터페이스
		Statement stmt = null; // SQL 명령을 수행하고 그 결과값을 저장하기 위해 정의된 Statement객체


		try {
			con = DriverManager.getConnection(url, "20145106", "s145106@hallym"); // 드라이버
																					// 매니저를통한
																					// 연결
			System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // 커서
																										// 속성
																										// 지정
			ResultSet rs = stmt.executeQuery(query); // 쿼리저장

			double count1 = 0;

			while (rs.next()) {
				String col1 = rs.getString(1); // passengerid
				String col2 = rs.getString(2); // survived
				String col3 = rs.getString(3); // pclass
				String col4 = rs.getString(4); // name
				String col5 = rs.getString(5); // sex
				String col6 = rs.getString(6); // age
				String col7 = rs.getString(7); // sibsp
				String col8 = rs.getString(8); // parch
				String col9 = rs.getString(9); // ticket
				String col10 = rs.getString(10); // fare
				String col11 = rs.getString(11); // cabin
				String col12 = rs.getString(12); // embarked

				if (!col6.equals("null")) { // age가 문자 "null"이 아닐 경우
					if (Double.parseDouble(rs.getString(6)) <= 20) { // age를 숫자 로 변경한게 20 이하일 경우
						count1++; // 몇명인지 세어준다
//						System.out.println("\t" + col1 + "\t:" + col2 + "\t:" + col3 + "\t:" + col4 + "\t:" + col5
//								+ "\t:" + col6 + "\t:" + col7 + "\t:" + col8 + "\t:" + col9 + "\t:" + col10 + "\t:"
//								+ col11 + "\t:" + col2); // 그에 해당하는 모든 정보를 출력해준다	
						save = " passengerid: " + col1 + " survived: " + col2 + " pclass: " + col3 + " name: " + col4 + " sex: " + col5
								+ " age: " + col6 + " sibsp: " + col7 + " parch: " + col8 + " ticket: " + col9 + " fare: " + col10 + " cabin: "
								+ col11 + " embarked: " + col2;
						// 파일로 출력하기 위해 넣어준다
						fw.write(save + "\r\n"); // 파일로 쓰기
						
					}
				}

			}
			//System.out.println("20세 이하 사망자의 수 " + count1); // 20세 이하 사망자의 수 
			save2 = "20세 이하 사망자의 수 " + count1; // 파일로 출력하기 위해
			fw.write(save2 + "\r\n"); // 파일로 쓰기
			rs.close();
			stmt.close();
			con.close();
			fw.close();
		} catch (SQLException se) { // 에러나면 프린트
			System.err.println(se.getMessage());
		}
		
		while (s.hasNextLine()) { 
            System.out.println(s.nextLine());
        }

	}
}
