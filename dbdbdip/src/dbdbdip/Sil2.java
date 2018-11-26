package dbdbdip;
import java.sql.*;
import java.util.Scanner;

public class Sil2 {
	public static void main(String[] args){
		
		String url = "jdbc:sqlserver://210.115.229.77:2433;DatabaseName=Northwind"; // MS-SQL�� �����ϱ� ���� ������
		String query = "select CompanyName, ContactName, Phone from Customers where Country = ?"; // SQL�������� ������ ����
		
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		PreparedStatement pstmt = null; // SQL ����� �����ϰ� �� ������� �����ϱ� ���� ���ǵ� PreparedStatement��ü
		
		Scanner s = new Scanner(System.in); // �Է¹ޱ�����
		String str; // �Է¹��� ���ڿ�
		
		System.out.print("�˻��� ���� �Է� : ");
		str = s.nextLine(); // �Է�
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // ����̹� �Ŵ��������� ����
			pstmt = con.prepareStatement(query); //������ ���
			pstmt.setString(1, str); // �Է��� ���ڿ� ���
			ResultSet rs = pstmt.executeQuery(); // ���
			
			while(rs.next()){
				String col1 = rs.getString(1); // ȸ���̸��� ���ڿ�
				String col2 = rs.getString(2); // �����̸��� ���ڿ�
				String col3 = rs.getString(3); // �ڵ�����ȣ�� ���ڿ�(���, - ������)
				System.out.println("   " + col1 + " : " + col2 + " : " + col3);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(SQLException se){ // �������� ���
			System.err.println(se.getMessage());
		}
	}

}