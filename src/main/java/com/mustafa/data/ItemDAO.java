package com.mustafa.data;

import java.util.List;

import com.mustafa.model.Item;

public interface ItemDAO {
	public List<Item> getItemByType(String type);
	public List<Item> getItemList();
	public List<String> getItemsTypes();
	public void setItemList(List<Item> itemList);
	public void addNewItem(Item item);
}
