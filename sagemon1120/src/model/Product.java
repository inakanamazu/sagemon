package model;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;


	private int pid;
	private int cid;
	private String pname;
	private int price;
	private String description;
	private String image;
	private int qty;

	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public Product(){}


	public Product(int pid, int cid, String pname, int price,
			String description, String image) {
		super();
		this.pid = pid;
		this.cid = cid;
		this.pname = pname;
		this.price = price;
		this.description = description;
		this.image = image;
	}


	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}



}
