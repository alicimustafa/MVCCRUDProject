package com.mustafa.web;

import java.util.ArrayList;
import java.util.List;

import com.mustafa.data.ClassType;
import com.mustafa.data.PartyDAO;
import com.mustafa.model.Adventurer;

public class MockPartyDAO implements PartyDAO {
	
	private List<Adventurer> poolList;
	private List<Adventurer> partyList;
	
	public MockPartyDAO() {
		poolList = new ArrayList<>();
		partyList = new ArrayList<>();
		init();
	}
	
	public void init() {
		this.partyList = new ArrayList<>();
		this.poolList = new ArrayList<>();
		this.partyList.add(new Adventurer("Gunther", ClassType.FIGHTHER));
		this.partyList.add(new Adventurer("Dormus", ClassType.ARCHER));
		this.partyList.add(new Adventurer("Loral", ClassType.CLERIC));
		this.partyList.add(new Adventurer("Lycia", ClassType.MAGE));
		
		this.poolList.add(new Adventurer("Talafane", ClassType.ROGUE));
		this.poolList.add(new Adventurer("Fhaga", ClassType.MAGE));
		this.poolList.add(new Adventurer("Valcon", ClassType.FIGHTHER));
		this.poolList.add(new Adventurer("Thormund", ClassType.CLERIC));
	}
	
	@Override
	public void addTocharacterPool(Adventurer character) {
		this.poolList.add(character);
	}

	@Override
	public void moveCharacterToParty(int index) {
		Adventurer moved = this.poolList.remove(index);
		this.partyList.add(moved);
	}

	@Override
	public void moveCharacterToPool(int index) {
		Adventurer moved = this.partyList.remove(index);
		this.poolList.add(moved);
	}

	@Override
	public Adventurer getCharacterFromParty(int index) {
		return partyList.get(index);
	}

	@Override
	public Adventurer getCharacterFromPool(int index) {
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
	public List<Adventurer> getPoolList() {
		return this.poolList;
	}

	@Override
	public void setPoolList(List<Adventurer> poolList) {
		this.poolList = poolList;
	}

	@Override
	public List<Adventurer> getPartyList() {
		return partyList;
	}

	@Override
	public void setPartyList(List<Adventurer> partyList) {
		this.partyList = partyList;
	}
	
	

}
