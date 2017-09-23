package com.mustafa.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mustafa.model.Adventurer;
import com.mustafa.model.Item;

@Component
public class PartyDAOImpl implements PartyDAO {
	
	private static final String POOL_FILE = "/WEB-INF/CSVfiles/PoolList.csv";
	private static final String PARTY_FILE = "/WEB-INF/CSVfiles/PartyList.csv";
	
	@Autowired
	private WebApplicationContext wac;
	
	private List<Adventurer> poolList;
	private List<Adventurer> partyList;
	private List<String> classTypes;
	
	public PartyDAOImpl() {
		poolList = new ArrayList<>();
		partyList = new ArrayList<>();
		classTypes = new ArrayList<>();
		init();
	}
	
	private void init() {
//		this.loadParty();
//		this.loadPool();
		this.classTypes.add("MAGE");
		this.classTypes.add("ARCHER");
		this.classTypes.add("FIGHTHER");
		this.classTypes.add("CLERIC");
		this.classTypes.add("ROGUE");
		this.partyList.add(new Adventurer(1,"Gunther", "FIGHTHER"));
		this.partyList.add(new Adventurer(2,"Dormus", "ARCHER"));
		this.partyList.add(new Adventurer(3,"Loral", "CLERIC"));
		this.partyList.add(new Adventurer(4,"Lycia", "MAGE"));
		
		this.poolList.add(new Adventurer(5,"Talafane", "ROGUE"));
		this.poolList.add(new Adventurer(6,"Fhaga", "MAGE"));
		this.poolList.add(new Adventurer(7,"Valcon", "FIGHTHER"));
		this.poolList.add(new Adventurer(8,"Thormund", "CLERIC"));
	}
	
	private void loadParty() {
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(PARTY_FILE);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
				) 
		{
			String line = "";
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0]);
				String name = tokens[1];
				String classType = classTypes.get(Integer.parseInt(tokens[2]));
				
				partyList.add(new Adventurer(id, name, classType));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void loadPool() {
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(POOL_FILE);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
				) 
		{
			String line = "";
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0]);
				String name = tokens[1];
				String classType = classTypes.get(Integer.parseInt(tokens[2]));
				poolList.add(new Adventurer(id, name, classType));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private Item createItems(String data) {
//		if(data.equals("")) {
//			return null;
//		}
//		String[] part = data.split(":");
//		return new Item(part[0], ItemType.valueOf(part[1]));
//	}
	
	@Override
	public List<Adventurer> getPoolList() {
		return poolList;
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
	
	@Override
	public void addTocharacterPool(Adventurer character) {
		this.poolList.add(character);
	}

	@Override
	public void moveCharacterToParty(int index) {
		Adventurer moved = this.getCharacterFromPool(index);
		this.partyList.add(moved);
	}

	@Override
	public void moveCharacterToPool(int index) {
		Adventurer moved = this.getCharacterFromParty(index);
		this.poolList.add(moved);
	}

	@Override
	public Adventurer getCharacterFromParty(int index) {
		for (Adventurer member : partyList) {
			if(member.getId() == index) {
				return member;
			}
		}
		return null;
	}

	@Override
	public Adventurer getCharacterFromPool(int index) {
		for (Adventurer member : poolList) {
			if(member.getId() == index) {
				return member;
			}
		}
		return null;
	}

	@Override
	public void deletCharacterFromPool(int index) {
		this.poolList.remove(index);
	}

	@Override
	public List<String> getClassTypes() {
		return new ArrayList<>(this.classTypes);
	}

	@Override
	public void updatePool(int index, Adventurer character) {
		for(int i = 0; i < this.poolList.size(); i++) {
			if(this.poolList.get(i).getId() == index) {
				this.poolList.set(i, character);
			}
		}
	}

	@Override
	public void updateParty(int index, Adventurer character) {
		for(int i = 0; i < this.partyList.size(); i++) {
			if(this.partyList.get(i).getId() == index) {
				this.partyList.set(i, character);
			}
		}
	}
}
