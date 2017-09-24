package com.mustafa.data;

import java.util.List;

import com.mustafa.model.Adventurer;

public interface PartyDAO {
	public Adventurer addTocharacterPool(Adventurer character);
	public void moveCharacterToParty(int index);
	public void moveCharacterToPool(int index);
	public void deletCharacterFromPool(int index);
	public Adventurer getCharacterFromParty(int index);
	public Adventurer getCharacterFromPool(int index);
	public List<Adventurer> getPoolList();
	public void setPoolList(List<Adventurer> poolList);
	public List<Adventurer> getPartyList();
	public void setPartyList(List<Adventurer> partyList);
	public List<String> getClassTypes();
	public void updatePool(int index, Adventurer character);
	public void updateParty(int index, Adventurer character);
}
