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
	
	Label label; // 라벨생성
	Label label2;
	TextField textField; // 값을 입력받을 텍스트 필드 선언
	TextField textField2;
	TextArea Area; // 결과 값을 출력할 textarea 선언
	TextArea Area2;
	
	Button button; // 검색버튼
	Button button2;
	
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
	label = new Label("Join"); // 검색라벨
	add(label); // 검색라벨 추가
	button = new Button("Join"); // Join이라는 버튼을 만든다 
	add(button); // 버튼추가
	
	
	Area = new TextArea(15,100); // 인터페이스 크기
	add(Area); // 인터페이스 추가
	button.addActionListener(this); // 버튼


}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	createview();
	if(st.equals("Join")){ // Join이라는 버튼이 눌리면
		Join(); // Join한 결과를 생성하는 함수를 호출
	}
}

private void createview(){ // customer 테이블의 모든 내용을 출력하는 함수
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
		// 뷰를 생성만 하고 출력은 하지 않기 때문에 excuteUpdate를 이용해서 뷰 생성
		// 연도가 2010이고 봄학기 컴퓨터 사이언스인걸 뷰 생성

		
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

private void Join(){ // 생성한 뷰를 단순 출력해주는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * from view2");
		// 생성한 뷰를 출력해주는 쿼리

		while(result.next()){
			String room = result.getString(1); // room_number

			// 다 읽어온다
			
			
			String value = "room  : " + room  +  "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}