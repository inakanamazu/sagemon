package dao;
//TODO DAOのロジックとの分割＆テーブル単位に分割…
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Address;
import model.OrderBasic;
import model.OrderDetail;
import model.OrderHistory;
public class OrderDAO extends MySQLDAO {
	//購買履歴をリストで持ってくる
	//履歴ごとの総額もほしいよな
	//使うかどうかわからないのでいったん保留
	List<OrderHistory> getOrderHistory(int uid)throws Exception{
		List<OrderHistory> ls = new ArrayList<OrderHistory>();
		String sql = "SELECT * FROM order_basic WHERE uid = ?;";
		sql = "SELECT ob.oid "
				+ ", ob.odate "
				+ ", Sum(p.price) AS total "
				+ ", ad.pref "
				+ ", ad.city "
				+ ", pay.payname "
				+ "FROM ((address AS ad "
				+ "INNER JOIN (order_basic as ob "
				+ "INNER JOIN order_detail as od "
				+ "ON ob.oid = od.oid) "
				+ "ON (ad.aid = ob.aid) AND (ad.uid = ob.uid)) "
				+ "INNER JOIN product as p "
				+ "ON od.pid = p.pid)  "
				+ "INNER JOIN payment as pay "
				+ "ON ob.opayment = pay.payid "
				+ "WHERE (((ob.uid)=?)) "
				+ "GROUP BY ob.oid "
				+ ", ob.odate "
				+ ", ad.pref "
				+ ", ad.city "
				+ ", pay.payname"
				+ "ORDER BY ob.odate DESC"
				+ ";";

		PreparedStatement Pstmt = getPreparedStatement(sql);
		Pstmt.setInt(1, uid);
		ResultSet rs = Pstmt.executeQuery();
		while(rs.next()){
			OrderHistory oh = new OrderHistory();
			//TODO ここから先は未着手。というか後回し


		}


		return ls;


	}
	//registOrder
	//注文完了時に各種テーブルにinsert
	//成功したらTrue
	//失敗したらfalseを返す
	//引数 	Address型,OrderBasic型,OrderDetail型のリストから構成
	public boolean registOrder(Address ad,OrderBasic ob,List<OrderDetail> od)throws Exception{
//		boolean f = false;
		Date d = new Date();
		String sql;
		int n;
		int i;
		OrderDetail tmp;
		PreparedStatement Pstmt;
		//宛先が新規登録の場合→Addressの登録を行い、obのaidに発番されたaidを格納する必要がある
		if((ad.getAid())==0){
			sql = "INSERT INTO address(uid, zip3, zip4, pref, city, address1,address2, tel,aname,mobile) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?);";
			Pstmt = getPreparedStatement(sql);
			Pstmt.setInt(1, ad.getUid());
			Pstmt.setString(2, ad.getZip3());
			Pstmt.setString(3, ad.getZip4());
			Pstmt.setString(4, ad.getPref());
			Pstmt.setString(5, ad.getCity());
			Pstmt.setString(6, ad.getAddress1());
			Pstmt.setString(7, ad.getAddress2());
			Pstmt.setString(8,ad.getTel());
			Pstmt.setString(9, ad.getAname());
			Pstmt.setString(10, ad.getMobile());
			try{
				n = Pstmt.executeUpdate();
				if(n>0){
					super.commit();
					}else{
						super.rollback();
						return false;
					}

			}catch(Exception e){
				e.printStackTrace();
				super.rollback();
				return false;
			}
			sql = "select last_insert_id() FROM address;";
			ResultSet rs = getPreparedStatement(sql).executeQuery();

			//登録済み送付先IDを取得し、obにSet
			n=-1;
			while(rs.next()){
				n = rs.getInt(1);
			}
			ob.setAid(n);

		}
		//注文基本情報の登録
		sql = "INSERT INTO order_basic(uid, aid, odate, opayment) VALUES (?, ?, ?, ?);";
		Pstmt = getPreparedStatement(sql);
		Pstmt.setInt(1, ob.getOid());
		Pstmt.setInt(2, ob.getAid());
		Pstmt.setDate(3, (java.sql.Date) d);
		Pstmt.setInt(4, ob.getOpayment());

		try{
			Pstmt.executeUpdate();
			super.commit();
		}catch(Exception e){
			e.printStackTrace();
			super.rollback();
		;
			return false;
		}
		//登録済みのoidを取得
		n=-1;
		sql = "select last_insert_id() FROM order_basic;";
		ResultSet rs = getPreparedStatement(sql).executeQuery();
		while(rs.next()){
			n = rs.getInt(1);
		}
		//od にoidを登録→このループで登録もしちゃおう
		sql = "INSERT INTO order_detail (oid,odid,pid, amount) VALUES (?, ?, ?,?);";
		Pstmt = getPreparedStatement(sql);
		for(i = 0;i<od.size();i++){
			tmp = od.get(i);
			Pstmt.setInt(1, n);
			Pstmt.setInt(2, tmp.getOdid());
			Pstmt.setInt(3, tmp.getPid());
			Pstmt.setInt(4, tmp.getAmount());
			try{
				Pstmt.executeUpdate();
				super.commit();
			}catch(Exception e){
				super.rollback();
				return false;
			}

		}
		return true;
	}
}
