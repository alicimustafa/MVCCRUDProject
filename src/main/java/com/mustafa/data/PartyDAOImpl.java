package com.mustafa.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mustafa.model.Adventurer;
import com.mustafa.model.Item;

@Component
public class PartyDAOImpl implements PartyDAO {
	
	private static final String FILE = "/WEB-INF/CSVfiles/ItemList.csv";
	private static final String POOL_FILE = "/WEB-INF/CSVfiles/PoolList.csv";
	private static final String PARTY_FILE = "/WEB-INF/CSVfiles/PartyList.csv";
	
	@Autowired
	private WebApplicationContext wac;
	
	private List<Adventurer> poolList;
	private List<Adventurer> partyList;
	
	public PartyDAOImpl() {
		poolList = new ArrayList<>();
		partyList = new ArrayList<>();
		init();
	}
	
	private void init() {
//		this.loadParty();
//		this.loadPool();
		this.partyList.add(new Adventurer("Gunther", ClassType.FIGHTHER));
		this.partyList.add(new Adventurer("Dormus", ClassType.ARCHER));
		this.partyList.add(new Adventurer("Loral", ClassType.CLERIC));
		this.partyList.add(new Adventurer("Lycia", ClassType.MAGE));
		
		this.poolList.add(new Adventurer("Talafane", ClassType.ROGUE));
		this.poolList.add(new Adventurer("Fhaga", ClassType.MAGE));
		this.poolList.add(new Adventurer("Valcon", ClassType.FIGHTHER));
		this.poolList.add(new Adventurer("Thormund", ClassType.CLERIC));
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
				String name = tokens[0];
				ClassType classType = ClassType.valueOf(tokens[1]);
				Item mainHand = this.createItems(tokens[2]);
				Item offHand = this.createItems(tokens[3]);
				Item armor = this.createItems(tokens[4]);
				partyList.add(new Adventurer(name, classType, mainHand, offHand, armor));
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
				String name = tokens[0];
				ClassType classType = ClassType.valueOf(tokens[1]);
				Item mainHand = this.createItems(tokens[2]);
				Item offHand = this.createItems(tokens[3]);
				Item armor = this.createItems(tokens[4]);
				poolList.add(new Adventurer(name, classType, mainHand, offHand, armor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Item createItems(String data) {
		if(data.equals("")) {
			return null;
		}
		String[] part = data.split(":");
		return new Item(part[0], ItemType.valueOf(part[1]));
	}
	
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
	public void save() {

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

}
