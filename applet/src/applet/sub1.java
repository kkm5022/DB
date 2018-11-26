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

	Label name; // 이름을 나타내는 라벨
	Label age; // 나이를 나타내는 라벨
	Label id; // id를 나타내는 라벨
	Label password; // password를 나타내는 라벨
	Label email; // email을 나타내는 라벨
	Label phone; // phone을 나타내는 라벨
	
	TextField textname; // 이름 값을 입력받을 텍스트필드
	TextField textage; // 나이 값을 입력받을 텍스트필드
	TextField textid; // id 값을 입력받을 텍스트필드
	TextField textpassword; // password 값을 입력받을 텍스트필드
	TextField textemail; // email 값을 입력받을 텍스트필드
	TextField textphone; // phone 값을 입력받을 텍스트필드
	
	TextArea Area; // 결과 값을 출력할 textarea 선언
	Button button; // 버튼 선언
	
	private String url = "jdbc:inetdae7://210.115.229.77:2433"; // 디비서버
	private String user = "20145106"; // db서버 아이디
	private String pass = "s145106@hallym"; // db서버 비밀번호
	
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
		name = new Label("이름"); 
		add(name); // 이름 라벨 추가
		textname = new TextField(10); 
		add(textname); // 이름 텍스트필드를 추가
		age = new Label("나이");
		add(age); // 나이 라벨 추가
		textage = new TextField(10);
		add(textage); // 나이 텍스트필드를 추가
		id = new Label("ID");
		add(id); // 아이디 라벨 추가
		textid = new TextField(10);
		add(textid); // 아이디 텍스트필드를 추가
		password = new Label("Password");
		add(password); // 비밀번호 라벨 추가
		textpassword = new TextField(10);
		add(textpassword); // 비밀번호 텍스트필드를 추가
		email = new Label("e-mail");
		add(email); // 이메일 라벨 추가
		textemail = new TextField(10);
		add(textemail); // 이메일 텍스트필드를 추가
		phone = new Label("phone");
		add(phone); // 폰번호 라벨 추가
		textphone = new TextField(10);
		add(textphone); // 폰번호 텍스트필드를 추가
		
		
		button = new Button("입력"); // 입력이라는 버튼을 만든다 
		add(button); // 버튼을 추가한다
		Area = new TextArea(10,100); // 인터페이스 크기
		add(Area); // 해당크기의 area 추가
		button.addActionListener(this); //버튼
	}
	
	
	public void actionPerformed(ActionEvent ae){
		Connection con = null;
		Statement stmt = null;
		
		String st = ae.getActionCommand();
		
		if(st.equals("입력")){ // 입력이라는 버튼이 눌리면
			String n = textname.getText();
			int a = Integer.parseInt(textage.getText());
			String i = textid.getText();
			String p = textpassword.getText();
			String e = textemail.getText();
			String phone = textphone.getText();
			// 읽어오고
			Insert(n,a,i,p,e,phone); // insert함수를 실행한다
		}
	}
	
	private void Insert(String name, int age, String id, String password, String email, String phone){ 
		// 테이블에 이름 나이 성별을 넣어서 튜플을 생성하는 함수
		try{
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO hallym VALUES('"+name+"','"+age+"','"+id+"','"+password+"','"+email+"','"+phone+"')");
			// 삽입 쿼리문

			String value = "INSERT COMPLETE\n"; // 입력 완료시 출력
			int index = Area.getText().length();
			Area.insertText(value, index);
			
			stmt.close();
			con.close();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			
			}
		}
	
}
