package by.gsu.pms.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Planet {
	private int id;
	private String name;
	private double radius;
	private double temp;
	private boolean atmosphere;
	private boolean life;
	private int galaxyId;
	public Planet(int id, String name, double radius, double temp, boolean atmosphere, boolean life, int galaxyId) {
		super();
		this.id = id;
		this.name = name;
		this.radius = radius;
		this.temp = temp;
		this.atmosphere = atmosphere;
		this.life = life;
		this.galaxyId = galaxyId;
	}
	public Planet(ResultSet set) throws SQLException{
		super();
		id = set.getInt("id");
		name = set.getString("name");
		radius = set.getDouble("radius");
		temp = set.getDouble("temp");
		atmosphere = set.getBoolean("atmosphere");
		life = set.getBoolean("life");
		galaxyId = set.getInt("galaxy_id");
	}
	public Planet() {
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
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public boolean isAtmosphere() {
		return atmosphere;
	}
	public void setAtmosphere(boolean atmosphere) {
		this.atmosphere = atmosphere;
	}
	public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public int getGalaxyId() {
		return galaxyId;
	}
	public void setGalaxyId(int galaxyId) {
		this.galaxyId = galaxyId;
	}
}
