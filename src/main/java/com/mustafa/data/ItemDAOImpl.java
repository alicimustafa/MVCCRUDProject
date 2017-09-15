package com.mustafa.data;

import java.util.List;

import org.springframework.web.context.support.ServletContextResource;

public class ItemDAOImpl implements ItemDAO {

	List<Items> itemList;
	ServletContextResource contex;
	private final String FILE = "ItemList";
	
	public ItemDAOImpl() {
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
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
