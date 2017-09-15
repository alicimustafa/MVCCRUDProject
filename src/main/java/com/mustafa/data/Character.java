package com.mustafa.data;

import java.util.ArrayList;
import java.util.List;

public class Character {
	private String name;
	private ClassType characterClass;
	private Items mainHand;
	private Items offHand;
	private Items armor;
	private List<Items> backpack;

	public Character(String name, ClassType characterClass) {
		super();
		this.name = name;
		this.characterClass = characterClass;
		this.backpack = new ArrayList<>();
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
	
	
}
