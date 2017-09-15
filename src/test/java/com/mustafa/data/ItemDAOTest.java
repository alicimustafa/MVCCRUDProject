package com.mustafa.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemDAOTest {
	
	ItemDAOImpl dao;
	List<Items> items;
	Items[] ia = { new Items("sword", ItemType.MAIN_HAND),
				new Items("axe", ItemType.MAIN_HAND),
				new Items("spell book", ItemType.OFF_HAND),
				new Items("shield", ItemType.OFF_HAND),
				new Items("plate", ItemType.ARMOR),
				new Items("gambison", ItemType.ARMOR),
				new Items("rope", ItemType.OTHER),
				new Items("rations", ItemType.OTHER),
				new Items("water bottle", ItemType.OTHER)};

	@Before
	public void setUp() throws Exception {
		items = new ArrayList<>();
		for (Items item : ia) {
			items.add(item);
		}
		dao = new ItemDAOImpl();
		dao.setItemList(items);
	}

	@After
	public void tearDown() throws Exception {
		items = null;
		dao = null;
	}

	@Test
	public void test_getItemByType_return_main_hand() {
		List<Items> main = dao.getItemByType(ItemType.MAIN_HAND);
		assertEquals(2 , main.size());
		assertTrue(main.get(0).equals(new Items("sword", ItemType.MAIN_HAND)));
		assertTrue(main.get(1).equals(new Items("axe", ItemType.MAIN_HAND)));
	}
	@Test
	public void test_getItemByType_return_off_hand() {
		List<Items> off = dao.getItemByType(ItemType.MAIN_HAND);
		assertEquals(2 , off.size());
		assertTrue(off.get(0).equals(new Items("spell book", ItemType.OFF_HAND)));
		assertTrue(off.get(1).equals(new Items("shield", ItemType.OFF_HAND)));
	}
	@Test
	public void test_getItemByType_return_armor() {
		List<Items> main = dao.getItemByType(ItemType.ARMOR);
		assertEquals(2 , main.size());
		assertTrue(main.get(0).equals(new Items("plate", ItemType.ARMOR)));
		assertTrue(main.get(1).equals(new Items("gambison", ItemType.ARMOR)));
	}
	@Test
	public void test_getItemByType_return_other() {
		List<Items> main = dao.getItemByType(ItemType.OTHER);
		assertEquals(2 , main.size());
		assertTrue(main.get(0).equals(new Items("rope", ItemType.OTHER)));
		assertTrue(main.get(1).equals(new Items("rations", ItemType.OTHER)));
		assertTrue(main.get(2).equals(new Items("water bottle", ItemType.OTHER)));
	}
	
}
