package applet;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class AppletTest extends Applet implements ActionListener{
	Choice search; //��ü, �̸�, ����, ������ ������ ����
	Choice search2; // �˻�, ����, ����, ������ ������ ����
	Label label; // �󺧼���
	TextField textField; // ���� �Է¹��� �ؽ�Ʈ �ʵ� ����
	TextArea Area; // ��� ���� ����� textarea ����
	Button button; // �˻���ư
	
	private String url = "jdbc:inetdae7://210.115.229.77:2433";
	private String user = "20145106"; // db���� ����
	private String pass = "s145106@hallym"; // db���� ���̵�
	
	public void init(){
		
		try{
			Class.forName("com.inet.tds.TdsDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println("Class Loading Failed");
		}
	}
	
	public void start(){
		setLayout(new FlowLayout());
		label = new Label("�˻� ����");
		add(label);
		
		search2 = new Choice(); // �� ���ʿ� �ִ� �޴��� �����
		search2.add("�˻�"); // �˻� ���ð���
		search2.add("����"); // ���� ���ð���
		search2.add("����"); // ���� ���ð���
		search2.add("����"); // ���� ���ð���
		add(search2); // �� ���ʿ� �ִ� �޴��� �����
		
		search = new Choice(); // ù��° �޴� ���� �޴��� �ϳ� �� �����
		search.add("��ü"); // ��ü ���ð���
		search.add("�̸�"); // �̸� ���ð���
		search.add("����"); // ���� ���ð���
		search.add("����"); // ���� ���ð���
		add(search); // ù��° �޴� ���� �޴��� �ϳ� �� �����
		
		
		textField = new TextField(10);
		add(textField);
		button = new Button("execute"); // execute��� ��ư�� ����� 
		add(button);
		Area = new TextArea(10,100); // �������̽� ũ��
		add(Area);
		button.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent ae){
		Connection con = null;
		Statement stmt = null;
		
		String st = ae.getActionCommand();
		String item = search.getSelectedItem(); // �˻�,����,����,������ �ϴ� �޴��� ����� �� �� ������
		String item2 = search2.getSelectedItem(); // ��ü, �̸�, ����, ���� �޴��� ����� �� �� ������
		
		if(st.equals("execute")){ // execute��� ��ư�� ������
			String n = textField.getText();
			String str[] = n.split(", "); // ��ǥ, ������ �����ؼ� �ڸ���
			
			if(item2.equals("�˻�")){ // �˻��� ��� ��ü�˻�, �̸�,����,���� �˻��� �� �� �־�� �ϱ� ������ item�� �� �� �־��ش�
				if(item.equals("��ü")){ // ��ü �˻�
					TotalgetDBSearch();
				}
				else if(item.equals("�̸�")){ // �̸����� �˻�
					NamegetDBSearch(n);
				}
				else if(item.equals("����")){ // ���̷� �˻�
					AgegetDBSearch(Integer.parseInt(n)); // ���ڿ��� ���������� �ٲ㼭 �־����
				}
				else if(item.equals("����")){ // ������ �˻�
					SexgetDBSearch(n);
				}
			}
			else if(item2.equals("����")){ // ������ ��� ������ �ϴ� �Լ�
				Insert(str[0],Integer.parseInt(str[1]),str[2]); // str[0]�� �̸�, str[1]�� ����, str[2]�� ����
			}
			else if(item2.equals("����")){ // �����ϴ� �Լ�
				customer_Delete(n);
			}
			else if(item2.equals("����")){ // �����ϴ� �Լ�
				update();
			}
		}
	}
	

	private void TotalgetDBSearch(){ // customer ���̺��� ��� ������ ����ϴ� �Լ�
		Connection con = null;
		Statement stmt = null;
		
		try{
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM customer"); //���������� ��ü���
			
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ",0,c);
			
			while(result.next()){
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);
				
				String value = "�̸� : " + Name + "���� : " + age + "���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
		}catch(Exception ee) {System.out.println(ee);}
	}
	
	private void NamegetDBSearch(String n){ // customer ���̺��� �̸��� �޾� �ش� �̸��� Ʃ���� ����ϴ� �Լ�
		Connection con = null;
		Statement stmt = null;
		try{
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE name = '"+n+"'");
			
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ",0,c);
			
			while(result.next()){
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);
				
				String value = "�̸� : " + Name + "���� : " + age + "���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
			
		}catch(SQLException se){
			System.out.println(se.getMessage());
			}
	}
	
	private void AgegetDBSearch(int n){ // customer ���̺��� ���̸� �޾� �ش� ������ Ʃ���� ����ϴ� �Լ�
		Connection con = null;
		Statement stmt = null;
		try{
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE age = '"+n+"'"); //���������� ���� ���
			
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ",0,c);
			
			while(result.next()){
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);
				
				String value = "�̸� : " + Name + "���� : " + age + "���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
			
		}catch(SQLException se){
			System.out.println(se.getMessage());
			}
	}
	
	private void SexgetDBSearch(String n){ // customer ���̺��� ������ �޾� �ش� ������ Ʃ�õ��� ����ϴ� �Լ�
		Connection con = null;
		Statement stmt = null;
		try{
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE sex = '"+n+"'"); //���������� ���� ���
			
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ",0,c);
			
			while(result.next()){
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);
				
				String value = "�̸� : " + Name + "���� : " + age + "���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
			
		}catch(SQLException se){
			System.out.println(se.getMessage());
			}
	}
	
	private void Insert(String name, int age, String sex){ // customer ���̺� �̸� ���� ������ �־ Ʃ���� �����ϴ� �Լ�
	try{
		Connection con;
		Statement stmt;
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		stmt.executeUpdate("INSERT INTO customer VALUES('"+name+"','"+age+"','"+sex+"')"); // �̸�, ����, ���� ����
		

		stmt.close();
		con.close();
	}catch(SQLException se){
		System.out.println(se.getMessage());
		
		}
	}
	
	void customer_Delete(String r){ // customer ���̺��� �̸��� �޾� �ش� Ʃ���� �����ϴ� �Լ�
		
		try{
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			
			stmt = con.createStatement();
			stmt.executeUpdate("delete from customer where name = '"+r+"'"); // �ش� �̸� Ʃ�� ����
			
			String value = "DELETE COMPLETE\n"; //���� �Ϸ�� ���
			int index = Area.getText().length(); 
			Area.insertText(value, index);
			
			stmt.close();
			con.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
		}
	}
	
	void update(){ // customer ���̺��� ���̸� 1�� ������Ű�� �Լ� (������Ʈ���� ����Ѵ�)
		
		try{
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			
			stmt = con.createStatement();
			stmt.executeUpdate("update customer set age = age + 1"); // ������Ʈ ������ age�� 1 ������Ų��
			
			String value = "UPDATE COMPLETE\n"; // ������Ʈ �Ϸ�� ���
			int index = Area.getText().length();
			Area.insertText(value, index);
			
			
			stmt.close();
			con.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
		}
		
	}
	
}
