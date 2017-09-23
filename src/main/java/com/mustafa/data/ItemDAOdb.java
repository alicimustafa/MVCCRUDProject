package com.mustafa.data;

import java.util.List;

import com.mustafa.model.Item;

public class ItemDAOdb implements ItemDAO {
	
	private static String url = "jdbc:mysql://localhost:3306/partydb";
	private String user = "gamemaster";
	private String pass = "gameOn66";

	public ItemDAOdb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}
	
	@Override
	public List<Item> getItemByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getItemsTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemList(List<Item> itemList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewItem(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Item getItemById(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
