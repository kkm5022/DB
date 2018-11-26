package tatanic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sub1 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL�� �����ϱ� ���� ������
		String query = "Select sex from titanic where Survived = '0' "; // ������� ������ ����ϴ� ����
		
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		Statement stmt = null; // SQL ������ �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // ����̹� �Ŵ��������� ����
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ŀ�� �Ӽ� ����
			ResultSet rs = stmt.executeQuery(query); // ��������

			double count1 = 0;
			double count2 = 0;
			double count3 = 0;
			
			while(rs.next()){
				String col1 = rs.getString(1); // ��ü �����
				//System.out.println(rs.getString(1));
				
				count1++; // ��ü ����� �� ī��Ʈ
				
				if(col1.equals("male")){ // ���ڶ��
					count2++; // ���� ī��Ʈ
				}else if(col1.equals("female")){ //���ڶ��
					count3++; // ���� ī��Ʈ
				}
				
				
			}
			
			System.out.println("��ü ����� : " + count1); // ��ü ����ڸ� �����ذ� ���
			System.out.println("����� �� ���� : " + count2); // ����� �� ���� ī��Ʈ ���
			System.out.println("����� �� ���� : " + count3); // ����� �� ���� ī��Ʈ ���
			double rst = (count2/count1)*100; // ��ü ����� �� ������ ����
			double rst2 = (count3/count1)*100; // ��ü ����� �� ������ ���� 
			System.out.println("��ü ����� �� ������ ���� : " + rst + "%"); // ��ü ����� �� ������ ���� ���
			System.out.println("��ü ����� �� ������ ���� : " + rst2 + "%"); // ��ü ����� �� ������ ���� ���
			
			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // �������� ����Ʈ
			System.err.println(se.getMessage());
		}
	}

}