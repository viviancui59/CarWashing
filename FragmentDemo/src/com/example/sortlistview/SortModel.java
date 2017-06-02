package com.example.sortlistview;

import ty.entity.CarBrand;

public class SortModel {

	//private String name;   //��ʾ�����
	private String sortLetters;  //��ʾ���ƴ��������ĸ
	private CarBrand carbrand;
	
	public CarBrand getcarbrand() {
		return carbrand;
	}
	public void setcarbrand(CarBrand carbrand) {
		this.carbrand = carbrand;
	}
	
	
	public String getSortLetters() {
		return sortLetters;
	}
	/*public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}*/
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
