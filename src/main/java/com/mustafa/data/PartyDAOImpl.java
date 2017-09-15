package com.mustafa.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartyDAOImpl implements PartyDAO {
	
	@Autowired
	ItemDAOImpl itemDAO;
	
	List<Character> poolList;
	List<Character> partyList;
	

	@Override
	public List<Character> getPartyMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> getPoolMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTocharacterPool(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveCharacterToParty(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveCharacterToPool(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Character getCharacterFromParty(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Character getCharacterFromPool(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
