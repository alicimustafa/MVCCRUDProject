package com.mustafa.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mustafa.data.Adventurer;
import com.mustafa.data.ClassType;
import com.mustafa.data.Input;
import com.mustafa.data.ItemDAO;
import com.mustafa.data.ItemType;
import com.mustafa.data.Items;
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
	public String displayeCharacterPage(Model model) {
		model.addAttribute("mainHand", itemDAO.getItemByType(ItemType.MAIN_HAND));
		model.addAttribute("offHand", itemDAO.getItemByType(ItemType.OFF_HAND));
		model.addAttribute("armor", itemDAO.getItemByType(ItemType.ARMOR));
		model.addAttribute("classType", Arrays.asList(ClassType.values()));
		model.addAttribute("submitType", "Create");
		return "character";
	}
	
	@RequestMapping(path = "character.do", 
			method = RequestMethod.POST,
			params = "Create")
	public String createCharactor(Input input) {
		//System.out.println(input);
		String name = input.getName();
		ClassType classType = ClassType.valueOf(input.getClassType());
		Items mainHand = new Items(input.getMainHand(), ItemType.MAIN_HAND);
		Items offHand = new Items(input.getOffHand(), ItemType.OFF_HAND);
		Items armor = new Items(input.getArmor(), ItemType.ARMOR);
		Adventurer ad = new Adventurer(name, classType, mainHand, offHand, armor);
		this.partyDAO.addTocharacterPool(ad);
		return "redirect:home.do";
	}

	public PartyDAO getPartyDOA() {
		return partyDAO;
	}

	public void setPartyDOA(PartyDAO partyDOA) {
		this.partyDAO = partyDOA;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	

}
