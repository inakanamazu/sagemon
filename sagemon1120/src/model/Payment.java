package model;

import java.io.Serializable;

public class Payment implements Serializable {
	private int payid;
	public int getPayid() {
		return payid;
	}



	public void setPayid(int payid) {
		this.payid = payid;
	}



	public String getPayname() {
		return payname;
	}



	public void setPayname(String payname) {
		this.payname = payname;
	}



	private String payname;



	public Payment(){}


}
