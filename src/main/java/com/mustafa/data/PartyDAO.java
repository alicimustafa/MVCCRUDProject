package com.mustafa.data;

public interface PartyDAO {
	public void addTocharacterPool(Character character);
	public void moveCharacterToParty(int index);
	public void moveCharacterToPool(int index);
	public void deletCharacterFromPool(int index);
	public Character getCharacterFromParty(int index);
	public Character getCharacterFromPool(int index);
	void save();
}
