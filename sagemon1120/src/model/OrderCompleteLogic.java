package model;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import dao.AddressDAO;
import dao.MySQLDAO;
import dao.OrderBasicDAO;
import dao.OrderDetailDAO;
public class OrderCompleteLogic {

	public boolean isNumber(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}

	//アドレスが新たに選択された際送付先情報をInsertするメソッド
	//これつかってないや…
	public boolean registAddress(Address a)throws Exception{
		boolean f = false;
		AddressDAO dao = new AddressDAO();
		try{
			System.out.println("★registAddress→tryIn:"+f);
			f= dao.InsertAddress(a);
			System.out.println("★registAddress→resultIns:"+f);
		//都度commitさせる
			if(f){
				dao.commit();
				System.out.println("★registAddress→commit");
			}else{
				dao.rollback();
			}
		}catch(Exception e){
			dao.rollback();
			e.printStackTrace();
			throw new ServletException(e);

		}finally{
			dao.closeConnection();
			System.out.println("★registAddress→close");
		}
		return f;
	}

	//送付先情報の入力チェック
	public String AddressCheck(Address ad){
		String errMsg = "";
		String tmp="";
		//zip3
		tmp=ad.getZip3();
		System.out.println("zip3chk:"+tmp);
		if(tmp==null){
			errMsg+="郵便番号3が入力されていません。\n";
		}else if(tmp.equals("")){
			errMsg+="郵便番号3が入力されていません。\n";
		}else if(tmp.length()!=3){
			errMsg+="郵便番号3は3桁で入力してください。\n";
		}else if(!isNumber(tmp)){
			errMsg+="郵便番号は3桁の数字で入力してください。\n";
		}
		//zip4
		tmp=ad.getZip4();
		System.out.println("zip4chk:"+tmp);

		if(tmp==null){
			errMsg+="郵便番号4が入力されていません。\n";
		}else if(tmp.equals("")){
			errMsg+="郵便番号4が入力されていません。\n";
		}else if(tmp.length()!=4){
			errMsg+="郵便番号4は4桁で入力してください。\n";
		}else if(!isNumber(tmp)){
			errMsg+="郵便番号4は4桁の数字で入力してください。\n";
		}
		//pref
		tmp=ad.getPref();
		System.out.println("prefchk:"+tmp);
		if(tmp==null){
			errMsg+="都道府県が入力されていません。\n";
		}else if(tmp.equals("")){
			errMsg+="都道府県が入力されていません。\n";
		}
		//city
		tmp=ad.getCity();
		System.out.println("citychk:"+tmp);
		if(tmp==null){
			errMsg+="市区町村が入力されていません。\n";
		}else if(tmp.equals("")){
			errMsg+="市区町村が入力されていません。\n";
		}
		//address1
		tmp=ad.getAddress1();
		System.out.println("ad1chk:"+tmp);
		if(tmp==null){
			errMsg+="住所が入力されていません。\n";
		}else if(tmp.equals("")){
			errMsg+="住所が入力されていません。\n";
		}
		//name
		tmp=ad.getName();
		System.out.println("namechk:"+tmp);
		if(tmp==null){
			errMsg+="受取人氏名が入力されていません。\n";
		}else if(tmp.equals("")){
			errMsg+="受取人氏名が入力されていません。\n";
		}
		//mobile or tel
		tmp=ad.getTel();
		String tmp2=ad.getMobile();
		if(tmp==null && tmp2==null){
			errMsg+="電話番号または携帯電話番号のいずれか一方は入力してください。\n";
		}else if(tmp==null && tmp2.equals("")){
			errMsg+="電話番号または携帯電話番号のいずれか一方は入力してください。\n";
		}else if(tmp.equals("") && tmp2==null){
			errMsg+="電話番号または携帯電話番号のいずれか一方は入力してください。\n";
		}else if(tmp.equals("") && tmp2.equals("")){
			errMsg+="電話番号または携帯電話番号のいずれか一方は入力してください。\n";
		}

		return errMsg;
	}
	//AddressのInsに成功した場合、LastIDを返すメソッド
	public int getLastaid(Address a)throws Exception {
		int n = -1;
		AddressDAO dao = new AddressDAO();
		if(dao.InsertAddress(a)){
			dao.commit();
			System.out.println("★registAddress→commit");
			n = dao.getLastId();
		}else{
			dao.rollback();
		}
		dao.closeConnection();
		System.out.println("★registAddress→close");

		return n;
	}
	public int getLastoid(OrderBasic ob)throws Exception{
		int n=-1;
		OrderBasicDAO dao = new OrderBasicDAO();
		if(dao.registOrderBasic(ob)){
		//つどcommitさせる
				dao.commit();
				System.out.println("★registOrderBasic→commit");
			n = dao.getLastId();
		}else{
			dao.rollback();
		}
		dao.closeConnection();
		System.out.println("★registOrderBasic→close");

		return n;
	}

	public List<OrderDetail> sp2od(ShoppingCart sp,int oid){
		List<OrderDetail> odl = new ArrayList<OrderDetail>();
		List<Product> pl = sp.getProductsCart();
		for(int i=0;i<pl.size();i++){
			Product p = pl.get(i);
			OrderDetail od = new OrderDetail();
			od.setOid(oid);
			od.setOdid(i+1);
			od.setPid(p.getPid());
			od.setAmount(p.getQty());
			odl.add(od);
		}
		return odl;
	}
	public boolean setod(List<OrderDetail> od)throws Exception{
		OrderDetailDAO dao = new OrderDetailDAO();
		boolean f=false;
		try{
			if(dao.registOrderDetail(od)){
				try{
					dao.commit();
					f=true;
				}catch(Exception e){
					dao.rollback();
					f=false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			f=false;
		}finally{
			dao.closeConnection();
		}
		return f;

	}
	public boolean commitAll()throws Exception{
		MySQLDAO dao = new MySQLDAO();
		boolean f=false;
		try{
			dao.commit();
			f=true;

		}catch(Exception e){
			e.printStackTrace();
			dao.rollback();
			f=false;
		}finally{
			dao.closeConnection();
		}
		return f;
	}

	//以下、デバッグ用
	public void debugsp(ShoppingCart sp){
		List<Product> pl = sp.getProductsCart();
		for(int i=0;i<pl.size();i++){
			Product p  = pl.get(i);
			System.out.println(i+":"+p.getCid()+":"+p.getPname());

		}
		return;
	}
}




