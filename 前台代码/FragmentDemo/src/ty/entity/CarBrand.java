package ty.entity;

import java.io.Serializable;

public class CarBrand implements Serializable {

	private int carbrandid;
	private String type;
	private String image;
	public int getCarbrandid() {
		return carbrandid;
	}
	public void setCarbrandid(int carbrandid) {
		this.carbrandid = carbrandid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "CarBrand [carbrandid=" + carbrandid + ", type=" + type
				+ ", image=" + image + "]";
	}
	
}
