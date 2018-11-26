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
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL�� �����ϱ� ����
															// ������
		String query = "Select * from titanic where Survived = '0' "; // ������� ��� ���� ����ϴ� ����
		
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		Statement stmt = null; // SQL ������ �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü


		try {
			con = DriverManager.getConnection(url, "20145106", "s145106@hallym"); // ����̹�
																					// �Ŵ���������
																					// ����
			System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ŀ��
																										// �Ӽ�
																										// ����
			ResultSet rs = stmt.executeQuery(query); // ��������

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

				if (!col6.equals("null")) { // age�� ���� "null"�� �ƴ� ���
					if (Double.parseDouble(rs.getString(6)) <= 20) { // age�� ���� �� �����Ѱ� 20 ������ ���
						count1++; // ������� �����ش�
//						System.out.println("\t" + col1 + "\t:" + col2 + "\t:" + col3 + "\t:" + col4 + "\t:" + col5
//								+ "\t:" + col6 + "\t:" + col7 + "\t:" + col8 + "\t:" + col9 + "\t:" + col10 + "\t:"
//								+ col11 + "\t:" + col2); // �׿� �ش��ϴ� ��� ������ ������ش�	
						save = " passengerid: " + col1 + " survived: " + col2 + " pclass: " + col3 + " name: " + col4 + " sex: " + col5
								+ " age: " + col6 + " sibsp: " + col7 + " parch: " + col8 + " ticket: " + col9 + " fare: " + col10 + " cabin: "
								+ col11 + " embarked: " + col2;
						// ���Ϸ� ����ϱ� ���� �־��ش�
						fw.write(save + "\r\n"); // ���Ϸ� ����
						
					}
				}

			}
			//System.out.println("20�� ���� ������� �� " + count1); // 20�� ���� ������� �� 
			save2 = "20�� ���� ������� �� " + count1; // ���Ϸ� ����ϱ� ����
			fw.write(save2 + "\r\n"); // ���Ϸ� ����
			rs.close();
			stmt.close();
			con.close();
			fw.close();
		} catch (SQLException se) { // �������� ����Ʈ
			System.err.println(se.getMessage());
		}
		
		while (s.hasNextLine()) { 
            System.out.println(s.nextLine());
        }

	}
}