package com.mustafa.web;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "PartyPickerControllerTest-context.xml" })  
@WebAppConfiguration  
public class PartyPickerControllerTest {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext wc; 

	MockPartyDAO partyDAO;
	//MockItemDAO itemDAO;
	
	@Autowired
	PartyPickerController ppc;
	

	@Before
	public void setUp() {
		partyDAO = wc.getBean(MockPartyDAO.class); 
		ppc.setPartyDOA(partyDAO);
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build(); 
	}


	@After
	public void tearDown() throws Exception {
		partyDAO.init();
	}

	@Test
	public void test_displayeHome_to_see_if_displays_home() {
		try {
			MvcResult result = mockMvc.perform(get("/home.do"))
					.andExpect(status().isOk()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("home", mv.getViewName());
			ModelMap map = mv.getModelMap();
			assertNotNull(map.get("party"));
			assertNotNull(map.get("pool"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void test_moveToPool_moves_to_pool_and_redricts_to_home() {
		try {
			MvcResult result = mockMvc.perform(post("/party.do")
					.param("partyMember", "0")
					.param("remove", "remove"))
					.andExpect(status().is3xxRedirection()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("redirect:home.do", mv.getViewName());
			assertEquals(3 , partyDAO.getPartyList().size());
			assertEquals(5 , partyDAO.getPoolList().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void test_moveToParty_moves_to_party_and_rederect_to_home() {
		try {
			MvcResult result = mockMvc.perform(post("/pool.do")
					.param("poolMember", "0")
					.param("move", "move"))
					.andExpect(status().is3xxRedirection()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("redirect:home.do", mv.getViewName());
			assertEquals(5 , partyDAO.getPartyList().size());
			assertEquals(3 , partyDAO.getPoolList().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test 
	public void test_deleteFromPool_delests_from_pool_and_rederect_home() {
		try {
			MvcResult result = mockMvc.perform(post("/pool.do")
					.param("poolMember", "0")
					.param("delete", "delete"))
					.andExpect(status().is3xxRedirection()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("redirect:home.do", mv.getViewName());
			assertEquals(3 , partyDAO.getPoolList().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void test_displayCharacterPageCreate_displays_character_page_correctly() {
		try {
			MvcResult result = mockMvc.perform(get("/createCharacter.do"))
					.andExpect(status().isOk()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("character", mv.getViewName());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void test_displayCharacterForUpdate_displays_character_page_correctly() {
		try {
			MvcResult result = mockMvc.perform(get("/createCharacter.do")
					.param("id", "0")
					.param("table", "pool"))
					.andExpect(status().isOk()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("character", mv.getViewName());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void test_createCharacter_add_character_to_pool_and_redirects_home() {
		try {
			MvcResult result = mockMvc.perform(post("/character.do")
						.param("name", "billy")
						.param("classType", "MAGE")
						.param("mainHand", "sword")
						.param("offHand", "shield")
						.param("armor", "leather")
						.param("id", "0")
						.param("table", "pool")
						.param("Create", "create"))
					.andExpect(status().is3xxRedirection()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("redirect:home.do", mv.getViewName());
			assertEquals(5 , partyDAO.getPoolList().size());
		} catch (Exception e) {
			fail(e.toString());
			e.printStackTrace();
		}
	}

}
