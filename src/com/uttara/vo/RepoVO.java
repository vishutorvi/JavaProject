package com.uttara.vo;

public class RepoVO{
	private long id;
	private String name;
	private String full_name;
	private UserVO owner;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public UserVO getOwner() {
		return owner;
	}
	public void setOwner(UserVO owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		String returnString;
		returnString = id + ",\'" + name + "\',\'"+ full_name + "\',";
		if(owner != null){
			returnString += owner.toString();
		}else{
			returnString += UserVO.nullStrings();
		}
		return returnString;
	}
	/**
	 * Method to insert null for each parameter if the object is null 
	 * @return string
	 */
	public static String nullStrings(){
		String returnString;
		returnString = "0,null,null";
		returnString += UserVO.nullStrings();
		return returnString;
	}
}