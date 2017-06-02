package ty.entity;

import java.io.Serializable;

public class Store implements Serializable{
	
	private int storeid;
	private String name;
	private String image;
	private String address;
	private String openingtime;
	private String closingtime;
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpeningtime() {
		return openingtime;
	}
	public void setOpeningtime(String openingtime) {
		this.openingtime = openingtime;
	}
	public String getClosingtime() {
		return closingtime;
	}
	public void setClosingtime(String closingtime) {
		this.closingtime = closingtime;
	}
	@Override
	public String toString() {
		return "Store [storeid=" + storeid + ", name=" + name + ", image="
				+ image + ", address=" + address + ", openingtime="
				+ openingtime + ", closingtime=" + closingtime + "]";
	}
	
}
