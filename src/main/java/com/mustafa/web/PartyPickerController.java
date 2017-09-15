package com.mustafa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mustafa.data.PartyDAOImpl;

@Controller
public class PartyPickerController {
	
	@Autowired
	PartyDAOImpl partyDOA;

}
