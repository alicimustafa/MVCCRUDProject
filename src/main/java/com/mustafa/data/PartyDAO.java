package com.mustafa.data;

import java.util.List;

public interface PartyDAO {
	void addTocharacterPool();
	void moveCharacterToParty();
	void moveCharacterToPool();
	List<Character> getPartyMembers();
	List<Character> getPoolMembers();
	Character getCharacterFromParty();
	Character getCharacterFromPool();
	void save();
}
