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

public class sil1 extends Applet implements ActionListener {
	
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
	label = new Label("LeftJoin"); // �˻���
	add(label); // �˻��� �߰�
	button = new Button("LeftJoin"); // LeftJoin��� ��ư�� ����� 
	add(button); // ��ư�߰�
	
	label2 = new Label("RightJoin"); // �˻���
	add(label2); // �˻��� �߰�
	button2 = new Button("RightJoin"); // RightJoin��� ��ư�� ����� 
	add(button2); // ��ư�߰�
	
	Area = new TextArea(15,100); // �������̽� ũ��
	add(Area); // �������̽� �߰�
	button.addActionListener(this); // ��ư

	Area2 = new TextArea(15,100); // �������̽� ũ��
	add(Area2); // �������̽� �߰�
	button2.addActionListener(this); // ��ư
}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	if(st.equals("LeftJoin")){ // LeftJoin�̶�� ��ư�� ������
		LeftJoin(); // LeftJoin�� �� ����� �ҷ�����
	}else if(st.equals("RightJoin")){ // RightJoin�̶�� ��ư�� ������
		RightJoin(); // RightJoin�� �� ����� �ҷ�����
	}
}

private void LeftJoin(){ // LeftJoin�� �ϴ� �Լ� 
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT course.course_id,section.course_id "
											+ "FROM course LEFT  Outer JOIN section "
											+ "on course.course_id = section.course_id ");
		// �ڽ��� �ڽ����̵�� ������ �ڽ����̵� ���� �� left ������ �ϴ� ������ 

		while(result.next()){
			String course_id1 = result.getString(1); // course_id1
			String course_id2 = result.getString(2); // course_id2

			
			String value = "course_id : " + course_id1 + "\t" + "course_id : " + course_id2 + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

private void RightJoin(){ // RightJoin�� �ϴ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT course.course_id,section.course_id "
											+ "FROM course Right  Outer JOIN section "
											+ "on course.course_id = section.course_id ");
		// �ڽ��� �ڽ����̵�� ������ �ڽ����̵� ���� �� right ������ �ϴ� ������ 

		while(result.next()){
			String course_id1 = result.getString(1); // course_id1
			String course_id2 = result.getString(2); // course_id2

			// �� �о�´�
			String value = "course_id : " + course_id1 + "\t" + "course_id : " + course_id2 + "\n";
			int index = Area2.getText().length();
			Area2.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}