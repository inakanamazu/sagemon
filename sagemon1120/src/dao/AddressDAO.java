package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Address;
public class AddressDAO extends MySQLDAO {


	/* ユーザIDから送付先リストを抽出するメソッド  */
	public List<Address> SelectByUid(int uid)throws Exception{
		List<Address> ls = new ArrayList<Address>();
		String sql = "SELECT * FROM address WHERE uid=?;";
		PreparedStatement Pstmt = getPreparedStatement(sql);
		Pstmt.setInt(1,uid);
		System.out.println("▼SelectByUid▼\n"+Pstmt);
		ResultSet rs = Pstmt.executeQuery();
		while(rs.next()){
			Address tmp = new Address();
			tmp.setAddress1(rs.getString("address1"));
			tmp.setAddress2(rs.getString("address2"));
			tmp.setAid(rs.getInt("aid"));
			tmp.setAname(rs.getString("aname"));
			tmp.setCity(rs.getString("city"));
			tmp.setMobile(rs.getString("mobile"));
			tmp.setPref(rs.getString("pref"));
			tmp.setTel(rs.getString("tel"));
			tmp.setUid(uid);
			tmp.setZip3(rs.getString("zip3"));
			tmp.setZip4(rs.getString("zip4"));
			tmp.setName(rs.getString("name"));

			ls.add(tmp);
		}
		return ls;

	}
	/* ユーザID＆送付先IDから送付先を特定するメソッド */
	public Address SelectByUidAid(int uid,int aid)throws Exception{
		Address tmp = new Address();
		String sql = "SELECT * FROM address WHERE uid=? AND aid=?;";
		PreparedStatement Pstmt = getPreparedStatement(sql);
		Pstmt.setInt(1, uid);
		Pstmt.setInt(2, aid);
		System.out.println("▼SelectByUidAid▼\n"+Pstmt);

		ResultSet rs = Pstmt.executeQuery();
		if(rs.next()){
			tmp.setAddress1(rs.getString("address1"));
			tmp.setAddress2(rs.getString("address2"));
			tmp.setAid(aid);
			tmp.setUid(uid);
			tmp.setAname(rs.getString("aname"));
			tmp.setCity(rs.getString("city"));
			tmp.setMobile(rs.getString("mobile"));
			tmp.setPref(rs.getString("pref"));
			tmp.setTel(rs.getString("tel"));
			tmp.setUid(uid);
			tmp.setZip3(rs.getString("zip3"));
			tmp.setZip4(rs.getString("zip4"));
			tmp.setName(rs.getString("name"));
		}
		return tmp;
	}

	/* 送付先情報を登録するメソッド */
	public boolean InsertAddress(Address ad)throws Exception{
		boolean f = false;
		String sql = "INSERT INTO address(uid, zip3, zip4, pref, city, address1,address2, tel,aname,mobile,name) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?);";
		PreparedStatement Pstmt = getPreparedStatement(sql);
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
		Pstmt.setString(11,ad.getName());
		System.out.println("▼InsertAddress▼\n"+Pstmt);

		try{
			int n = Pstmt.executeUpdate();
			if(n>0){f=true;}
		}catch(Exception e){
			e.printStackTrace();

		}

		return f;
	}
	//最新のaid を取得
	public int getLastId()throws Exception{
		int n = -1;
		String sql = "select last_insert_id() FROM address;";
		ResultSet rs = getPreparedStatement(sql).executeQuery();
		if(rs.next()){
			n=rs.getInt(1);
		}

		return n;
	}
}

