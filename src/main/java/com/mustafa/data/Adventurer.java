package com.mustafa.data;

import java.util.ArrayList;
import java.util.List;

public class Adventurer {
	private String name;
	private ClassType characterClass;
	private Items mainHand;
	private Items offHand;
	private Items armor;
	private List<Items> backpack;

	public Adventurer(String name, ClassType characterClass) {
		super();
		this.name = name;
		this.characterClass = characterClass;
		this.backpack = new ArrayList<>();
	}
	
	
	public Adventurer(String name, ClassType characterClass, Items mainHand, Items offHand, Items armor) {
		super();
		this.name = name;
		this.characterClass = characterClass;
		this.mainHand = mainHand;
		this.offHand = offHand;
		this.armor = armor;
	}


	public void addItemToBackpack(Items item) {
		this.backpack.add(item);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassType getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(ClassType characterClass) {
		this.characterClass = characterClass;
	}

	public Items getMainHand() {
		return mainHand;
	}

	public void setMainHand(Items mainHand) {
		this.mainHand = mainHand;
	}

	public Items getOffHand() {
		return offHand;
	}

	public void setOffHand(Items offHand) {
		this.offHand = offHand;
	}

	public Items getArmor() {
		return armor;
	}

	public void setArmor(Items armor) {
		this.armor = armor;
	}

	public List<Items> getBackpack() {
		return backpack;
	}

	public void setBackpack(List<Items> backpack) {
		this.backpack = backpack;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((armor == null) ? 0 : armor.hashCode());
		result = prime * result + ((backpack == null) ? 0 : backpack.hashCode());
		result = prime * result + ((characterClass == null) ? 0 : characterClass.hashCode());
		result = prime * result + ((mainHand == null) ? 0 : mainHand.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((offHand == null) ? 0 : offHand.hashCode());
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
		if (armor == null) {
			if (other.armor != null)
				return false;
		} else if (!armor.equals(other.armor))
			return false;
		if (backpack == null) {
			if (other.backpack != null)
				return false;
		} else if (!backpack.equals(other.backpack))
			return false;
		if (characterClass != other.characterClass)
			return false;
		if (mainHand == null) {
			if (other.mainHand != null)
				return false;
		} else if (!mainHand.equals(other.mainHand))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (offHand == null) {
			if (other.offHand != null)
				return false;
		} else if (!offHand.equals(other.offHand))
			return false;
		return true;
	}
	
	
}
