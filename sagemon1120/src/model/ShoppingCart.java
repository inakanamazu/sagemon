//-------------------------------------------------
//2015/11/17 MOMONO
//-------------------------------------------------

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	//カートに入ってる商品リスト
	private List<Product> productsCart;
	private int totalPrice;
	//合計金額


	public ShoppingCart(){

		productsCart = new ArrayList<Product>();
	}

	//Product型のリストproductsCartを返すgetter
	public List<Product> getProductsCart() {
		return productsCart;
	}

	public void setProductsCart(List<Product> productsCart) {
		this.productsCart = productsCart;
	}

	public void addProduct(Product product,int qty){

		//デバッグ用
		//System.out.println("Product:"+ product);
		//System.out.println("Qty:"+ qty);


		boolean flag = false; //フラグの変数
		Product temp;

		//すでにカート内にある商品が追加された場合
		if(productsCart != null){
			for(int i = 0; i< productsCart.size(); i++){

				temp = productsCart.get(i);

				if(product.getPid() == temp.getPid()){
					temp.setQty(temp.getQty() + qty);


					flag = true;
					getTotalPrice();

					break;
				}
			}
		}

		if(!flag && qty != 0){

			product.setQty(qty);
			productsCart.add(product);

			getTotalPrice();
		}
	}

	//ショッピングカートからitemと数量を削除するメソッド
	public void removeItem(Product product){

		Product temp;

		//カートが空でない場合
		if(productsCart != null){
			for(int i = 0; i< productsCart.size();i++){
				temp = productsCart.get(i); //一時リストにカート内容を保管

				//商品の名称がリストにあった場合
				if(product.getPid() == temp.getPid()){
					//数量が0以下になったら項目を削除
						productsCart.remove(i);
				}
			}
		}
	}



	//合計金額を返すメソッド
	public int getTotalPrice(){

		//

		int total = 0;
		for(Product product:productsCart){
			total += product.getPrice() * product.getQty();
		}

			totalPrice = total;
			return totalPrice;

	}


}
