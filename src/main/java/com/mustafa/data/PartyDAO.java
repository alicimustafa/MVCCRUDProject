package com.mustafa.data;

import java.util.List;

public interface PartyDAO {
	void addTocharacterPool(int index);
	void moveCharacterToParty(int index);
	void moveCharacterToPool(int index);
	List<Character> getPartyMembers();
	List<Character> getPoolMembers();
	Character getCharacterFromParty(int index);
	Character getCharacterFromPool(int index);
	void save();
}
