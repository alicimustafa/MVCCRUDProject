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
//		try (
//				InputStream is = wac.getServletContext().getResourceAsStream(FILE);
//				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
//				) 
//		{
//			String line = buf.readLine();
//			while ((line = buf.readLine()) != null) {
//				String[] tokens = line.split(",");
//				String name = tokens[0];
//				ItemType type = ItemType.valueOf(tokens[1]);
//				itemList.add(new Items(name, type));
//				System.out.println(line);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();;
//		}
		this.getItemList().add(new Items("sword", ItemType.MAIN_HAND));
		this.getItemList().add(new Items("axe", ItemType.MAIN_HAND));
		this.getItemList().add(new Items("spell book", ItemType.OFF_HAND));
		this.getItemList().add(new Items("shield", ItemType.OFF_HAND));
		this.getItemList().add(new Items("plate", ItemType.ARMOR));
		this.getItemList().add(new Items("gambison", ItemType.ARMOR));
		this.getItemList().add(new Items("rope", ItemType.OTHER));
		this.getItemList().add(new Items("rations", ItemType.OTHER));
		this.getItemList().add(new Items("water bottle", ItemType.OTHER));
	}

	public List<Items> getItemList() {
		return itemList;
	}

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
