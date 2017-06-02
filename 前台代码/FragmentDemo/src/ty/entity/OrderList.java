package ty.entity;

import java.io.Serializable;

public class OrderList implements Serializable{

	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	
	private String orderid;
	private int carid;
	private int storeid;
	private int userid;
	private String time;
	private float price;
	public String getCarplate() {
		return carplate;
	}
	public void setCarplate(String carplate) {
		this.carplate = carplate;
	}

	private int type;
	private String storename;
	private String carplate;

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "OrderList [orderid=" + orderid + ", carplate=" + carplate
				+ ", storeid=" + storeid + ", userid=" + userid + ", time="
				+ time + ", price=" + price + ", type=" + type + "]";
	}

	
}
