package com.mustafa.data;

import java.util.List;

import com.mustafa.model.Item;

public interface ItemDAO {
	public List<Item> getItemByType(int type);
	public Item getItemById(int index);
	public List<Item> getItemList();
	public List<String> getItemsTypes();
	public void setItemList(List<Item> itemList);
	public Item addNewItem(Item item);
}
