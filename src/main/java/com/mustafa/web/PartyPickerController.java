package com.mustafa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mustafa.data.ItemDAO;
import com.mustafa.data.PartyDAO;
import com.mustafa.model.Adventurer;
import com.mustafa.model.Input;
import com.mustafa.model.Item;
import com.mustafa.model.ItemType;

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
		Adventurer adventurer = new Adventurer();
		Item item = new Item();
		adventurer.setMainHand(item);
		adventurer.setOffHand(item);
		adventurer.setArmor(item);
		model.addAttribute("adventurer", adventurer);
		model.addAttribute("table", "pool");
		model.addAttribute("name", "");
		model.addAttribute("mainHand", itemDAO.getItemByType(1));
		model.addAttribute("offHand", itemDAO.getItemByType(2));
		model.addAttribute("armor", itemDAO.getItemByType(3));
		model.addAttribute("classType", partyDAO.getClassTypes());
		model.addAttribute("submitType", "Create");
		return "character";
	}
	
	@RequestMapping(path= "updateCharacter.do", method = RequestMethod.GET)
	public String displayCharacterForUpdate(Model model, 	@RequestParam("id") int id,	@RequestParam("table") String table) {
		Adventurer adventurer;
		if(table.equals("party")) {
			adventurer = partyDAO.getCharacterFromParty(id);
		} else {
			adventurer = partyDAO.getCharacterFromPool(id);
		}
		List<Item> backpack = partyDAO.getBackpack(id);
		model.addAttribute("backpack", backpack);
		model.addAttribute("adventurer", adventurer);
		model.addAttribute("id", id);
		model.addAttribute("table", table);
		model.addAttribute("mainHand", itemDAO.getItemByType(1));
		model.addAttribute("offHand", itemDAO.getItemByType(2));
		model.addAttribute("armor", itemDAO.getItemByType(3));
		model.addAttribute("classType", partyDAO.getClassTypes());
		model.addAttribute("submitType", "Update");
		return "character";
	}
	
	@RequestMapping(path = "character.do", 
			method = RequestMethod.POST,
			params = "Create")
	public String createCharacter(Input input) {
		String name = input.getName();
		String classType = input.getClassType();
		Item mainHand = itemDAO.getItemById(input.getMainHand());
		Item offHand = itemDAO.getItemById(input.getOffHand());
		Item armor = itemDAO.getItemById(input.getArmor());
		Adventurer ad = new Adventurer(0 , name, classType, mainHand, offHand, armor);
		this.partyDAO.addTocharacterPool(ad);
		return "redirect:home.do";
	}
	
	@RequestMapping(path = "character.do", 
			method = RequestMethod.POST,
			params = "Update")
	public String updateCharacter(Input input) {
		int id = input.getId();
		String name = input.getName();
		String classType = input.getClassType();
		Item mainHand = itemDAO.getItemById(input.getMainHand());
		Item offHand = itemDAO.getItemById(input.getOffHand());
		Item armor = itemDAO.getItemById(input.getArmor());
		Adventurer ad = new Adventurer(id , name, classType, mainHand, offHand, armor);
		if(input.getTable().equals("party")) {
			this.partyDAO.updateParty(ad);
		} else {
			this.partyDAO.updatePool(ad);
		}
		return "redirect:home.do";
	}
	
	@RequestMapping(path = "editBackpack.do", method = RequestMethod.GET)
	public String displayItemEdit(Model model, @RequestParam("id") int id) {
		List<Item> itemList = itemDAO.getItemList();
		List<Item> backpack = partyDAO.getBackpack(id);
		List<ItemType> itemTypes = itemDAO.getItemsTypes();
		String name = partyDAO.getCharacterFromParty(id).getName();
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("itemList", itemList);
		model.addAttribute("backpack", backpack);
		model.addAttribute("itemTypes", itemTypes);
		return "item";
	}
	
	@RequestMapping(path = "addItem.do", method = RequestMethod.POST)
	public String addItemToBackpack(@RequestParam("itemId") int itemId, @RequestParam("advenId") int advenId) {
		partyDAO.addItemToBackpack(advenId, itemId);
		return "redirect:editBackpack.do?id=" + advenId;
	}
	@RequestMapping(path = "deleteItem.do", method = RequestMethod.POST)
	public String deleteItemToBackpack(@RequestParam("itemId") int itemId, @RequestParam("advenId") int advenId) {
		partyDAO.deleItemFromBackpack(advenId, itemId);
		return "redirect:editBackpack.do?id=" + advenId;
	}
	
	@RequestMapping(path = "createItem.do", method = RequestMethod.GET)
	public String createItem(@RequestParam("name") String name, 
							@RequestParam("type") int type,
							@RequestParam("advenId") int advenId) {
		itemDAO.addNewItem(new Item(0,name,type));
		return "redirect:editBackpack.do?id=" + advenId;
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
