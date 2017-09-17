package com.mustafa.web;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "StateControllerTests-context.xml" })  
@WebAppConfiguration  
public class PartyPickerControllerTest {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext wc; 

	@Autowired
	PartyPickerController ppc;
	
	MockPartyDAO mockDAO;

	@Before
	public void setUp() {
		mockDAO = wc.getBean(MockPartyDAO.class); // this was created in stateControllerTest-context.xml
		
		// TODO - uncomment below to add a Mock object, which we control
		ppc.setPartyDOA(mockDAO);
		
		// TODO - build the MockMvc object with MockMvcBuilders
		mockMvc =MockMvcBuilders.webAppContextSetup(wc).build(); // final step build the thing to make request
																// using appliccation context that spring 
		                                                         // created for me
	}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
