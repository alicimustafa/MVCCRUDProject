package com.mustafa.data;

import java.util.List;

import com.mustafa.model.Adventurer;

public interface PartyDAO {
	public void addTocharacterPool(Adventurer character);
	public void moveCharacterToParty(int index);
	public void moveCharacterToPool(int index);
	public void deletCharacterFromPool(int index);
	public Adventurer getCharacterFromParty(int index);
	public Adventurer getCharacterFromPool(int index);
	public List<Adventurer> getPoolList();
	public void setPoolList(List<Adventurer> poolList);
	public List<Adventurer> getPartyList();
	public void setPartyList(List<Adventurer> partyList);
	void save();
}
