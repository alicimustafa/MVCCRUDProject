package com.mustafa.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mustafa.model.Adventurer;
import com.mustafa.model.Item;

@Component
public class PartyDAOdb implements PartyDAO {
	
	private static String url = "jdbc:mysql://localhost:3306/partydb";
	private String user = "gamemaster";
	private String pass = "gameOn66";

	public PartyDAOdb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}
	
	@Override
	public void addTocharacterPool(Adventurer character) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveCharacterToParty(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveCharacterToPool(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletCharacterFromPool(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public Adventurer getCharacterFromParty(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adventurer getCharacterFromPool(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adventurer> getPoolList() {
		return this.getListByParty(2);
	}
	
	private List<Adventurer> getListByParty(int index){
		List<Adventurer> adventurers = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT \n" + 
					"	adventurer.id,\n" + 
					"	adventurer.name,\n" + 
					"    class_type.name ct\n" + 
					"    FROM adventurer\n" + 
					"    JOIN class_type ON class_type.id = adventurer.class_type\n" + 
					"    JOIN adventurer_group ON adventurer_id = adventurer.id\n" + 
					"    WHERE adventurer_group.group_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, index);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Adventurer adven = new Adventurer();
				adven.setId(rs.getInt(1));
				adven.setName(rs.getString(2));
				adven.setCharacterClass(rs.getString(3));
				adventurers.add(adven);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adventurers;
	}
	@Override
	public void setPoolList(List<Adventurer> poolList) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Adventurer> getPartyList() {
		return this.getListByParty(1);
	}

	@Override
	public void setPartyList(List<Adventurer> partyList) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getClassTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePool(int index, Adventurer character) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateParty(int index, Adventurer character) {
		// TODO Auto-generated method stub

	}

}
