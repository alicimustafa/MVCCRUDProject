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
		partyDAO.getPartyList().add(new Adventurer("a", ClassType.FIGHTHER));
		partyDAO.getPartyList().add(new Adventurer("b", ClassType.ARCHER));
		partyDAO.getPartyList().add(new Adventurer("c", ClassType.CLERIC));
		partyDAO.getPartyList().add(new Adventurer("d", ClassType.MAGE));
		
		partyDAO.getPoolList().add(new Adventurer("z", ClassType.ROGUE));
		partyDAO.getPoolList().add(new Adventurer("y", ClassType.MAGE));
		partyDAO.getPoolList().add(new Adventurer("x", ClassType.FIGHTHER));
		partyDAO.getPoolList().add(new Adventurer("w", ClassType.CLERIC));
	}

	@After
	public void tearDown() throws Exception {
		partyDAO = null;
	}

	@Test
	public void test_addTocharacterPool_to_se_if_character_added() {
		Adventurer ch = new Adventurer("bob", ClassType.ARCHER);
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
		assertTrue(partyDAO.getCharacterFromParty(0).equals(new Adventurer("a", ClassType.FIGHTHER)));
		assertTrue(partyDAO.getCharacterFromParty(3).equals(new Adventurer("d", ClassType.MAGE)));
	}
	
	@Test
	public void test_getCharacterFromPool_returns_correct_character() {
		assertTrue(partyDAO.getCharacterFromPool(0).equals(new Adventurer("z", ClassType.ROGUE)));
		assertTrue(partyDAO.getCharacterFromPool(3).equals(new Adventurer("w", ClassType.CLERIC)));
	}
	
	@Test
	public void test_deletCharacterFromPool_delets_character_from_pool() {
		partyDAO.deletCharacterFromPool(0);
		assertEquals(3 , partyDAO.getPoolList().size());
		assertTrue(partyDAO.getPoolList().get(0).equals(new Adventurer("y", ClassType.MAGE)));
	}

}
