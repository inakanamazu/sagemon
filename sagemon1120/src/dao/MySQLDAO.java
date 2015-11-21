//DB操作に関する基本的な操作が格納されている

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MySQLDAO {
	private Connection connection = null;
//コンストラクタ
	public MySQLDAO() {}
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}
	public PreparedStatement getPreparedStatement(String sql) throws Exception {
		return getConnection().prepareStatement(sql);
	}
	public void commit() throws SQLException {
		connection.commit();
	}
	public void rollback() throws SQLException {
		connection.rollback();
	}
	public Connection getConnection() throws Exception {
		try {
			if (connection == null || connection.isClosed()) {
				InitialContext initCtx = new InitialContext();
				DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/localDB");
				connection = ds.getConnection();
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection = null;
			throw e;
		}
		return connection;
	}
}
