package com.mustafa.data;

import java.util.List;

import com.mustafa.model.Item;
import com.mustafa.model.ItemType;

public interface ItemDAO {
	public List<Item> getItemByType(int type);
	public Item getItemById(int index);
	public List<Item> getItemList();
	public List<ItemType> getItemsTypes();
	public void setItemList(List<Item> itemList);
	public Item addNewItem(Item item);
}
