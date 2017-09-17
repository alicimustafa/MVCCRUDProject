package com.mustafa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mustafa.data.ItemDAO;
import com.mustafa.data.PartyDAO;

@Controller
public class PartyPickerController {
	
	@Autowired
	PartyDAO partyDAO;
	@Autowired
	ItemDAO itemDAO;
	
	@RequestMapping( path = "home.do" ,method = RequestMethod.GET)
	public String displayeHome( Model model) {
		model.addAttribute("party", partyDAO.getPartyList());
		model.addAttribute("pool", partyDAO.getPoolList());
		return "home";
	}
	
	@RequestMapping(path = "party.do", 
				method = RequestMethod.POST, 
				params = "remove")
	public String moveToPool(@RequestParam("partyMember") int id, Model model) {
		partyDAO.moveCharacterToPool(id);
		return "redirect:home.do";
	}
	
	@RequestMapping(path = "pool.do", 
			method = RequestMethod.POST, 
			params = "move")
	public String moveToParty(@RequestParam("poolMember") int id, Model model) {
		partyDAO.moveCharacterToParty(id);
		return "redirect:home.do";
	}
	@RequestMapping(path = "pool.do", 
			method = RequestMethod.POST, 
			params = "delete")
	public String deleteFromPool(@RequestParam("poolMember") int id, Model model) {
		partyDAO.deletCharacterFromPool(id);
		return "redirect:home.do";
	}
		
	@RequestMapping(path= "createCharacter.do", method = RequestMethod.GET)
	public String displayeCharacterPage() {
		
		return "character.jsp";
	}

	public PartyDAO getPartyDOA() {
		return partyDAO;
	}

	public void setPartyDOA(PartyDAO partyDOA) {
		this.partyDAO = partyDOA;
	}
	
	

}
