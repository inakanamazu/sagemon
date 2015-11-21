//-------------------------------------------------
//2015/11/17 MOMONO
//-------------------------------------------------

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Product;


public class CartDAO extends MySQLDAO{
	public Product select(int pid) throws Exception{

		//System.out.println("【DAO】★dao起動");

		//★実行するSQL文を作成
		String sql = "SELECT pid, cid, pname, price, description,  FROM product_tbl WHERE pid=?";

		//
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setInt(1, pid);


		ResultSet rs = statement.executeQuery();

		//★デバッグ用：SQL文の確認
		System.out.println("【DAO】生成されたSQL文"+statement);

		Product product = new Product();

		//検索結果をbeans"item"に格納する
		while(rs.next()){

			product.setPid(rs.getInt("pid"));
			product.setCid(rs.getInt("cid"));
			product.setPname(rs.getString("pname"));
			product.setPrice(rs.getInt("price"));
			product.setDescription(rs.getString("description"));
			product.setImage(rs.getString("image"));

			//★デバッグ用：格納された要素の確認
			System.out.println("【DAO】id"+product.getPid());
			System.out.println("【DAO】p_name"+product.getPname());
			System.out.println("【DAO】price"+product.getPrice());


		}

		return product;
	}

}
