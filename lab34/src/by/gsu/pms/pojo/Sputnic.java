package by.gsu.pms.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sputnic {
	private int id;
	private String name;
	private double radius;
	private double distance;
	private int planetId;
	public Sputnic(int id, String name, double radius, double distance, int planetId) {
		super();
		this.id = id;
		this.name = name;
		this.radius = radius;
		this.distance = distance;
		this.planetId = planetId;
	}
	public Sputnic() {
		super();
	}
	public Sputnic(ResultSet set) throws SQLException{
		id = set.getInt("id");
		name = set.getString("name");
		radius = set.getDouble("radius");
		distance = set.getDouble("distance");
		planetId = set.getInt("planet_id");
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getPlanetId() {
		return planetId;
	}
	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}
	
}
