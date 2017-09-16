package com.mustafa.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartyDAOImpl implements PartyDAO {
	
	@Autowired
	ItemDAOImpl itemDAO;
	
	private List<Character> poolList;
	private List<Character> partyList;
	
	public PartyDAOImpl() {
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
	
	public ItemDAOImpl getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAOImpl itemDAO) {
		this.itemDAO = itemDAO;
	}

	public List<Character> getPoolList() {
		return poolList;
	}

	public void setPoolList(List<Character> poolList) {
		this.poolList = poolList;
	}

	public List<Character> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<Character> partyList) {
		this.partyList = partyList;
	}
	
	

	@Override
	public void save() {
		// TODO Auto-generated method stub

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

}
