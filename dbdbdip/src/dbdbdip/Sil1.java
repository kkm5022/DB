package dbdbdip;
import java.sql.*;

public class Sil1 {
public static void main(String[] args){
		
		String url = "jdbc:sqlserver://210.115.229.77:2433;DatabaseName=Northwind"; // MS-SQL�� �����ϱ� ���� ������
		String query = "select ProductName, UnitPrice, UnitsinStock from Products where ProductName LIKE 'C%'"; // SQL�������� ������ ����
		
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		Statement stmt = null; // SQL ����� �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // ����̹� �Ŵ��������� ����
			con.setCatalog("Northwind"); //����� db�� ���� ����
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ŀ�� �Ӽ� ����
			ResultSet rs = stmt.executeQuery(query); // ��������
			
			while(rs.next()){
				String col1 = rs.getString(1); // ��ǰ���� ���ڿ�
				int col2 = rs.getInt(2); // ��ǰ������ ������
				int col3 = rs.getInt(3); // ��ǰ ���� ������
				System.out.println("   " + col1 + " : " + col2 + " : " + col3 + " : " + col2*col3); // ���ݰ� ��� ���Ѱ� �߰��ؼ� ���
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // �������� ����Ʈ
			System.err.println(se.getMessage());
		}
	}

}