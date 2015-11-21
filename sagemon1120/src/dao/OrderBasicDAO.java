package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import model.OrderBasic;
public class OrderBasicDAO extends MySQLDAO {
	public boolean registOrderBasic(OrderBasic ob)throws Exception{
		//
		try{
		String sql = "INSERT INTO order_basic (uid, aid, odate, payid) VALUES (?, ?, ?, ?);";
		PreparedStatement Pstmt = getPreparedStatement(sql);

		Timestamp d =new Timestamp(System.currentTimeMillis());
		Pstmt.setInt(1, ob.getUid());
		Pstmt.setInt(2, ob.getAid());
		Pstmt.setTimestamp(3, d);
		Pstmt.setInt(4, ob.getOpayment());
		System.out.println("▼registOrderBasic▼\n"+Pstmt);

		Pstmt.executeUpdate();

		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public int getLastId()throws Exception{
		int n = -1;
		String sql = "select last_insert_id() FROM order_basic;";
		ResultSet rs = getPreparedStatement(sql).executeQuery();
		if(rs.next()){
			n=rs.getInt(1);
		}

		return n;
	}
}
