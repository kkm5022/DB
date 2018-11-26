package dbdbdip;

import java.sql.*;

public class EX3 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL�� �����ϱ� ���� ������
		String query = "Select OrderID, CustomerID, EmployeeID from orders"; // SQL�������� ������ ����
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		Statement stmt = null; // SQL ����� �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü

		try {
			con = DriverManager.getConnection(url, "20145106", "s145106@hallym"); // ����̹��Ŵ��������ѿ���
			con.setCatalog("Northwind"); // ����� db�� ��������
			System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ŀ�� �Ӽ� ����
			ResultSet rs = stmt.executeQuery(query); // ��������
			rs.afterLast(); // Ŀ���� ���� ������ ���� ���� Ŀ���� �̵�

			if (rs.isAfterLast() == true) { // ������ ���� �������̶��
				while (rs.previous()) { //Ŀ���� ���������� �̵� ù �ຸ�� ���̸� false��ȯ
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
