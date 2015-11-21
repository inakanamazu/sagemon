package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import model.Product;


public class ProductDAO extends MySQLDAO{
  public List<Product>  findALL()throws Exception  {



      // SELECT文を準備
      String sql = "SELECT * FROM Product";
      PreparedStatement   pStmt= getPreparedStatement(sql);
                                 //SQLの準備


      // SELECTを実行し、結果表を取得
      ResultSet rs = pStmt.executeQuery();
                                        //DBのSQIを発行し結果を受け取る

      List<Product> list= new ArrayList<Product>();
      while (rs.next()) {
        // 結果表からデーtタを取得
    	  int pid = rs.getInt("pid");
    	  int cid = rs.getInt("cid");
          String pname = rs.getString("pname");
          int price = rs.getInt("price");
          String description = rs.getString("description");
          String image = rs.getString("image");

        Product product = new Product(pid, cid, pname,price,description,image);
        list.add(product);
      }

    return list;
  }
}
