package applet2;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sil3 extends Applet implements ActionListener {
	
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
	label = new Label("Join"); // �˻���
	add(label); // �˻��� �߰�
	button = new Button("Join"); // Join�̶�� ��ư�� ����� 
	add(button); // ��ư�߰�
	
	
	Area = new TextArea(15,100); // �������̽� ũ��
	add(Area); // �������̽� �߰�
	button.addActionListener(this); // ��ư


}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	createview();
	if(st.equals("Join")){ // Join�̶�� ��ư�� ������
		Join(); // Join�� ����� �����ϴ� �Լ��� ȣ��
	}
}

private void createview(){ // customer ���̺��� ��� ������ ����ϴ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		stmt.executeUpdate("create view view2 as "
				+ "SELECT room_number "
				+ "FROM course JOIN section "
				+ "on course.course_id = section.course_id "
				+ "where year = 2010 and semester = 'Spring' and dept_name = 'Comp_Sci.'");
		// �並 ������ �ϰ� ����� ���� �ʱ� ������ excuteUpdate�� �̿��ؼ� �� ����
		// ������ 2010�̰� ���б� ��ǻ�� ���̾��ΰ� �� ����

		
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

private void Join(){ // ������ �並 �ܼ� ������ִ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * from view2");
		// ������ �並 ������ִ� ����

		while(result.next()){
			String room = result.getString(1); // room_number

			// �� �о�´�
			
			
			String value = "room  : " + room  +  "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}