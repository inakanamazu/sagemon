package model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import dao.AddressDAO;
import dao.PaymentDAO;
public class SelectAddressLogic {

	//該当ユーザIDの送付先リストを取得
	public  List<Address> getAddress(int uid) throws Exception{
		AddressDAO dao = new AddressDAO();
		List<Address> ls = null;
		try{
			ls = dao.SelectByUid(uid);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}finally{
			dao.closeConnection();
		}
		return ls;
	}
	//決済方法リストを取得
	public List<Payment> getPayment() throws Exception{
		PaymentDAO dao = new PaymentDAO();
		List<Payment> ls = new ArrayList<Payment>();
		 try{
			 ls= dao.SelectAll();
		 }catch(Exception e){
				e.printStackTrace();
				throw new ServletException(e);
		 }finally{
			 dao.closeConnection();
		 }
		return ls;

	}

}
