package applet;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class sub1 extends Applet implements ActionListener{

	Label name; // �̸��� ��Ÿ���� ��
	Label age; // ���̸� ��Ÿ���� ��
	Label id; // id�� ��Ÿ���� ��
	Label password; // password�� ��Ÿ���� ��
	Label email; // email�� ��Ÿ���� ��
	Label phone; // phone�� ��Ÿ���� ��
	
	TextField textname; // �̸� ���� �Է¹��� �ؽ�Ʈ�ʵ�
	TextField textage; // ���� ���� �Է¹��� �ؽ�Ʈ�ʵ�
	TextField textid; // id ���� �Է¹��� �ؽ�Ʈ�ʵ�
	TextField textpassword; // password ���� �Է¹��� �ؽ�Ʈ�ʵ�
	TextField textemail; // email ���� �Է¹��� �ؽ�Ʈ�ʵ�
	TextField textphone; // phone ���� �Է¹��� �ؽ�Ʈ�ʵ�
	
	TextArea Area; // ��� ���� ����� textarea ����
	Button button; // ��ư ����
	
	private String url = "jdbc:inetdae7://210.115.229.77:2433"; // ��񼭹�
	private String user = "20145106"; // db���� ���̵�
	private String pass = "s145106@hallym"; // db���� ��й�ȣ
	
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
		name = new Label("�̸�"); 
		add(name); // �̸� �� �߰�
		textname = new TextField(10); 
		add(textname); // �̸� �ؽ�Ʈ�ʵ带 �߰�
		age = new Label("����");
		add(age); // ���� �� �߰�
		textage = new TextField(10);
		add(textage); // ���� �ؽ�Ʈ�ʵ带 �߰�
		id = new Label("ID");
		add(id); // ���̵� �� �߰�
		textid = new TextField(10);
		add(textid); // ���̵� �ؽ�Ʈ�ʵ带 �߰�
		password = new Label("Password");
		add(password); // ��й�ȣ �� �߰�
		textpassword = new TextField(10);
		add(textpassword); // ��й�ȣ �ؽ�Ʈ�ʵ带 �߰�
		email = new Label("e-mail");
		add(email); // �̸��� �� �߰�
		textemail = new TextField(10);
		add(textemail); // �̸��� �ؽ�Ʈ�ʵ带 �߰�
		phone = new Label("phone");
		add(phone); // ����ȣ �� �߰�
		textphone = new TextField(10);
		add(textphone); // ����ȣ �ؽ�Ʈ�ʵ带 �߰�
		
		
		button = new Button("�Է�"); // �Է��̶�� ��ư�� ����� 
		add(button); // ��ư�� �߰��Ѵ�
		Area = new TextArea(10,100); // �������̽� ũ��
		add(Area); // �ش�ũ���� area �߰�
		button.addActionListener(this); //��ư
	}
	
	
	public void actionPerformed(ActionEvent ae){
		Connection con = null;
		Statement stmt = null;
		
		String st = ae.getActionCommand();
		
		if(st.equals("�Է�")){ // �Է��̶�� ��ư�� ������
			String n = textname.getText();
			int a = Integer.parseInt(textage.getText());
			String i = textid.getText();
			String p = textpassword.getText();
			String e = textemail.getText();
			String phone = textphone.getText();
			// �о����
			Insert(n,a,i,p,e,phone); // insert�Լ��� �����Ѵ�
		}
	}
	
	private void Insert(String name, int age, String id, String password, String email, String phone){ 
		// ���̺� �̸� ���� ������ �־ Ʃ���� �����ϴ� �Լ�
		try{
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO hallym VALUES('"+name+"','"+age+"','"+id+"','"+password+"','"+email+"','"+phone+"')");
			// ���� ������

			String value = "INSERT COMPLETE\n"; // �Է� �Ϸ�� ���
			int index = Area.getText().length();
			Area.insertText(value, index);
			
			stmt.close();
			con.close();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			
			}
		}
	
}
