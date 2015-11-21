package model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private int uid;
	private String familyname;
	private String firstname;
	private String mail;
	private String familykana;
	private String firstkana;
	private String password;
	private Date birthday;
	private int gender;
	private int status;
	//以下、DBでは持たない情報
	private String mail_chk;
	private String errmsg;
	private String passwordchk;


	public String getMail_chk() {
		return mail_chk;
	}


	public void setMail_chk(String mail_chk) {
		this.mail_chk = mail_chk;
	}


	public String getErrmsg() {
		return errmsg;
	}


	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


	public String getPasswordchk() {
		return passwordchk;
	}


	public void setPasswordchk(String passwordchk) {
		this.passwordchk = passwordchk;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getFamilyname() {
		return familyname;
	}


	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getFamilykana() {
		return familykana;
	}


	public void setFamilykana(String familykana) {
		this.familykana = familykana;
	}


	public String getFirstkana() {
		return firstkana;
	}


	public void setFirstkana(String firstkana) {
		this.firstkana = firstkana;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}




	public User(){}


}
