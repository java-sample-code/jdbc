import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDemo {
	public static void main(String[] args) {
		transferMoney(3, 4, 200);
	}
	
	public static void transferMoney(int senderID, int receiverID, float amount) {
		Connection connection = DBConnection.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		String sql = "Update user_account set amount = amount + ? where id=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setFloat(1, - amount);
			preparedStatement.setInt(2, senderID);
			preparedStatement.executeUpdate();
			
			preparedStatement.setFloat(1, amount);
			preparedStatement.setInt(2, receiverID);
			preparedStatement.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
