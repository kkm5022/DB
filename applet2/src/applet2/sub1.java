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

public class sub1 extends Applet implements ActionListener {
	
	Label label; // �󺧻���
	Label label2;
	Label label3;
	
	TextField textField; // ���� �Է¹��� �ؽ�Ʈ �ʵ� ����
	TextField textField2;
	TextField textField3;
	
	TextArea Area; // ��� ���� ����� textarea ����
	TextArea Area2;
	TextArea Area3;
	
	Button button; // �˻���ư
	Button button2;
	Button button3;
	
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
	label = new Label("one"); // �˻���
	add(label); // �˻��� �߰�
	button = new Button("one"); // one�̴� ��ư�� ����� 
	add(button); // ��ư�߰�
	
	label2 = new Label("two"); // �˻���
	add(label2); // �˻��� �߰�
	button2 = new Button("two"); // two��� ��ư�� ����� 
	add(button2); // ��ư�߰�
	
	label3 = new Label("three"); // �˻���
	add(label3); // �˻��� �߰�
	button3 = new Button("three"); // three��� ��ư�� ����� 
	add(button3); // ��ư�߰�
	
	
	Area = new TextArea(15,100); // �������̽� ũ��
	add(Area); // �������̽� �߰�
	button.addActionListener(this); // ��ư

	Area2 = new TextArea(15,100); // �������̽� ũ��
	add(Area2); // �������̽� �߰�
	button2.addActionListener(this); // ��ư
	
	Area3 = new TextArea(15,100); // �������̽� ũ��
	add(Area3); // �������̽� �߰�
	button3.addActionListener(this); // ��ư
}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	if(st.equals("one")){ // �Է��̶�� ��ư�� ������
		one(); // ��� ��� �о���� �Լ� ����
	}else if(st.equals("two")){
		two();
	}else if(st.equals("three")){
		three();
	}
}

private void one(){ // ���� 1-1�� ������ �����ϴ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("select title from course where dept_name = 'Comp_Sci.' and credits = 3");
		// �ڽ� ���̺��� ������� ��ǻ�� ���̾��̰� ������3�� ���� ������ ����ϴ� ����

		while(result.next()){
			String title = result.getString(1); // title

			// �� �о�´�
			
			
			String value = "title : " + title +  "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

private void two(){ // ���� 1-2�� ������ �����ϴ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("select S_id from View_1 where Expr1 = 'Einstein'");
		// �����ð��� ���Ƿ� �����ߴ� ��1�� �̿��� �������� Einstein�� ������ ��� �л��� ���̵� ����ϴ� ����

		while(result.next()){
			String student_id = result.getString(1); // student_id

			// �� �о�´�
			String value = "student_id : " + student_id + "\n";
			int index = Area2.getText().length();
			Area2.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}


private void three(){ // ���� 1-3���� �����ϴ� �Լ�
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("select * from instructor where salary >= all(select salary from instructor)");
		// ��� instructor���̺��� salary���� ũ�ų� ���⶧���� �ִ밪�� ���� �� �ִ� �� �ִ񰪿� �ش��ϴ� ������� ����ϴ� ������

		while(result.next()){
			String id = result.getString(1); // id
			String name = result.getString(2); // name
			String dept_name = result.getString(3); // dept_name
			String salary = result.getString(4); // salary

			// �� �о�´�
			String value = "  id : " + id + "\t" + "name : " + name + "  dept_name : " + dept_name + "  salary : " + salary +  "\n";
			int index = Area3.getText().length();
			Area3.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

}