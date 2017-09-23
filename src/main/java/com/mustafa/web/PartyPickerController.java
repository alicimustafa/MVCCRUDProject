package com.mustafa.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mustafa.data.ClassType;
import com.mustafa.data.ItemDAO;
import com.mustafa.data.ItemType;
import com.mustafa.data.PartyDAO;
import com.mustafa.model.Adventurer;
import com.mustafa.model.Input;
import com.mustafa.model.Item;

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
	public String displayCharacterPageCreate(Model model) {
		model.addAttribute("id", "0");
		model.addAttribute("table", "pool");
		model.addAttribute("mainHand", itemDAO.getItemByType(ItemType.MAIN_HAND));
		model.addAttribute("offHand", itemDAO.getItemByType(ItemType.OFF_HAND));
		model.addAttribute("armor", itemDAO.getItemByType(ItemType.ARMOR));
		model.addAttribute("classType", Arrays.asList(ClassType.values()));
		model.addAttribute("name", "");
		model.addAttribute("submitType", "Create");
		return "character";
	}
	
	@RequestMapping(path= "updateCharacter.do", method = RequestMethod.GET)
	public String displayCharacterForUpdate(Model model, 	@RequestParam("id") int id,	@RequestParam("table") String table) {
		model.addAttribute("id", id);
		model.addAttribute("table", table);
		model.addAttribute("mainHand", itemDAO.getItemByType(ItemType.MAIN_HAND));
		model.addAttribute("offHand", itemDAO.getItemByType(ItemType.OFF_HAND));
		model.addAttribute("armor", itemDAO.getItemByType(ItemType.ARMOR));
		model.addAttribute("classType", Arrays.asList(ClassType.values()));
		model.addAttribute("submitType", "Update");
		Adventurer adven;
		if(table.equals("party")) {
			adven = partyDAO.getCharacterFromParty(id);
		} else {
			adven = partyDAO.getCharacterFromPool(id);
		}
		model.addAttribute("name", adven.getName());
		return "character";
	}
	
	@RequestMapping(path = "character.do", 
			method = RequestMethod.POST,
			params = "Create")
	public String createCharacter(Input input) {
		String name = input.getName();
		ClassType classType = ClassType.valueOf(input.getClassType());
		Item mainHand = new Item(input.getMainHand(), ItemType.MAIN_HAND);
		Item offHand = new Item(input.getOffHand(), ItemType.OFF_HAND);
		Item armor = new Item(input.getArmor(), ItemType.ARMOR);
		Adventurer ad = new Adventurer(name, classType, mainHand, offHand, armor);
		this.partyDAO.addTocharacterPool(ad);
		return "redirect:home.do";
	}
	
	@RequestMapping(path = "character.do", 
			method = RequestMethod.POST,
			params = "Update")
	public String updateCharacter(Input input) {
		String name = input.getName();
		ClassType classType = ClassType.valueOf(input.getClassType());
		Item mainHand = new Item(input.getMainHand(), ItemType.MAIN_HAND);
		Item offHand = new Item(input.getOffHand(), ItemType.OFF_HAND);
		Item armor = new Item(input.getArmor(), ItemType.ARMOR);
		Adventurer ad = new Adventurer(name, classType, mainHand, offHand, armor);
		if(input.getTable().equals("party")) {
			this.partyDAO.getPartyList().set(input.getId(), ad);
		} else {
			this.partyDAO.getPoolList().set(input.getId(), ad);
		}
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
