package com.mustafa.web;

import java.util.ArrayList;
import java.util.List;

import com.mustafa.data.ItemDAO;
import com.mustafa.data.ItemType;
import com.mustafa.data.Items;


public class MockItemDAO implements ItemDAO{
	
	private List<Items> itemList;
	
	public MockItemDAO() {
		this.init();
	}
	
	public void init() {
		this.itemList = new ArrayList<>();
		this.itemList.add(new Items("sword", ItemType.MAIN_HAND));
		this.itemList.add(new Items("axe", ItemType.MAIN_HAND));
		this.itemList.add(new Items("spell book", ItemType.OFF_HAND));
		this.itemList.add(new Items("shield", ItemType.OFF_HAND));
		this.itemList.add(new Items("plate", ItemType.ARMOR));
		this.itemList.add(new Items("gambison", ItemType.ARMOR));
		this.itemList.add(new Items("rope", ItemType.OTHER));
		this.itemList.add(new Items("rations", ItemType.OTHER));
		this.itemList.add(new Items("water bottle", ItemType.OTHER));

	}

	@Override
	public List<Items> getItemByType(ItemType type) {
		List<Items> newList = new ArrayList<>();
		for (Items item : itemList) {
			if (item.getType() == type) {
				newList.add(item);
			}
		}
		return newList;
	}

	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}
	

}
