package dao;
import java.sql.PreparedStatement;
import java.util.List;

import model.OrderDetail;

public class OrderDetailDAO extends MySQLDAO {

	public boolean registOrderDetail(List<OrderDetail> od)throws Exception{
		try{
			String sql = "INSERT INTO order_detail (oid, odid, pid, amount) VALUES (?, ?, ?, ?);";
			PreparedStatement Pstmt = getPreparedStatement(sql);
			for(int i=0;i<od.size();i++){
				OrderDetail buf = od.get(i);
				Pstmt.setInt(1, buf.getOid());
				Pstmt.setInt(2, buf.getOdid());
				Pstmt.setInt(3, buf.getPid());
				Pstmt.setInt(4, buf.getAmount());
				System.out.println("▼registOrderDetail▼\n"+Pstmt);
				Pstmt.executeUpdate();

			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
