package dbdbdip;
import java.sql.*;

public class Ex4 {
	   Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
	   Statement stmt = null; // SQL ����� �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü
	   ResultSet rs = null;
	   
	   public static void main(String[] args) {
	      Ex4 exam4 = new Ex4();
	      exam4.Execute();
	      //Ex4 Ŭ���� ��ü�� ����Ͽ� SQL������ ���� �� ���� ����

	   }
	   
	   public Ex4() {
	      try {
	         con = DriverManager.getConnection("jdbc:inetdae7://210.115.229.77:2433", "20145106", "s145106@hallym"); // MS-SQL�� �����ϱ� ���� ������
	         con.setCatalog("20145106");
	         System.out.println("Connected...");
	      }
	      catch(SQLException se) {
	         System.err.println(se.getMessage());
	      }
	   }
	   
	   public void Execute() {
	      try {
	         stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); // Ŀ�� �Ӽ� ����
	         rs = stmt.executeQuery("select * from score");
	         
	         rs.absolute(4); // 4��° ��
	         rs.deleteRow(); // �ٻ���
	         rs.moveToInsertRow(); // �� �̵�?
	         rs.updateString("s_id", "954522"); // ������Ʈ
	         rs.updateInt("korean", 75); //������Ʈ
	         rs.updateInt("english", 95); //������Ʈ
	         rs.updateInt("math", 100); //������Ʈ
	         rs.insertRow(); // �ٻ���
	         rs.absolute(2); // 2��° �ٷ� �̵�
	         rs.updateInt("korean", 95); //������Ʈ
	         rs.updateRow(); // �� ������Ʈ
	         rs.refreshRow(); // �� ���ΰ�ħ?
	         
	         System.out.println("s_id     Korean English Math");
	         
	         if(rs.first()) {
	            do {
	               String s_id = rs.getString(1);
	               int korean = rs.getInt(2);
	               int english = rs.getInt(3);
	               int math = rs.getInt(4);
	               System.out.println(s_id + "   " + korean + "\t" + english + "\t" + math);
	            }while(rs.next());
	         }
	      }
	      catch(SQLException se) {
	         System.err.println(se.getMessage());
	      }
	   }
	   
	   public void Close() {
	      try {
	         con.close();
	         stmt.close();
	         rs.close();
	      }
	      catch(SQLException se) {
	         System.err.println(se.getMessage());
	      }
	      System.out.println("Disconnected...");
	   }
	}

