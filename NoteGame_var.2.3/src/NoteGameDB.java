import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteGameDB {

	
	static ResultSet rs;
	static java.sql.Statement st;
	static boolean DB;
	Connection con;
	
	public NoteGameDB() {
		
		try {

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//DB연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@net.yjc.ac.kr:1521:orcl",
					"s1301005", "p1301005");
			
			st = con.createStatement();
			//DB 연결 성공시 
			DB =true;
		} catch (SQLException sqex) {
			
			DB = false;
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		
		
	}
	
	
	
	//점수 입력
	public static void setScore(String musicName, String userName, int score, String mode) {
		
		String query = 
				"INSERT INTO gamescore VALUES (" + 
				  score
				+ ", '"
				+ userName
				+ "', '"
				+ musicName
				+ "', '"
				+ mode
				+ "')";
		
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//등수 정보받기
	public static ResultSet getScore(String musicName) {
		
		rs = null;
		String query = "select * from gamescore where musicname = '" + 
				musicName + 
				"' order by score desc";
		try {
			
			rs = st.executeQuery(query);
			
			if (st.execute(query)) {
				rs = st.getResultSet();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
}
