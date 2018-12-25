package by.gsu.pms.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	static final String URL = "jdbc:mysql://localhost:3333/planets?useSSL=false&characterEncoding=UTF8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static Connector connector;
	Connection connection;
	
	private Connector() {
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, "root", "admin");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connector getInstance() {
		if(connector == null) {
			connector = new Connector();
		}
		return connector;
	}
	public Connection getConnection() {
		return connection;
	}

}
