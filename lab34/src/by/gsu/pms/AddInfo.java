package by.gsu.pms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import by.gsu.pms.connector.Connector;

public class AddInfo {

	public static void main(String[] args) {
		Connection connection = Connector.getInstance().getConnection();
		try {
			Statement st = connection.createStatement();
			st.execute("SET NAMES UTF8;");
			st.execute("TRUNCATE galaxy;");
			st.execute("TRUNCATE planet;");
			st.execute("TRUNCATE sputnic;");
			st.close();
			String query = "INSERT INTO galaxy (name) VALUES (?);";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, "Млечный путь");
			statement.execute();
			statement.setString(1, "Андромеды");
			statement.execute();
			statement.setString(1, "Треугольника");
			statement.execute();
			query = "INSERT INTO planet(name,radius,temp,atmosphere,life,galaxy_id) VALUES (?,?,?,?,?,?);";
			statement = connection.prepareStatement(query);
			addPlanet(statement, "Земля", 6371, 700, true, true, 1);
			addPlanet(statement, "Меркурий", 2439, 2732, false, false, 1);
			addPlanet(statement, "Венера", 6051, 1573, false, false, 1);
			addPlanet(statement, "Марс", 2439, 654, false, true, 1);
			addPlanet(statement, "Юпитер", 71492, 762, false, false, 1);
			addPlanet(statement, "Сатурн", 60268, 142, false, false, 1);
			addPlanet(statement, "Уран", 25559, 302, false, false, 1);
			addPlanet(statement, "Нептун", 24764, 213, false, false, 1);
			addPlanet(statement, "Глизе 581", 2393, 874, true, true, 1);
			addPlanet(statement, "Lunix643", 34252, 2981, true, false, 3);
			addPlanet(statement, "GL-243", 6543, 12000, false, false, 2);
			addPlanet(statement, "Sirius", 7684, 3242, true, true, 1);
			addPlanet(statement, "Rex", 243098, 3244, false, false, 3);
			query = "INSERT INTO sputnic(name,radius,distance,planet_id) VALUES (?,?,?,?);";
			statement = connection.prepareStatement(query);
			addSputnic(statement, "Луна", 1738, 384400, 1);
			addSputnic(statement, "Фобос", 26, 9377, 4);
			addSputnic(statement, "Деймос", 12, 23458, 4);
			addSputnic(statement, "Ио", 3660, 421700,5);
			addSputnic(statement, "Европа", 3121, 671000,5);
			addSputnic(statement, "Ганимед", 5262, 1070000, 5);
			addSputnic(statement, "Каллисто", 4820, 1882809, 5);
			addSputnic(statement, "Амальтея", 250, 181889, 5);
			addSputnic(statement, "Титан", 5150, 1221900, 6);
			addSputnic(statement, "Диона", 1118, 377400, 6);
			addSputnic(statement, "Мимас", 397, 185600, 6);
			addSputnic(statement, "Пак", 162, 86004, 7);
			addSputnic(statement, "Ариэль", 1157, 191020, 7);
			addSputnic(statement, "Бианка", 54, 59165, 7);
			addSputnic(statement, "Eggmoon", 1700, 63165, 12);
			addSputnic(statement, "Atronis", 5472, 73870, 12);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void addPlanet(PreparedStatement st,String name,double radius, double temp, boolean atmosphere, boolean life, int galaxyId) {
		try {	
			st.setString(1,name);
			st.setDouble(2, radius);
			st.setDouble(3, temp);
			st.setBoolean(4, atmosphere);
			st.setBoolean(5, life);
			st.setInt(6, galaxyId);
			st.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static void addSputnic(PreparedStatement st,String name,double radius,double distance,int planet_id) {
		try {
			st.setString(1, name);
			st.setDouble(2,radius);
			st.setDouble(3, distance);
			st.setInt(4,planet_id);
			st.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
