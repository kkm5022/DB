package tatanic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sil2 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL�� �����ϱ� ���� ������
		String query = "Select age,sex from titanic where Survived = '1' ";
		
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		Statement stmt = null; // SQL ������ �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // ����̹� �Ŵ��������� ����
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ŀ�� �Ӽ� ����
			ResultSet rs = stmt.executeQuery(query); // ��������

			int count1 = 0;
			
			double sum1 = 0;
			
			while(rs.next()){
				
				String col1 = rs.getString(1); // ���̸� �޾Ƶ��δ�
				String col2 = rs.getString(2); // ������ �޾Ƶ��δ�
				if(!col1.equals("null")){ // ���̰� ���� �״�� "null"�� �ƴ� ��
					if(rs.getString(2).equals("female")){ // ������ female�̶�� 
						count1++; // �����ش�
						sum1 += Double.parseDouble(rs.getString(1)); // ����� ���ϱ� ���� ���̸� �����ش�
					}
				}
				
			}
			System.out.println("���� ������ �� : " + count1); // ���� ������ �� ���
			System.out.println("������ ������ ���� ��� = " + sum1/count1); // ���� ������ ��� ���� ��� 

			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // �������� ����Ʈ
			System.err.println(se.getMessage());
		}
	}

}
