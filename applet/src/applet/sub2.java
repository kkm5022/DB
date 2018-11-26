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
	
	Label label; // 라벨생성
	TextField textField; // 값을 입력받을 텍스트 필드 선언
	TextArea Area; // 결과 값을 출력할 textarea 선언
	Button button; // 검색버튼
	
	private String url = "jdbc:inetdae7://210.115.229.77:2433"; // db서버
	private String user = "20145106"; // db서버 아이디
	private String pass = "s145106@hallym"; //db서버 비밀번호
	
public void init(){
		
		try{
			Class.forName("com.inet.tds.TdsDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println("Class Loading Failed");
		}
	}

public void start(){
	setLayout(new FlowLayout()); // 인터페이스
	label = new Label("검색"); // 검색라벨
	add(label); // 검색라벨 추가
	button = new Button("search"); // search라는 버튼을 만든다 
	add(button); // 버튼추가
	Area = new TextArea(10,150); // 인터페이스 크기
	add(Area); // 인터페이스 추가
	button.addActionListener(this); // 버튼
}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	if(st.equals("search")){ // 입력이라는 버튼이 눌리면
		TotalgetDBSearch(); // 모든 디비를 읽어오는 함수 수행
	}
}

private void TotalgetDBSearch(){ // customer 테이블의 모든 내용을 출력하는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM hallym");
		// 테이블의 모든 데이터를 읽어오는 쿼리 
		
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
			// 다 읽어온다
			
			
			String value = "이름 : " + name + "나이 : " + age + "ID : " + id + "Password : " + password + "e-mail : " + email + "phone : " + phone +"\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}
