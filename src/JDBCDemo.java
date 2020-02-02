import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	public static void main(String[] args) {
//		getUserAccountWithStmt();
//		insertUserAccoutWithStmt();
//		updateUserAccoutWithStmt();
//		getUserAccoutByIDWithPStmt();
//		insertUserAccoutWithPStmt();
		updateUserAccoutWithPStmt();
	}
	
	public static void updateUserAccoutWithPStmt() {
		Connection con = DBConnection.getConnection();
		
		String sql = "Update user_account set name=?, amount=? where id=?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "Doe");
			stmt.setFloat(2, 2000.0f);
			stmt.setInt(3, 4);
			
			int result = stmt.executeUpdate();
			if(result==1) {
				System.out.println("Update success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertUserAccoutWithPStmt() {
		Connection con = DBConnection.getConnection();
		
		String sql = "Insert into user_account(name, amount) values(?, ?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "Nora");
			stmt.setFloat(2, 1500.0f);
			int result = stmt.executeUpdate();
			if(result==1) {
				System.out.println("Insert success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getUserAccoutByIDWithPStmt() {
		Connection con = DBConnection.getConnection();
		
		String sql = "Select * from user_account where id = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, 2);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getFloat(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateUserAccoutWithStmt() {
		Connection con = DBConnection.getConnection();
		
		String sql = "Update user_account set name='John', amount=1000.0 where id=3";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result==1) {
				System.out.println("Update success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertUserAccoutWithStmt() {
		Connection con = DBConnection.getConnection();
		
		String sql = "Insert Into user_account(name, amount) Values('"+"Dara"+"',"+500.0+")";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result==1) {
				System.out.println("Insert success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getUserAccountWithStmt() {
		Connection con = DBConnection.getConnection();
		
		String sql = "Select * From user_account";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getFloat(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
