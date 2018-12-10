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
	label = new Label("print1"); // 검색라벨
	add(label); // 검색라벨 추가
	button = new Button("print1"); // print1이는 버튼을 만든다 
	add(button); // 버튼추가
	
	label2 = new Label("print2"); // 검색라벨
	add(label2); // 검색라벨 추가
	button2 = new Button("print2"); // print2라는 버튼을 만든다 
	add(button2); // 버튼추가
	
	
	Area = new TextArea(15,120); // 인터페이스 크기
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
	Change();
	createview();
	if(st.equals("print1")){ // print1이라는 버튼이 눌리면
		print1(); // print1한 결과를 생성하는 함수를 호출
	}else if(st.equals("print2")){ // print2라느 버튼이 눌리면
		print2(); // print2한 결과를 생성하는 함수를 호출
	}
}

private void Change(){ // takes의 테이블을 케이스에 맞게 변환 시킨 후 grade_point라는 테이블을 생성해주는 함수
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
		// 생성만 하고 출력은 하지 않기 때문에 excuteUpdate를 이용해서  생성
		// select into를 이용하여 grade_point라는 테이블을 takes라는 테이블에서 따온 뒤 case에 맞게 변경시켜준다

		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}


private void createview(){ // 변환시킨 grade_point 테이블의 학점 평균으로 뷰를 만드는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		stmt.executeUpdate("create view view3 as select avg(point*credits) as avgPoint, name from grade_point join course on course.course_id = grade_point.course_id join student on grade_point.id = student.id group by student.name");
		// 뷰를 생성만 하고 출력은 하지 않기 때문에 excuteUpdate를 이용해서 뷰 생성
		// 3개의 테이블을 join하기 하는 쿼리를 이용했으며 학점과 크레딧을 곱한 값의 평균과 학생을 이름을 저장하는 뷰 

		
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}


private void print1(){ // 생성한 테이블을 단순 출력해주는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * from grade_point");
		// 생성한 테이블을 출력해주는 쿼리

		while(result.next()){
			String id = result.getString(1); // id
			String course_id = result.getString(2); // course_id
			String sec_id = result.getString(3); // sec_id
			String semester = result.getString(4); // semester
			String years = result.getString(5); // years
			String grade = result.getString(6); // grade
			double point = result.getDouble(7); // point

			// 다 읽어온다
			
			
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

private void print2(){ // 생성한 뷰를 단순 출력해주는 함수
	Connection con = null;
	Statement stmt = null;
	
	try{
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * from view3");
		// 생성한 뷰를 출력해주는 쿼리

		while(result.next()){
			double avgPoint = result.getDouble(1); // avgPoint
			String name = result.getString(2); // name
			// 다 읽어온다
			
			
			String value = "avgPoint : " + avgPoint + "\nname : " + name  +  "\n";
			int index = Area.getText().length();
			Area2.insertText(value, index);
		}
		con.close();
		stmt.close();
	}catch(Exception ee) {System.out.println(ee);}
}
	
}