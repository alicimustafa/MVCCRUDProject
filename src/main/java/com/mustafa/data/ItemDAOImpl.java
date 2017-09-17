package com.mustafa.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.experimental.theories.Theories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class ItemDAOImpl implements ItemDAO {

	List<Items> itemList;
	private static final String FILE = "/WEB-INF/CSVfiles/ItemList.csv";
	@Autowired
	private WebApplicationContext wac;

	public ItemDAOImpl() {
		itemList = new ArrayList<>();
		this.init();
	}

	@PostConstruct
	private void init() {
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(FILE);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
				) 
		{
			String line = "";
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				String name = tokens[0];
				ItemType type = ItemType.valueOf(tokens[1]);
				itemList.add(new Items(name, type));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Items> getItemList() {
		return itemList;
	}
	@Override
	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
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
}
