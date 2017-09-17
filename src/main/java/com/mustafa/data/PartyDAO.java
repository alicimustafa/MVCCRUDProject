package com.mustafa.data;

import java.util.List;

public interface PartyDAO {
	public void addTocharacterPool(Character character);
	public void moveCharacterToParty(int index);
	public void moveCharacterToPool(int index);
	public void deletCharacterFromPool(int index);
	public Character getCharacterFromParty(int index);
	public Character getCharacterFromPool(int index);
	public List<Character> getPoolList();
	public void setPoolList(List<Character> poolList);
	public List<Character> getPartyList();
	public void setPartyList(List<Character> partyList);
	void save();
}
