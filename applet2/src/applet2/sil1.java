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
	label = new Label("LeftJoin"); // 검색라벨
	add(label); // 검색라벨 추가
	button = new Button("LeftJoin"); // LeftJoin라는 버튼을 만든다 
	add(button); // 버튼추가
	
	label2 = new Label("RightJoin"); // 검색라벨
	add(label2); // 검색라벨 추가
	button2 = new Button("RightJoin"); // RightJoin라는 버튼을 만든다 
	add(button2); // 버튼추가
	
	Area = new TextArea(15,100); // 인터페이스 크기
	add(Area); // 인터페이스 추가
	button.addActionListener(this); // 버튼

	Area2 = new TextArea(15,100); // 인터페이스 크기
	add(Area2); // 인터페이스 추가
	button2.addActionListener(this); // 버튼
}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	if(st.equals("LeftJoin")){ // LeftJoin이라는 버튼이 눌리면
		LeftJoin(); // LeftJoin을 한 결과를 불러오기
	}else if(st.equals("RightJoin")){ // RightJoin이라는 버튼이 눌리면
		RightJoin(); // RightJoin을 한 결과를 불러오기
	}
}

private void LeftJoin(){ // LeftJoin을 하는 함수 
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT course.course_id,section.course_id "
											+ "FROM course LEFT  Outer JOIN section "
											+ "on course.course_id = section.course_id ");
		// 코스의 코스아이디와 섹션의 코스아이디가 같을 때 left 조인을 하는 쿼리문 

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

private void RightJoin(){ // RightJoin을 하는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT course.course_id,section.course_id "
											+ "FROM course Right  Outer JOIN section "
											+ "on course.course_id = section.course_id ");
		// 코스의 코스아이디와 섹션의 코스아이디가 같을 때 right 조인을 하는 쿼리문 

		while(result.next()){
			String course_id1 = result.getString(1); // course_id1
			String course_id2 = result.getString(2); // course_id2

			// 다 읽어온다
			String value = "course_id : " + course_id1 + "\t" + "course_id : " + course_id2 + "\n";
			int index = Area2.getText().length();
			Area2.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}