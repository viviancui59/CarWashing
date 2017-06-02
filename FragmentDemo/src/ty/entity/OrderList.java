package ty.entity;

import java.io.Serializable;

public class OrderList implements Serializable{

	private String orderid;
	private String plate;
	private int storeid;
	private int userid;
	private String time;
	private float price;
	private int type;
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
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
		return "OrderList [orderid=" + orderid + ", plate=" + plate
				+ ", storeid=" + storeid + ", userid=" + userid + ", time="
				+ time + ", price=" + price + ", type=" + type + "]";
	}

	
	
	
}
