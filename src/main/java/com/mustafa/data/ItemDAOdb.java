package com.mustafa.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;

import org.springframework.stereotype.Component;

import com.mustafa.model.Item;
import com.mustafa.model.ItemType;

@Component
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
	public List<Item> getItemByType(int type) {
		List<Item> items = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, name FROM items WHERE type = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, type);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				items.add(new Item(id, name, type));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public Item getItemById(int index) {
		Item item = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT name, type FROM items WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, index);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				item = new Item(index, rs.getString(1), rs.getInt(2));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public List<Item> getItemList() {
		List<Item> items = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, name, type FROM items";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int type = rs.getInt(3);
				items.add(new Item(id, name, type));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public List<ItemType> getItemsTypes() {
		List<ItemType> types = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, name FROM item_type";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				types.add(new ItemType(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return types;	
	}

	@Override
	public void setItemList(List<Item> itemList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item addNewItem(Item item) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO items (name,type) VALUES ( ? , ? )";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, item.getName());
			stmt.setInt(2, item.getType());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					item.setId(newFilmId);
				} else {
					item = null;
				}
			} else {
				item = null;
			}
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return item;
	}
}
