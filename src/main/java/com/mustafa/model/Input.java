package com.mustafa.model;

public class Input {
	private int id;
	private String name;
	private String classType;
	private int mainHand;
	private int offHand;
	private int armor;
	private String table;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMainHand() {
		return mainHand;
	}

	public void setMainHand(int mainHand) {
		this.mainHand = mainHand;
	}

	public int getOffHand() {
		return offHand;
	}

	public void setOffHand(int offHand) {
		this.offHand = offHand;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Input [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", classType=");
		builder.append(classType);
		builder.append(", mainHand=");
		builder.append(mainHand);
		builder.append(", offHand=");
		builder.append(offHand);
		builder.append(", armor=");
		builder.append(armor);
		builder.append(", table=");
		builder.append(table);
		builder.append("]");
		return builder.toString();
	}
	
}
