package com.mustafa.data;

import java.util.List;

import com.mustafa.model.Item;

public interface ItemDAO {
	public List<Item> getItemByType(ItemType type);
	public List<Item> getItemList();
	public void setItemList(List<Item> itemList);
}
