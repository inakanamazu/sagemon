package model;

import java.io.Serializable;

public class Address implements Serializable {

	private int uid;
	private int aid;
	private String zip3;
	private String zip4;
	private String pref;
	private String city;
	private String name;

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getUid(){
		return uid;
	}



	public void setUid(int uid) {
		this.uid = uid;
	}



	public int getAid() {
		return aid;
	}



	public void setAid(int aid) {
		this.aid = aid;
	}



	public String getZip3() {
		return zip3;
	}



	public void setZip3(String zip3) {
		this.zip3 = zip3;
	}



	public String getZip4() {
		return zip4;
	}



	public void setZip4(String zip4) {
		this.zip4 = zip4;
	}



	public String getPref() {
		return pref;
	}



	public void setPref(String pref) {
		this.pref = pref;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getAname() {
		return aname;
	}



	public void setAname(String aname) {
		this.aname = aname;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	private String address1;
	private String address2;
	private String tel;
	private String aname;
	private String mobile;



	public Address(){

	}

}
