package com.mustafa.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.mustafa.model.Item;
import com.mustafa.model.ItemType;


public class ItemDAOImpl implements ItemDAO {

	private List<Item> itemList;
	private List<ItemType> itemTypes;
	private static final String FILE = "/WEB-INF/CSVfiles/ItemList.csv";
	@Autowired
	private WebApplicationContext wac;

	public ItemDAOImpl() {
		itemList = new ArrayList<>();
		itemTypes = new ArrayList<>();
		this.init();
	}

	@PostConstruct
	public void init() {
		this.itemTypes.add(new ItemType(1,"Main Hand"));
		this.itemTypes.add(new ItemType(2,"Off Hand"));
		this.itemTypes.add(new ItemType(3,"Armor"));
		this.itemTypes.add(new ItemType(4,"Other"));
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(FILE);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
				) 
		{
			String line = "";
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0]);
				String name = tokens[1];
				int type = Integer.parseInt(tokens[2]);
				itemList.add(new Item(id, name, type));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Item> getItemList() {
		return itemList;
	}
	@Override
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public List<Item> getItemByType(int type) {
		List<Item> newList = new ArrayList<>();
		for (Item item : itemList) {
			if (item.getType() == type) {
				newList.add(item);
			}
		}
		return newList;
	}

	@Override
	public Item addNewItem(Item item) {
		itemList.add(item);
		return item;
	}

	@Override
	public List<ItemType> getItemsTypes() {
		return new ArrayList<>(this.itemTypes);
	}

	@Override
	public Item getItemById(int index) {
		for (Item item : itemList) {
			if(item.getId() == index) {
				return item;
			}
		}
		return null;
	}
}
