package com.mustafa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mustafa.data.PartyDAOImpl;

@Controller
public class PartyPickerController {
	
	@Autowired
	PartyDAOImpl partyDOA;
	
	@RequestMapping( path = "home.do" ,method = RequestMethod.GET)
	public String displayeHome( Model model) {
		model.addAttribute("party", partyDOA.getPartyList());
		model.addAttribute("pool", partyDOA.getPoolList());
		return "home";
	}
	
	@RequestMapping(path = "party.do", 
				method = RequestMethod.POST, 
				params = "remove")
	public String moveToPool(@RequestParam("partyMember") int id, Model model) {
		partyDOA.moveCharacterToPool(id);
		return "redirect:home.do";
	}
	
	@RequestMapping(path = "pool.do", 
			method = RequestMethod.POST, 
			params = "move")
	public String moveToParty(@RequestParam("poolMember") int id, Model model) {
		partyDOA.moveCharacterToParty(id);
		return "redirect:home.do";
	}
	@RequestMapping(path = "pool.do", 
			method = RequestMethod.POST, 
			params = "delete")
	public String deleteFromPool(@RequestParam("poolMember") int id, Model model) {
		partyDOA.deletCharacterFromPool(id);
		return "redirect:home.do";
	}
		
	
	
	

}
