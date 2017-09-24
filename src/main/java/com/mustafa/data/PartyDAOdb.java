package com.mustafa.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mustafa.model.Adventurer;
import com.mysql.jdbc.Statement;

@Component
public class PartyDAOdb implements PartyDAO {
	
	private static String url = "jdbc:mysql://localhost:3306/partydb";
	private String user = "gamemaster";
	private String pass = "gameOn66";
	private Map<String, Integer> classId;

	public PartyDAOdb() {
		classId = new HashMap<>();
		classId.put("MAGE", 1);
		classId.put("ARCHER", 2);
		classId.put("FIGHTHER", 3);
		classId.put("ROGUE", 4);
		classId.put("CLERIC", 5);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}
	
	@Override
	public  Adventurer addTocharacterPool(Adventurer character) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO adventurer (name,main_hand,off_hand,armor) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, character.getName());
			stmt.setInt(2, classId.get(character.getCharacterClass()));
			stmt.setInt(2, character.getMainHand().getId());
			stmt.setInt(3, character.getOffHand().getId());
			stmt.setInt(4, character.getArmor().getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newAdvenId = keys.getInt(1);
					character.setId(newAdvenId);
					keys.close();
					stmt.close();
					sql = "INSERT INTO adventurer_group (adventurer_id,group_id) VALUES(?,?)";
					stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					stmt.setInt(1, newAdvenId);
					stmt.setInt(2 , 2);
					updateCount = stmt.executeUpdate();
					if(updateCount == 1) {
						conn.commit();
					} else {
						try {
							conn.rollback();
						} catch (SQLException sqle2) {
							System.err.println("Error trying to rollback");
						}
					}
				} else {
					character = null;
					try {
						conn.rollback();
					} catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
			} else {
				character = null;
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqle2) {
				System.err.println("Error trying to rollback");
			}
		}
		return character;
	}

	@Override
	public void moveCharacterToParty(int index) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			String sql = "UPDATE  film set title=?, description=?, release_year=?,  "
					+ " rental_duration=?, rental_rate=?, length=?, replacement_cost=? "
					+ " WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getYear());
			stmt.setInt(4, film.getRental_duration());
			stmt.setDouble(5, film.getRentalRate());
			stmt.setInt(6, film.getLength());
			stmt.setDouble(7, film.getReplacementCost());
			stmt.setInt(8, film.getId());
			int updateCount = stmt.executeUpdate();
			System.out.println("update: " + updateCount);
			if (updateCount != 1) {
				film = null;
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(film);
		return film;

	}

	@Override
	public void moveCharacterToPool(int index) {
		
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
