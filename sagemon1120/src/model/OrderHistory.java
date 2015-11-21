package model;

import java.io.Serializable;

public class OrderHistory extends OrderBasic implements Serializable {
	public OrderHistory(){}
	private int total;
	private String sendTo;

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}




}
