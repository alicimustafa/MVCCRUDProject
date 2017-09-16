package com.mustafa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mustafa.data.ItemDAOImpl;

@Controller
public class PartyPickerController {
	
	@Autowired
	ItemDAOImpl partyDOA;

}
