package applet;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class AppletTest extends Applet implements ActionListener{
	Choice search; //전체, 이름, 나이, 성별을 선택할 변수
	Choice search2; // 검색, 삽입, 삭제, 갱신을 선택할 변수
	Label label; // 라벨선언
	TextField textField; // 값을 입력받을 텍스트 필드 선언
	TextArea Area; // 결과 값을 출력할 textarea 선언
	Button button; // 검색버튼
	
	private String url = "jdbc:inetdae7://210.115.229.77:2433";
	private String user = "20145106"; // db서버 계정
	private String pass = "s145106@hallym"; // db서버 아이디
	
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
		label = new Label("검색 조건");
		add(label);
		
		search2 = new Choice(); // 맨 왼쪽에 있는 메뉴를 만든다
		search2.add("검색"); // 검색 선택가능
		search2.add("삽입"); // 삽입 선택가능
		search2.add("삭제"); // 삭제 선택가능
		search2.add("갱신"); // 갱신 선택가능
		add(search2); // 맨 왼쪽에 있는 메뉴를 만든다
		
		search = new Choice(); // 첫번째 메뉴 옆에 메뉴를 하나 더 만든다
		search.add("전체"); // 전체 선택가능
		search.add("이름"); // 이름 선택가능
		search.add("나이"); // 나이 선택가능
		search.add("성별"); // 성별 선택가능
		add(search); // 첫번째 메뉴 옆에 메뉴를 하나 더 만든다
		
		
		textField = new TextField(10);
		add(textField);
		button = new Button("execute"); // execute라는 버튼을 만든다 
		add(button);
		Area = new TextArea(10,100); // 인터페이스 크기
		add(Area);
		button.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent ae){
		Connection con = null;
		Statement stmt = null;
		
		String st = ae.getActionCommand();
		String item = search.getSelectedItem(); // 검색,삽입,삭제,갱신을 하는 메뉴를 사용할 때 쓸 아이템
		String item2 = search2.getSelectedItem(); // 전체, 이름, 나이, 성별 메뉴를 사용할 떄 쓸 아이템
		
		if(st.equals("execute")){ // execute라는 버튼이 눌리면
			String n = textField.getText();
			String str[] = n.split(", "); // 쉼표, 공백을 구분해서 자른다
			
			if(item2.equals("검색")){ // 검색일 경우 전체검색, 이름,나이,성별 검색을 할 수 있어야 하기 때문에 item을 싹 다 넣어준다
				if(item.equals("전체")){ // 전체 검색
					TotalgetDBSearch();
				}
				else if(item.equals("이름")){ // 이름으로 검색
					NamegetDBSearch(n);
				}
				else if(item.equals("나이")){ // 나이로 검색
					AgegetDBSearch(Integer.parseInt(n)); // 문자열을 정수형으로 바꿔서 넣어줬다
				}
				else if(item.equals("성별")){ // 성별로 검색
					SexgetDBSearch(n);
				}
			}
			else if(item2.equals("삽입")){ // 삽입일 경우 삽입을 하는 함수
				Insert(str[0],Integer.parseInt(str[1]),str[2]); // str[0]은 이름, str[1]은 나이, str[2]는 성별
			}
			else if(item2.equals("삭제")){ // 삭제하는 함수
				customer_Delete(n);
			}
			else if(item2.equals("갱신")){ // 갱신하는 함수
				update();
			}
		}
	}
	

	private void TotalgetDBSearch(){ // customer 테이블의 모든 내용을 출력하는 함수
		Connection con = null;
		Statement stmt = null;
		
		try{
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM customer"); //쿼리문으로 전체출력
			
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ",0,c);
			
			while(result.next()){
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);
				
				String value = "이름 : " + Name + "나이 : " + age + "성별 : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
		}catch(Exception ee) {System.out.println(ee);}
	}
	
	private void NamegetDBSearch(String n){ // customer 테이블의 이름을 받아 해당 이름의 튜플을 출력하는 함수
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
				
				String value = "이름 : " + Name + "나이 : " + age + "성별 : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
			
		}catch(SQLException se){
			System.out.println(se.getMessage());
			}
	}
	
	private void AgegetDBSearch(int n){ // customer 테이블의 나이를 받아 해당 나이의 튜플을 출력하는 함수
		Connection con = null;
		Statement stmt = null;
		try{
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE age = '"+n+"'"); //쿼리문으로 나이 출력
			
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ",0,c);
			
			while(result.next()){
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);
				
				String value = "이름 : " + Name + "나이 : " + age + "성별 : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
			
		}catch(SQLException se){
			System.out.println(se.getMessage());
			}
	}
	
	private void SexgetDBSearch(String n){ // customer 테이블의 성별을 받아 해당 성별의 튜플들을 출력하는 함수
		Connection con = null;
		Statement stmt = null;
		try{
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE sex = '"+n+"'"); //쿼리문으로 성별 출력
			
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ",0,c);
			
			while(result.next()){
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);
				
				String value = "이름 : " + Name + "나이 : " + age + "성별 : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index);
			}
			con.close();
			stmt.close();
			
		}catch(SQLException se){
			System.out.println(se.getMessage());
			}
	}
	
	private void Insert(String name, int age, String sex){ // customer 테이블에 이름 나이 성별을 넣어서 튜플을 생성하는 함수
	try{
		Connection con;
		Statement stmt;
		con = DriverManager.getConnection(url,user,pass);
		con.setCatalog("20145106");
		stmt = con.createStatement();
		
		stmt.executeUpdate("INSERT INTO customer VALUES('"+name+"','"+age+"','"+sex+"')"); // 이름, 나이, 성별 삽입
		

		stmt.close();
		con.close();
	}catch(SQLException se){
		System.out.println(se.getMessage());
		
		}
	}
	
	void customer_Delete(String r){ // customer 테이블의 이름을 받아 해당 튜플을 제거하는 함수
		
		try{
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			
			stmt = con.createStatement();
			stmt.executeUpdate("delete from customer where name = '"+r+"'"); // 해당 이름 튜플 삭제
			
			String value = "DELETE COMPLETE\n"; //삭제 완료시 출력
			int index = Area.getText().length(); 
			Area.insertText(value, index);
			
			stmt.close();
			con.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
		}
	}
	
	void update(){ // customer 테이블의 나이를 1씩 증가시키는 함수 (업데이트문을 사용한다)
		
		try{
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url,user,pass);
			con.setCatalog("20145106");
			
			stmt = con.createStatement();
			stmt.executeUpdate("update customer set age = age + 1"); // 업데이트 문에서 age를 1 증가시킨다
			
			String value = "UPDATE COMPLETE\n"; // 업데이트 완료시 출력
			int index = Area.getText().length();
			Area.insertText(value, index);
			
			
			stmt.close();
			con.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
		}
		
	}
	
}
