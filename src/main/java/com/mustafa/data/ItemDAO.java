package com.mustafa.data;

import java.util.List;

public interface ItemDAO {
	public List<Items> getItemByType(ItemType type);
	public List<Items> getItemList();
	public void setItemList(List<Items> itemList);
}
