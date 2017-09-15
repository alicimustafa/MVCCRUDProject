package com.mustafa.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletContextResource;

@Component
public class ItemDAOImpl implements ItemDAO {

	List<Items> itemList;
	ServletContextResource contex;
	//private final String FILE = "ItemList.txt";
	
	public ItemDAOImpl() {
		itemList = new ArrayList<>();
		this.init();
	}
	
	private void init() {
		itemList.add(new Items("Sword", ItemType.MAIN_HAND));
		itemList.add(new Items("Axe", ItemType.MAIN_HAND));
		itemList.add(new Items("Spear", ItemType.MAIN_HAND));
		itemList.add(new Items("Mace", ItemType.MAIN_HAND));
		itemList.add(new Items("Bow", ItemType.MAIN_HAND));
		itemList.add(new Items("Staf", ItemType.MAIN_HAND));
		itemList.add(new Items("Dagger", ItemType.MAIN_HAND));
		itemList.add(new Items("Shield", ItemType.OFF_HAND));		
		itemList.add(new Items("Spell Book", ItemType.OFF_HAND));		
		itemList.add(new Items("Holy symbol", ItemType.OFF_HAND));		
		itemList.add(new Items("Gambeson", ItemType.ARMOR));		
		itemList.add(new Items("Leather", ItemType.ARMOR));		
		itemList.add(new Items("Chain Mail", ItemType.ARMOR));		
		itemList.add(new Items("Plate Mail", ItemType.ARMOR));		
		itemList.add(new Items("Rope", ItemType.OTHER));		
		itemList.add(new Items("Flint and Steel", ItemType.OTHER));		
		itemList.add(new Items("Bedding", ItemType.OTHER));		
		itemList.add(new Items("Dry Rations", ItemType.OTHER));		
		itemList.add(new Items("Water Skin", ItemType.OTHER));		
	}
	
	public ItemDAOImpl(ServletContextResource contex) {
		super();
		this.contex = contex;
	}

	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}

	@Override
	public List<Items> getItemByType(ItemType type) {
		List<Items> newList = new  ArrayList<>();
		for (Items item : itemList) {
			if(item.getType() == type) {
				newList.add(item);
			}
		}
		return newList;
	}
	
	

}
