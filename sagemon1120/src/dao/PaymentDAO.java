package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Payment;
public class PaymentDAO extends MySQLDAO {
	public List<Payment> SelectAll()throws Exception{
		List<Payment> ls = new ArrayList<Payment>();
		String sql = "SELECT * FROM Payment;";
		ResultSet rs = getPreparedStatement(sql).executeQuery();
		while(rs.next()){
			Payment tmp = new Payment();
			tmp.setPayid(rs.getInt("payid"));
			tmp.setPayname(rs.getString("payname"));
			ls.add(tmp);
			System.out.println(tmp.getPayid()+tmp.getPayname());
		}
		return ls;
	}

}
