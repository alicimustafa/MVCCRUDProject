package com.mustafa.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mustafa.model.Adventurer;

public class PartyDAOTest {
	
	PartyDAOImpl partyDAO;

	@Before
	public void setUp() throws Exception {
		partyDAO = new PartyDAOImpl();
		partyDAO.getPartyList().clear();
		partyDAO.getPoolList().clear();
		partyDAO.getPartyList().add(new Adventurer(1,"a", "FIGHTHER"));
		partyDAO.getPartyList().add(new Adventurer(2,"b", "ARCHER"));
		partyDAO.getPartyList().add(new Adventurer(3,"c", "CLERIC"));
		partyDAO.getPartyList().add(new Adventurer(4,"d", "MAGE"));
		
		partyDAO.getPoolList().add(new Adventurer(10,"z", "ROGUE"));
		partyDAO.getPoolList().add(new Adventurer(11,"y", "MAGE"));
		partyDAO.getPoolList().add(new Adventurer(12,"x", "FIGHTHER"));
		partyDAO.getPoolList().add(new Adventurer(13,"w", "CLERIC"));
	}

	@After
	public void tearDown() throws Exception {
		partyDAO = null;
	}

	@Test
	public void test_addTocharacterPool_to_se_if_character_added() {
		Adventurer ch = new Adventurer(5,"bob", "ARCHER");
		int size = partyDAO.getPoolList().size();
		partyDAO.addTocharacterPool(ch);
		assertEquals(size + 1, partyDAO.getPoolList().size());
		assertEquals(ch , partyDAO.getPoolList().get(size));
	}
	
	@Test
	public void test_moveCharacterToPool_removes_and_adds_character_to_pool() {
		int poolsize = partyDAO.getPoolList().size();
		int partysize = partyDAO.getPartyList().size();
		Adventurer ch = partyDAO.getPartyList().get(0);
		partyDAO.moveCharacterToPool(0);
		assertEquals(partysize -1, partyDAO.getPartyList().size());
		assertEquals(poolsize +1, partyDAO.getPoolList().size());
		assertEquals(ch, partyDAO.getPoolList().get(poolsize));
	}
	
	@Test 
	public void test_moveCharacterToParty_removes_and_adds_character_to_party() {
		int poolsize = partyDAO.getPoolList().size();
		int partysize = partyDAO.getPartyList().size();
		Adventurer ch = partyDAO.getPoolList().get(0);
		partyDAO.moveCharacterToParty(0);
		assertEquals(partysize +1, partyDAO.getPartyList().size());
		assertEquals(poolsize -1, partyDAO.getPoolList().size());
		assertEquals(ch, partyDAO.getPartyList().get(partysize));

	}
	
	@Test
	public void test_getCharacterFromParty_returns_correct_character() {
		assertTrue(partyDAO.getCharacterFromParty(0).equals(new Adventurer(1,"a", "FIGHTHER")));
		assertTrue(partyDAO.getCharacterFromParty(3).equals(new Adventurer(4,"d", "MAGE")));
	}
	
	@Test
	public void test_getCharacterFromPool_returns_correct_character() {
		assertTrue(partyDAO.getCharacterFromPool(0).equals(new Adventurer(10,"z", "ROGUE")));
		assertTrue(partyDAO.getCharacterFromPool(3).equals(new Adventurer(13,"w", "CLERIC")));
	}
	
	@Test
	public void test_deletCharacterFromPool_delets_character_from_pool() {
		partyDAO.deletCharacterFromPool(0);
		assertEquals(3 , partyDAO.getPoolList().size());
		assertTrue(partyDAO.getPoolList().get(0).equals(new Adventurer(11,"y", "MAGE")));
	}

}
