package com.mustafa.model;

public class Input {
	private String name;
	private String classType;
	private String mainHand;
	private String offHand;
	private String armor;
	private int id;
	private String table;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getMainHand() {
		return mainHand;
	}
	public void setMainHand(String mainHand) {
		this.mainHand = mainHand;
	}
	public String getOffHand() {
		return offHand;
	}
	public void setOffHand(String offHand) {
		this.offHand = offHand;
	}
	public String getArmor() {
		return armor;
	}
	public void setArmor(String armor) {
		this.armor = armor;
	}
	@Override
	public String toString() {
		return "Input [name=" + name + ", classType=" + classType + ", mainHand=" + mainHand + ", offHand=" + offHand
				+ ", armor=" + armor + "]";
	}
	
	
	
}
