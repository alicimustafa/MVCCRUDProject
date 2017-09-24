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
import com.mustafa.model.Item;
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
			String sql = "INSERT INTO adventurer (name,class_type,main_hand,off_hand,armor) VALUES (?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, character.getName());
			stmt.setInt(2, classId.get(character.getCharacterClass()));
			stmt.setInt(3, character.getMainHand().getId());
			stmt.setInt(4, character.getOffHand().getId());
			stmt.setInt(5, character.getArmor().getId());
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
					if(updateCount != 1) {
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
			conn.commit();
			stmt.close();
			conn.close();
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
			
			String sql = "UPDATE adventurer_group SET group_id = ? WHERE adventurer_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setInt(2, index);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moveCharacterToPool(int index) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			String sql = "UPDATE adventurer_group SET group_id = ? WHERE adventurer_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 2);
			stmt.setInt(2, index);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletCharacterFromPool(int index) {
		Connection conn = null;
		boolean b = false;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql1 = "DELETE FROM adventurer_group WHERE adventurer_id= ?";
			String sql2 = "DELETE FROM adventurer WHERE id= ?";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, index);
			stmt.executeUpdate();
			stmt = conn.prepareStatement(sql2);
			stmt.setInt(1, index);
			stmt.executeUpdate();
			conn.commit(); 
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException sqle2) {
				System.err.println("Error trying to rollback");
			}
		}
	}

	@Override
	public Adventurer getCharacterFromParty(int index) {
		return getCharacter(index);
	}

	@Override
	public Adventurer getCharacterFromPool(int index) {
		return getCharacter(index);
	}
	
	private Adventurer getCharacter(int index) {
		Adventurer adven = new Adventurer();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT \n" + 
					"	adventurer.id,\n" + 
					"	adventurer.name,\n" + 
					"    class_type.name ct,\n" + 
					"    mh.id,\n" + 
					"    mh.name,\n" + 
					"    mh.type,\n" + 
					"    oh.id,\n" + 
					"    oh.name,\n" + 
					"    oh.type,\n" + 
					"    ar.id,\n" + 
					"    ar.name,\n" + 
					"    ar.type\n" + 
					"    FROM adventurer\n" + 
					"    JOIN class_type ON class_type.id = adventurer.class_type\n" + 
					"    JOIN items AS mh ON adventurer.main_hand = mh.id\n" + 
					"    JOIN items AS oh ON adventurer.off_hand = oh.id\n" + 
					"    JOIN items AS ar ON adventurer.armor = ar.id\n" + 
					"    WHERE adventurer.id = ? \n" + 
					"    \n" + 
					"";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, index);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				adven.setId(rs.getInt(1));
				adven.setName(rs.getString(2));
				adven.setCharacterClass(rs.getString(3));
				adven.setMainHand(new Item(rs.getInt(4), rs.getString(5), rs.getShort(6)));
				adven.setOffHand(new Item(rs.getInt(7), rs.getString(8), rs.getShort(9)));
				adven.setArmor(new Item(rs.getInt(10), rs.getString(11), rs.getShort(12)));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println(adven);
		return adven;
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
		List<String> classList = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT name FROM class_type";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				classList.add(rs.getString(1));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return classList;
	}

	@Override
	public void updatePool(Adventurer character) {
		this.updatedAdventurer(character);
	}

	@Override
	public void updateParty(Adventurer character) {
		this.updatedAdventurer(character);
	}
	
	private void updatedAdventurer(Adventurer character) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "UPDATE adventurer SET\n" + 
					"	class_type = ?,\n" + 
					"    main_hand = ?,\n" + 
					"    off_hand = ?,\n" + 
					"    armor = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, classId.get(character.getCharacterClass()));
			stmt.setInt(2, character.getMainHand().getId());
			stmt.setInt(3, character.getOffHand().getId());
			stmt.setInt(4, character.getArmor().getId());
			stmt.setInt(5, character.getId());
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
