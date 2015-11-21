package model;

import java.io.Serializable;
import java.util.Date;

public class OrderBasic implements Serializable {
	private	int	oid	;
	private	int	uid	;
	private	int	aid	;
	private	Date	odate	;
	private	int	opayment	;

	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getUid() {
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
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public int getOpayment() {
		return opayment;
	}
	public void setOpayment(int opayment) {
		this.opayment = opayment;
	}


}
