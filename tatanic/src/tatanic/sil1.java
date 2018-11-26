package tatanic;
import java.sql.*;

public class sil1 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433"; // MS-SQL�� �����ϱ� ���� ������
		String query = "Select age,Embarked from titanic where Survived = '1' ";
		
		Connection con = null; // SQL������ ���ǿ����� ������ COnnection �������̽�
		Statement stmt = null; // SQL ������ �����ϰ� �� ������� �����ϱ� ���� ���ǵ� Statement��ü
		
		try{
			con = DriverManager.getConnection(url,"20145106","s145106@hallym"); // ����̹� �Ŵ��������� ����
	        System.out.println("Connected to DB");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // Ŀ�� �Ӽ� ����
			ResultSet rs = stmt.executeQuery(query); // ��������

			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;
			
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double sum4 = 0;
			
			while(rs.next()){
				
				String col1 = rs.getString(1); // ����
				String col2 = rs.getString(2); // �¼���ġ
				if(!col1.equals("null")){ // ���̰� ���� ����ü�� "null"�� �ƴҶ�
					if(rs.getString(2).equals("C")){ // �¼���ġ�� C�̸�
						count1++; // ����� ���ϱ� ���� �¼���ġ�� C�� �ֵ� ī��Ʈ
						sum1 += Double.parseDouble(rs.getString(1)); // ����� ���ϱ� ���� ������ ������ �����ش�(���ڿ��̱⶧���� double�����κ�ȯ)
					}else if(rs.getString(2).equals("S")){ // �¼���ġ�� S�̸�
						count2++; // ����� ���ϱ� ���� �¼���ġ�� S�� �ֵ� ī��Ʈ
						sum2 += Double.parseDouble(rs.getString(1)); // ����� ���ϱ� ���� ������ ������ �����ش�(���ڿ��̱⶧���� double�����κ�ȯ)
					}else if(rs.getString(2).equals("Q")){ // �¼���ġ�� Q�̸�
						count3++; // ����� ���ϱ� ���� �¼���ġ�� Q�� �ֵ� ī��Ʈ
						sum3 += Double.parseDouble(rs.getString(1)); // ����� ���ϱ� ���� ������ ������ �����ش�(���ڿ��̱⶧���� double�����κ�ȯ)
					}else{
						count4++; // ����� ���ϱ� ���� �¼���ġ�� 1�� �ֵ� ī��Ʈ
						sum4 += Double.parseDouble(rs.getString(1)); // ����� ���ϱ� ���� ������ ������ �����ش�(���ڿ��̱⶧���� double�����κ�ȯ)
					}
					
				}
				
			}
			System.out.println("C��� = " + sum1/count1); // �¼���ġ C�� ���
			System.out.println("S��� = " + sum2/count2); // �¼���ġ S�� ���
			System.out.println("Q��� = " + sum3/count3); // �¼���ġ Q�� ���
			System.out.println("null��� = " + sum4/count4); // �¼���ġ�� �������� ���� �ֵ��� ���
			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException se){ // �������� ����Ʈ
			System.err.println(se.getMessage());
		}
	}

}