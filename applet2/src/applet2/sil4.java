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

public class sil4 extends Applet implements ActionListener {
	
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
	label = new Label("print1"); // �˻���
	add(label); // �˻��� �߰�
	button = new Button("print1"); // print1�̴� ��ư�� ����� 
	add(button); // ��ư�߰�
	
	label2 = new Label("print2"); // �˻���
	add(label2); // �˻��� �߰�
	button2 = new Button("print2"); // print2��� ��ư�� ����� 
	add(button2); // ��ư�߰�
	
	
	Area = new TextArea(15,120); // �������̽� ũ��
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
	Change();
	createview();
	if(st.equals("print1")){ // print1�̶�� ��ư�� ������
		print1(); // print1�� ����� �����ϴ� �Լ��� ȣ��
	}else if(st.equals("print2")){ // print2��� ��ư�� ������
		print2(); // print2�� ����� �����ϴ� �Լ��� ȣ��
	}
}

private void Change(){ // takes�� ���̺��� ���̽��� �°� ��ȯ ��Ų �� grade_point��� ���̺��� �������ִ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		stmt.executeUpdate("select *,point = CASE grade "
				+ "WHEN 'A' THEN 4.0 "
				+ "WHEN 'A-' THEN 3.7"
				+ "WHEN 'B+' THEN 3.4"
				+ "WHEN 'B' THEN 3.1 "
				+ "WHEN 'B-' THEN 2.8"
				+ "WHEN 'C+' THEN 2.5"
				+ "WHEN 'C' THEN 2.2"
				+ "WHEN 'C-' THEN 1.9"
				+ "WHEN 'D+' THEN 1.6"
				+ "WHEN 'D' THEN 1.3"
				+ "WHEN 'D-' THEN 1.0"
				+ "WHEN 'F' THEN 0.7 END "
				+ "into grade_point from takes");
		// ������ �ϰ� ����� ���� �ʱ� ������ excuteUpdate�� �̿��ؼ�  ����
		// select into�� �̿��Ͽ� grade_point��� ���̺��� takes��� ���̺��� ���� �� case�� �°� ��������ش�

		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}


private void createview(){ // ��ȯ��Ų grade_point ���̺��� ���� ������� �並 ����� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		stmt.executeUpdate("create view view3 as select avg(point*credits) as avgPoint, name from grade_point join course on course.course_id = grade_point.course_id join student on grade_point.id = student.id group by student.name");
		// �並 ������ �ϰ� ����� ���� �ʱ� ������ excuteUpdate�� �̿��ؼ� �� ����
		// 3���� ���̺��� join�ϱ� �ϴ� ������ �̿������� ������ ũ������ ���� ���� ��հ� �л��� �̸��� �����ϴ� �� 

		
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}


private void print1(){ // ������ ���̺��� �ܼ� ������ִ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * from grade_point");
		// ������ ���̺��� ������ִ� ����

		while(result.next()){
			String id = result.getString(1); // id
			String course_id = result.getString(2); // course_id
			String sec_id = result.getString(3); // sec_id
			String semester = result.getString(4); // semester
			String years = result.getString(5); // years
			String grade = result.getString(6); // grade
			double point = result.getDouble(7); // point

			// �� �о�´�
			
			
			String value = "id : " + id + "\tcourse_id : " + course_id + 
				"\tsec_id : " + sec_id + "\tsemester : " + semester +
				"\tyears : " + years + "\tgrade : " + grade +
				"\tpoint : " + point + "\n";
			
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

private void print2(){ // ������ �並 �ܼ� ������ִ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * from view3");
		// ������ �並 ������ִ� ����

		while(result.next()){
			double avgPoint = result.getDouble(1); // avgPoint
			String name = result.getString(2); // name
			// �� �о�´�
			
			
			String value = "avgPoint : " + avgPoint + "\nname : " + name  +  "\n";
			int index = Area.getText().length();
			Area2.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}