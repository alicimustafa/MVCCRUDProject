package com.mustafa.web;

import java.util.ArrayList;
import java.util.List;

import com.mustafa.data.Character;
import com.mustafa.data.ClassType;
import com.mustafa.data.PartyDAO;

public class MockPartyDAO implements PartyDAO {
	
	private List<Character> poolList;
	private List<Character> partyList;
	
	public MockPartyDAO() {
		poolList = new ArrayList<>();
		partyList = new ArrayList<>();
		init();
	}
	
	private void init() {
		this.partyList.add(new Character("Gunther", ClassType.FIGHTHER));
		this.partyList.add(new Character("Dormus", ClassType.ARCHER));
		this.partyList.add(new Character("Loral", ClassType.CLERIC));
		this.partyList.add(new Character("Lycia", ClassType.MAGE));
		
		this.poolList.add(new Character("Talafane", ClassType.ROGUE));
		this.poolList.add(new Character("Fhaga", ClassType.MAGE));
		this.poolList.add(new Character("Valcon", ClassType.FIGHTHER));
		this.poolList.add(new Character("Thormund", ClassType.CLERIC));
	}
	
	@Override
	public void addTocharacterPool(Character character) {
		this.poolList.add(character);
	}

	@Override
	public void moveCharacterToParty(int index) {
		Character moved = this.poolList.remove(index);
		this.partyList.add(moved);
	}

	@Override
	public void moveCharacterToPool(int index) {
		Character moved = this.partyList.remove(index);
		this.poolList.add(moved);
	}

	@Override
	public Character getCharacterFromParty(int index) {
		return partyList.get(index);
	}

	@Override
	public Character getCharacterFromPool(int index) {
		return poolList.get(index);
	}

	@Override
	public void deletCharacterFromPool(int index) {
		this.poolList.remove(index);
	}

	@Override
	public void save() {
		// TODO Auto-generated me
	}

	@Override
	public List<Character> getPoolList() {
		return this.poolList;
	}

	@Override
	public void setPoolList(List<Character> poolList) {
		this.poolList = poolList;
	}

	@Override
	public List<Character> getPartyList() {
		return partyList;
	}

	@Override
	public void setPartyList(List<Character> partyList) {
		this.partyList = partyList;
	}
	
	

}
