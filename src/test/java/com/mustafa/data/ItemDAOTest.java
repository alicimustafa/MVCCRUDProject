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
	Item[] ia = { new Item("sword", ItemType.MAIN_HAND),
				new Item("axe", ItemType.MAIN_HAND),
				new Item("spell book", ItemType.OFF_HAND),
				new Item("shield", ItemType.OFF_HAND),
				new Item("plate", ItemType.ARMOR),
				new Item("gambison", ItemType.ARMOR),
				new Item("rope", ItemType.OTHER),
				new Item("rations", ItemType.OTHER),
				new Item("water bottle", ItemType.OTHER)};

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
		List<Item> main = dao.getItemByType(ItemType.MAIN_HAND);
		assertEquals(2 , main.size());
		assertTrue(main.get(0).equals(new Item("sword", ItemType.MAIN_HAND)));
		assertTrue(main.get(1).equals(new Item("axe", ItemType.MAIN_HAND)));
	}
	@Test
	public void test_getItemByType_return_off_hand() {
		List<Item> off = dao.getItemByType(ItemType.OFF_HAND);
		assertEquals(2 , off.size());
		assertTrue(off.get(0).equals(new Item("spell book", ItemType.OFF_HAND)));
		assertTrue(off.get(1).equals(new Item("shield", ItemType.OFF_HAND)));
	}
	@Test
	public void test_getItemByType_return_armor() {
		List<Item> main = dao.getItemByType(ItemType.ARMOR);
		assertEquals(2 , main.size());
		assertTrue(main.get(0).equals(new Item("plate", ItemType.ARMOR)));
		assertTrue(main.get(1).equals(new Item("gambison", ItemType.ARMOR)));
	}
	@Test
	public void test_getItemByType_return_other() {
		List<Item> main = dao.getItemByType(ItemType.OTHER);
		assertEquals(3 , main.size());
		assertTrue(main.get(0).equals(new Item("rope", ItemType.OTHER)));
		assertTrue(main.get(1).equals(new Item("rations", ItemType.OTHER)));
		assertTrue(main.get(2).equals(new Item("water bottle", ItemType.OTHER)));
	}
	
}
