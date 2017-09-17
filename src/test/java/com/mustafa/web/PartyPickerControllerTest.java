package com.mustafa.web;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
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
@ContextConfiguration(locations = { "StateControllerTests-context.xml" })  
@WebAppConfiguration  
public class PartyPickerControllerTest {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext wc; 

	@Autowired
	PartyPickerController ppc;
	
	
	MockPartyDAO partyDAO;

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
			MvcResult result = mockMvc.perform(post("/party.do").param("partyMember", "0"))
					.andExpect(status().isOk()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("redirect:home.do", mv.getViewName());
			assertEquals(3 , partyDAO.getPartyList().size());
			assertEquals(5 , partyDAO.getPoolList().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
