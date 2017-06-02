package ty.entity;

import java.io.Serializable;

public class Car implements Serializable {

	private int carid;
	private int carbrandid;
	private String plate;
	private String brandtype;
	private String brandimage;
	
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public int getCarbrandid() {
		return carbrandid;
	}
	public void setCarbrandid(int carbrandid) {
		this.carbrandid = carbrandid;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getBrandtype() {
		return brandtype;
	}
	public void setBrandtype(String brandtype) {
		this.brandtype = brandtype;
	}
	public String getBrandimage() {
		return brandimage;
	}
	public void setBrandimage(String brandimage) {
		this.brandimage = brandimage;
	}
	@Override
	public String toString() {
		return "Car [carid=" + carid + ", carbrandid=" + carbrandid + ", plate=" + plate + ", brandtype=" + brandtype
				+ ", brandimage=" + brandimage + "]";
	}
	
}
