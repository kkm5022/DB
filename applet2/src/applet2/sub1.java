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
	
	Label label; // 라벨생성
	Label label2;
	Label label3;
	
	TextField textField; // 값을 입력받을 텍스트 필드 선언
	TextField textField2;
	TextField textField3;
	
	TextArea Area; // 결과 값을 출력할 textarea 선언
	TextArea Area2;
	TextArea Area3;
	
	Button button; // 검색버튼
	Button button2;
	Button button3;
	
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
	label = new Label("one"); // 검색라벨
	add(label); // 검색라벨 추가
	button = new Button("one"); // one이는 버튼을 만든다 
	add(button); // 버튼추가
	
	label2 = new Label("two"); // 검색라벨
	add(label2); // 검색라벨 추가
	button2 = new Button("two"); // two라는 버튼을 만든다 
	add(button2); // 버튼추가
	
	label3 = new Label("three"); // 검색라벨
	add(label3); // 검색라벨 추가
	button3 = new Button("three"); // three라는 버튼을 만든다 
	add(button3); // 버튼추가
	
	
	Area = new TextArea(15,100); // 인터페이스 크기
	add(Area); // 인터페이스 추가
	button.addActionListener(this); // 버튼

	Area2 = new TextArea(15,100); // 인터페이스 크기
	add(Area2); // 인터페이스 추가
	button2.addActionListener(this); // 버튼
	
	Area3 = new TextArea(15,100); // 인터페이스 크기
	add(Area3); // 인터페이스 추가
	button3.addActionListener(this); // 버튼
}


public void actionPerformed(ActionEvent ae){
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	if(st.equals("one")){ // 입력이라는 버튼이 눌리면
		one(); // 모든 디비를 읽어오는 함수 수행
	}else if(st.equals("two")){
		two();
	}else if(st.equals("three")){
		three();
	}
}

private void one(){ // 과제 1-1번 문제를 수행하는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("select title from course where dept_name = 'Comp_Sci.' and credits = 3");
		// 코스 테이블에서 과목명이 컴퓨터 사이언스이고 학점이3인 것의 제목을 출력하는 쿼리

		while(result.next()){
			String title = result.getString(1); // title

			// 다 읽어온다
			
			
			String value = "title : " + title +  "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

private void two(){ // 과제 1-2번 문제를 수행하는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("select S_id from View_1 where Expr1 = 'Einstein'");
		// 수업시간에 임의로 생성했던 뷰1을 이용해 교수명이 Einstein인 수업을 듣는 학생의 아이디를 출력하는 쿼리

		while(result.next()){
			String student_id = result.getString(1); // student_id

			// 다 읽어온다
			String value = "student_id : " + student_id + "\n";
			int index = Area2.getText().length();
			Area2.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}


private void three(){ // 과제 1-3번을 수행하는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("select * from instructor where salary >= all(select salary from instructor)");
		// 모든 instructor테이블의 salary보다 크거나 같기때문에 최대값을 구할 수 있다 그 최댓값에 해당하는 모든정보 출력하는 쿼리문

		while(result.next()){
			String id = result.getString(1); // id
			String name = result.getString(2); // name
			String dept_name = result.getString(3); // dept_name
			String salary = result.getString(4); // salary

			// 다 읽어온다
			String value = "  id : " + id + "\t" + "name : " + name + "  dept_name : " + dept_name + "  salary : " + salary +  "\n";
			int index = Area3.getText().length();
			Area3.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}

}