package com.mustafa.model;

import java.util.List;

public class Adventurer {
	private int id;
	private String name;
	private String characterClass;
	private Item mainHand;
	private Item offHand;
	private Item armor;
	private List<Item> backpack;

	public void addItemToBackpack(Item item) {
		this.backpack.add(item);
	}
	
	public Adventurer() {
	}
	
	public Adventurer(int id, String name, String characterClass) {
		super();
		this.id = id;
		this.name = name;
		this.characterClass = characterClass;
	}

	public Adventurer(int id, String name, String characterClass, Item mainHand, Item offHand, Item armor) {
		super();
		this.id = id;
		this.name = name;
		this.characterClass = characterClass;
		this.mainHand = mainHand;
		this.offHand = offHand;
		this.armor = armor;
	}

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

	public String getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}

	public Item getMainHand() {
		return mainHand;
	}

	public void setMainHand(Item mainHand) {
		this.mainHand = mainHand;
	}

	public Item getOffHand() {
		return offHand;
	}

	public void setOffHand(Item offHand) {
		this.offHand = offHand;
	}

	public Item getArmor() {
		return armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public List<Item> getBackpack() {
		return backpack;
	}

	public void setBackpack(List<Item> backpack) {
		this.backpack = backpack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adventurer other = (Adventurer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adventurer [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", characterClass=");
		builder.append(characterClass);
		builder.append(", mainHand=");
		builder.append(mainHand);
		builder.append(", offHand=");
		builder.append(offHand);
		builder.append(", armor=");
		builder.append(armor);
		builder.append(", backpack=");
		builder.append(backpack);
		builder.append("]");
		return builder.toString();
	}
	
}
