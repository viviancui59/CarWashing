package ty.entity;


import java.io.Serializable;
public class Goods {
	private int goodsid;
	   private int type;
	   private  String name;
	   private double price;
	   private  String introduction;
	   private  String image;
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Goods [goodsid=" + goodsid + ", type=" + type + ", name="
				+ name + ", price=" + price + ", introduction=" + introduction
				+ ", image=" + image + "]";
	}
	
	   
}
