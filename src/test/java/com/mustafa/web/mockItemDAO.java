package com.mustafa.web;

import java.util.ArrayList;
import java.util.List;

import com.mustafa.data.ItemDAO;
import com.mustafa.data.ItemType;
import com.mustafa.data.Item;

public class MockItemDAO implements ItemDAO{
	
	private List<Item> itemList;
	
	public MockItemDAO() {
		this.init();
	}
	
	public void init() {
		this.itemList = new ArrayList<>();
		this.itemList.add(new Item("sword", ItemType.MAIN_HAND));
		this.itemList.add(new Item("axe", ItemType.MAIN_HAND));
		this.itemList.add(new Item("spell book", ItemType.OFF_HAND));
		this.itemList.add(new Item("shield", ItemType.OFF_HAND));
		this.itemList.add(new Item("plate", ItemType.ARMOR));
		this.itemList.add(new Item("gambison", ItemType.ARMOR));
		this.itemList.add(new Item("rope", ItemType.OTHER));
		this.itemList.add(new Item("rations", ItemType.OTHER));
		this.itemList.add(new Item("water bottle", ItemType.OTHER));

	}

	@Override
	public List<Item> getItemByType(ItemType type) {
		List<Item> newList = new ArrayList<>();
		for (Item item : itemList) {
			if (item.getType() == type) {
				newList.add(item);
			}
		}
		return newList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	

}
