package by.gsu.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.pms.connector.Connector;
import by.gsu.pms.pojo.Planet;
import by.gsu.pms.pojo.Sputnic;


@WebServlet("/Query2")
public class Query2 extends HttpServlet {
	private static final long serialVersionUID = 2L;
    public Query2() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String sql1 = "SELECT * FROM planet ORDER BY radius;";
		String sql2 = "SELECT * FROM sputnic WHERE planet_id = ?";
		String sql3 = "SELECT planet_id as id, count(planet_id) as num FROM sputnic group by planet_id order by num desc limit 1;";
		String sql4 = "SELECT * FROM planet WHERE id = ?";
		PrintWriter out = response.getWriter();
		try {
			Connection db = Connector.getInstance().getConnection();
			PreparedStatement stLittlePlanet = db.prepareStatement(sql1);
			PreparedStatement stSputnic = db.prepareStatement(sql2);
			PreparedStatement stCount = db.prepareStatement(sql3);
			PreparedStatement stPlanet = db.prepareStatement(sql4);
			ResultSet result = stLittlePlanet.executeQuery();
			Planet planet;
			if(result.next()) {
				planet = new Planet(result);
				out.print("<h2>Наименьший радиус</h2>");
				out.print("<h4>"+ planet.getName() + "</h4>");
				stSputnic.setInt(1, planet.getId());
				result = stSputnic.executeQuery();
				while(result.next()) {
					out.print("<h5>"+ new Sputnic(result).getName() + "</h5>");
				}
			}
			result = stCount.executeQuery();
			if(result.next()) {
				int planetId = result.getInt("id");
				stPlanet.setInt(1, planetId);
				result = stPlanet.executeQuery();
				result.next();
				planet = new Planet(result);
				out.print("<h2>Наибольшее количество спутников</h2>");
				out.print("<h4>"+ planet.getName() + "</h4>");
				stSputnic.setInt(1, planet.getId());
				result = stSputnic.executeQuery();
				while(result.next()) {
					out.print("<h5>"+ new Sputnic(result).getName() + "</h5>");
				}
			}
		}catch(SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
