package applet2;

// teaches instructor

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sil2 extends Applet implements ActionListener {

	Label label; // �󺧻���
	Label label2;
	TextField textField; // ���� �Է¹��� �ؽ�Ʈ �ʵ� ����
	TextField textField2;
	TextArea Area; // ��� ���� ����� textarea ����
	TextArea Area2;

	Button button; // �˻���ư
	Button button2;

	private String url = "jdbc:inetdae7://210.115.229.77:2433"; // db����
	private String user = "20145106"; // db���� ���̵�
	private String pass = "s145106@hallym"; // db���� ��й�ȣ

	public void init() {

		try {
			Class.forName("com.inet.tds.TdsDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Loading Failed");
		}
	}

	public void start() {
		setLayout(new FlowLayout()); // �������̽�
		label = new Label("Join"); // �˻���
		add(label); // �˻��� �߰�
		button = new Button("Join"); // Join�̶�� ��ư�� �����
		add(button); // ��ư�߰�

		Area = new TextArea(20, 150); // �������̽� ũ��
		add(Area); // �������̽� �߰�
		button.addActionListener(this); // ��ư
		
		Area2 = new TextArea(20, 150); // �������̽� ũ��
		add(Area2); // �������̽� �߰�
	}

	public void actionPerformed(ActionEvent ae) {
		Connection con = null;
		Statement stmt = null;

		String st = ae.getActionCommand();

		if (st.equals("Join")) { // �Է��̶�� ��ư�� ������
			Join(); // ��� ��� �о���� �Լ� ����
		}
	}

	private void Join() { // customer ���̺��� ��� ������ ����ϴ� �Լ�
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery("SELECT instructor.ID, instructor.name, instructor.dept_name FROM instructor join teaches on instructor.ID = teaches.ID");
			/*ResultSet result = stmt.executeQuery("select teaches.ID,name,COUNT(teaches.ID)\n" + 
                    "from teaches RIGHT OUTER JOIN instructor\n" + 
                    "on teaches.ID = instructor.ID\n" + 
                    "group by teaches.ID,instructor.name");*/
			
			// instructor�� teaches�� ���� �� �� ���� Ƚ���� �����ֱ����� ������

			// �������� ���� Ƚ���� count�ϱ����� 0���� �ʱ�ȭ�� ������ ����� �ش�
			int Srinivasan = 0;
			int Wu = 0;
			int Mozart = 0;
			int Einstein = 0;
			int El_Said = 0;
			int Gold = 0;
			int Katz = 0;
			int Califieri = 0;
			int Singh = 0;
			int Crick = 0;
			int Brandt = 0;
			int Kim = 0;

			
			
			while (result.next()) {
				String id = result.getString(1); // id
				String name = result.getString(2); // name
				String dept = result.getString(3); // dept_name
				
				// �ش米������ ���̺� ������ ī��Ʈ�� ���ش�
				if(name.equals("Srinivasan")){
					Srinivasan++;
				}else if(name.equals("Wu")){
					Wu++;
				}else if(name.equals("Mozart")){
					Mozart++;
				}else if(name.equals("Einstein")){
					Einstein++;
				}else if(name.equals("El Said")){
					El_Said++;
				}else if(name.equals("Gold")){
					Gold++;
				}else if(name.equals("Katz")){
					Katz++;
				}else if(name.equals("Califieri")){
					Califieri++;
				}else if(name.equals("Singh")){
					Singh++;
				}else if(name.equals("Crick")){
					Crick++;
				}else if(name.equals("Brandt")){
					Brandt++;
				}else if(name.equals("Kim")){
					Kim++;
				}

				String value = "   Id : " + id +  "   Name : " + name + "   Dept : " + dept + "\n";
				
				
				int index = Area.getText().length();
				Area.insertText(value, index);
				
			}
			
			String value2 = "������ ����ģ ���� �� \n" +"Srinivasan : " + Srinivasan + "\n" +
					"Wu : " + Wu + "\n" +
					"Mozart : " + Mozart +"\n" +
					"Einstein : " + Einstein + "\n" +
					"El_Said : " + El_Said + "\n" +
					"Gold : " + Gold + "\n" +
					"Katz : " + Katz + "\n" +
					"Califieri : " + Califieri + "\n" +
					"Singh : " + Singh + "\n" +
					"Crick : " + Crick + "\n" +
					"Brandt : " + Brandt +"\n" +
					"Kim : " + Kim;
			// ������ ���Ǹ� ����� ������ ������ش� 
			int index2 = Area.getText().length();
			Area2.insertText(value2, index2); // �ι�° â�� ������ش�
			
			con.close();
			stmt.close();
		} catch (Exception ee) {
			System.out.println(ee);
		}
	}

}