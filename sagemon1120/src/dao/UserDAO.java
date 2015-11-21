package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDAO extends MySQLDAO {

	//IDで検索→これ使うことないかな
	public User getUser(int uid) throws Exception{
		String sql = "SELECT * FROM user WHERE uid = ?;";
		PreparedStatement Pstmt =  getPreparedStatement(sql);
		Pstmt.setInt(1, uid);
		ResultSet rs = Pstmt.executeQuery();
		User u = new User();
		if(rs.next()){
			u.setUid(uid);
			u.setFamilyname(rs.getString("familyname"));
			u.setFirstname(rs.getString("firstname"));
			u.setMail(rs.getString("mail"));
			u.setFamilykana(rs.getString("familykana"));
			u.setFirstkana(rs.getString("firstkana"));
			u.setPassword(rs.getString("password"));
			u.setBirthday(rs.getDate("birthday"));
			u.setGender(rs.getInt("gender"));
			u.setStatus(rs.getInt(rs.getInt("status")));
		}else{
			u.setErrmsg("指定したユーザは存在しません。");
		}
		return u;
	}

}
