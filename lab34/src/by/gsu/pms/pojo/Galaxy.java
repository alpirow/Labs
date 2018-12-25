package by.gsu.pms.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Galaxy {
	private int id;
	private String name;
	public Galaxy(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Galaxy(ResultSet set) throws SQLException{
		super();
		id = set.getInt("id");
		name = set.getString("name");
	}
	public Galaxy() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
