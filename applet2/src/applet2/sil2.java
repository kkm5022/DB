package applet2;

// teaches instructor

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sil2 extends Applet implements ActionListener {

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
	private String pass = "s145106@hallym"; // db서버 비밀번호

	public void init() {

		try {
			Class.forName("com.inet.tds.TdsDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Loading Failed");
		}
	}

	public void start() {
		setLayout(new FlowLayout()); // 인터페이스
		label = new Label("Join"); // 검색라벨
		add(label); // 검색라벨 추가
		button = new Button("Join"); // Join이라는 버튼을 만든다
		add(button); // 버튼추가

		Area = new TextArea(20, 150); // 인터페이스 크기
		add(Area); // 인터페이스 추가
		button.addActionListener(this); // 버튼
		
		Area2 = new TextArea(20, 150); // 인터페이스 크기
		add(Area2); // 인터페이스 추가
	}

	public void actionPerformed(ActionEvent ae) {
		Connection con = null;
		Statement stmt = null;

		String st = ae.getActionCommand();

		if (st.equals("Join")) { // 입력이라는 버튼이 눌리면
			Join(); // 모든 디비를 읽어오는 함수 수행
		}
	}

	private void Join() { // customer 테이블의 모든 내용을 출력하는 함수
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20145106");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery("SELECT instructor.ID, instructor.name, instructor.dept_name FROM instructor join teaches on instructor.ID = teaches.ID");
			/*ResultSet result = stmt.executeQuery("select teaches.ID,name,COUNT(teaches.ID)\n" + 
                    "from teaches RIGHT OUTER JOIN instructor\n" + 
                    "on teaches.ID = instructor.ID\n" + 
                    "group by teaches.ID,instructor.name");*/
			
			// instructor와 teaches를 조인 한 뒤 강의 횟수를 세어주기위한 쿼리문

			// 교수들의 강의 횟수를 count하기위해 0으로 초기화한 변수를 만들어 준다
			int Srinivasan = 0;
			int Wu = 0;
			int Mozart = 0;
			int Einstein = 0;
			int El_Said = 0;
			int Gold = 0;
			int Katz = 0;
			int Califieri = 0;
			int Singh = 0;
			int Crick = 0;
			int Brandt = 0;
			int Kim = 0;

			
			
			while (result.next()) {
				String id = result.getString(1); // id
				String name = result.getString(2); // name
				String dept = result.getString(3); // dept_name
				
				// 해당교수명이 테이블에 있으면 카운트를 해준다
				if(name.equals("Srinivasan")){
					Srinivasan++;
				}else if(name.equals("Wu")){
					Wu++;
				}else if(name.equals("Mozart")){
					Mozart++;
				}else if(name.equals("Einstein")){
					Einstein++;
				}else if(name.equals("El Said")){
					El_Said++;
				}else if(name.equals("Gold")){
					Gold++;
				}else if(name.equals("Katz")){
					Katz++;
				}else if(name.equals("Califieri")){
					Califieri++;
				}else if(name.equals("Singh")){
					Singh++;
				}else if(name.equals("Crick")){
					Crick++;
				}else if(name.equals("Brandt")){
					Brandt++;
				}else if(name.equals("Kim")){
					Kim++;
				}

				String value = "   Id : " + id +  "   Name : " + name + "   Dept : " + dept + "\n";
				
				
				int index = Area.getText().length();
				Area.insertText(value, index);
				
			}
			
			String value2 = "교수별 가르친 강의 수 \n" +"Srinivasan : " + Srinivasan + "\n" +
					"Wu : " + Wu + "\n" +
					"Mozart : " + Mozart +"\n" +
					"Einstein : " + Einstein + "\n" +
					"El_Said : " + El_Said + "\n" +
					"Gold : " + Gold + "\n" +
					"Katz : " + Katz + "\n" +
					"Califieri : " + Califieri + "\n" +
					"Singh : " + Singh + "\n" +
					"Crick : " + Crick + "\n" +
					"Brandt : " + Brandt +"\n" +
					"Kim : " + Kim;
			// 교수가 강의를 몇번씩 헀는지 출력해준다 
			int index2 = Area.getText().length();
			Area2.insertText(value2, index2); // 두번째 창에 출력해준다
			
			con.close();
			stmt.close();
		} catch (Exception ee) {
			System.out.println(ee);
		}
	}

}