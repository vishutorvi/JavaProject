package com.uttara.vo;

public class LinkRefVO{

	private String href;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	@Override
	public String toString() {
		return "\'"+href+"\'";
	}	
}