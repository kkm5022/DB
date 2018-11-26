package applet;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class sub2 extends Applet implements ActionListener{
	
	Label label; // �󺧻���
	TextField textField; // ���� �Է¹��� �ؽ�Ʈ �ʵ� ����
	TextArea Area; // ��� ���� ����� textarea ����
	Button button; // �˻���ư
	
	private String url = "jdbc:inetdae7://210.115.229.77:2433"; // db����
	private String user = "20145106"; // db���� ���̵�
	private String pass = "s145106@hallym"; //db���� ��й�ȣ
	
public void init(){
		
		try{
			Class.forName("com.inet.tds.TdsDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println("Class Loading Failed");
		}
	}

public void start(){
	setLayout(new FlowLayout()); // �������̽�
	label = new Label("�˻�"); // �˻���
	add(label); // �˻��� �߰�
	button = new Button("search"); // search��� ��ư�� ����� 
	add(button); // ��ư�߰�
	Area = new TextArea(10,150); // �������̽� ũ��
	add(Area); // �������̽� �߰�
	button.addActionListener(this); // ��ư
}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	if(st.equals("search")){ // �Է��̶�� ��ư�� ������
		TotalgetDBSearch(); // ��� ��� �о���� �Լ� ����
	}
}

private void TotalgetDBSearch(){ // customer ���̺��� ��� ������ ����ϴ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM hallym");
		// ���̺��� ��� �����͸� �о���� ���� 
		
		//String count = Area.getText();
		//int c = count.length();
		//Area.replaceText(" ",0,c);
		
		while(result.next()){
			String name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String id = result.getString(3);
			String password = result.getString(4);
			String email = result.getString(5);
			String phone = result.getString(6);
			// �� �о�´�
			
			
			String value = "�̸� : " + name + "���� : " + age + "ID : " + id + "Password : " + password + "e-mail : " + email + "phone : " + phone +"\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}
