package com.mustafa.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mustafa.model.Item;

public class ItemDAOTest {
	
	ItemDAOImpl dao;
	Item[] ia = { new Item(1,"sword", "MAIN_HAND"),
				new Item(2,"axe", "MAIN_HAND"),
				new Item(3,"spell book", "OFF_HAND"),
				new Item(4,"shield", "OFF_HAND"),
				new Item(5,"plate", "ARMOR"),
				new Item(6,"gambison", "ARMOR"),
				new Item(7,"rope", "OTHER"),
				new Item(8,"rations", "OTHER"),
				new Item(9,"water bottle", "OTHER")};

	@Before
	public void setUp() throws Exception {
		List<Item> items = new ArrayList<>();
		for (Item item : ia) {
			items.add(item);
		}
		dao = new ItemDAOImpl();
		dao.setItemList(items);
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void test_getItemByType_return_main_hand() {
		List<Item> main = dao.getItemByType("MAIN_HAND");
		assertEquals(2 , main.size());
		assertTrue(main.get(0).equals(new Item(1,"sword", "MAIN_HAND")));
		assertTrue(main.get(1).equals(new Item(2,"axe", "MAIN_HAND")));
	}
	@Test
	public void test_getItemByType_return_off_hand() {
		List<Item> off = dao.getItemByType("OFF_HAND");
		assertEquals(2 , off.size());
		assertTrue(off.get(0).equals(new Item(3,"spell book", "OFF_HAND")));
		assertTrue(off.get(1).equals(new Item(4,"shield", "OFF_HAND")));
	}
	@Test
	public void test_getItemByType_return_armor() {
		List<Item> main = dao.getItemByType("ARMOR");
		assertEquals(2 , main.size());
		assertTrue(main.get(0).equals(new Item(5,"plate", "ARMOR")));
		assertTrue(main.get(1).equals(new Item(6,"gambison", "ARMOR")));
	}
	@Test
	public void test_getItemByType_return_other() {
		List<Item> main = dao.getItemByType("OTHER");
		assertEquals(3 , main.size());
		assertTrue(main.get(0).equals(new Item(7,"rope", "OTHER")));
		assertTrue(main.get(1).equals(new Item(8,"rations", "OTHER")));
		assertTrue(main.get(2).equals(new Item(9,"water bottle", "OTHER")));
	}
	
}
