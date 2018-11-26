package tatanic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sil3 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL�� �����ϱ� ���� ������
		String query = "Select Pclass from titanic where Survived = '1' "; // �������� Ŭ������ ����ϴ� ����
		
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		Statement stmt = null; // SQL ������ �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // ����̹� �Ŵ��������� ����
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ŀ�� �Ӽ� ����
			ResultSet rs = stmt.executeQuery(query); // ��������

			double count1 = 0;
			double count2 = 0;
		
			while(rs.next()){
				String col1 = rs.getString(1); // pclass
				//System.out.println(rs.getString(1));
				
				count2++; // ��ü ������ ī��Ʈ 
				
				if(col1.equals("1")){ // 1����̶��
						count1++; // 1��޿� ź ������ ī��Ʈ
				}
				
				
			}
			
			System.out.println("��ü ������ : " + count2); // ��ü �����ڸ� �����ذ� ���
			System.out.println("������ �� 1��޿� ź ��� : " + count1); // ������ �� 1��޿� ź ī��Ʈ ���
			double rst = (count1/count2)*100; // ��ü ������ �� 1��޿� ź �������� ���� 
			System.out.println("��ü ������ �� 1��޿� ź �������� ���� : " + rst + "%"); // ��ü ������ �� 1��޿� ź �������� ���� ���
			
			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // �������� ����Ʈ
			System.err.println(se.getMessage());
		}
	}

}