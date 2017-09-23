package com.mustafa.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mustafa.model.Item;

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
			String sql = "SELECT title FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				title = rs.getString(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return title;
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
	
}
