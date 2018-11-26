package dbdbdip;
import java.sql.*;

public class Ex2 {
	public static void main(String[] args){
		
		String url = "jdbc:sqlserver://210.115.229.77:2433;DatabaseName=Northwind";
		String query = "select OrderID, CustomerID, EmployeeID from orders where EmployeeID = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 4);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int col1 = rs.getInt(1);
				String col2 = rs.getString(2);
				int col3 = rs.getInt(3);
				System.out.println("   " + col1 + " : " + col2 + " : " + col3);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(SQLException se){
			System.err.println(se.getMessage());
		}
	}

}
