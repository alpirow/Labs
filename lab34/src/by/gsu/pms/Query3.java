package by.gsu.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.pms.connector.Connector;
import by.gsu.pms.pojo.Galaxy;
import by.gsu.pms.pojo.Planet;
import by.gsu.pms.pojo.Sputnic;


@WebServlet("/Query3")
public class Query3 extends HttpServlet {
	private static final long serialVersionUID = 2L;
    public Query3() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String sql1 = "SELECT * FROM sputnic WHERE planet_id = ?";
		String sql2 = "SELECT planet_id as id, count(planet_id) as num FROM sputnic group by planet_id order by num desc;";
		String sql3 = "SELECT * FROM planet WHERE id = ?";
		String sql4 = "SELECT * FROM galaxy WHERE id = ?";
		PrintWriter out = response.getWriter();
		try {
			Connection db = Connector.getInstance().getConnection();
			PreparedStatement stSputnic = db.prepareStatement(sql1);
			PreparedStatement stCount = db.prepareStatement(sql2);
			PreparedStatement stPlanet = db.prepareStatement(sql3);
			PreparedStatement stGalaxy = db.prepareStatement(sql4);
			ResultSet result = stCount.executeQuery();
			result.next();
			List<Integer> concurents = new ArrayList<>();
			concurents.add(result.getInt("id"));
			int currentTotal = result.getInt("num");
			while(result.next()&&currentTotal == result.getInt("num")) {
				concurents.add(result.getInt("id"));
			}
			int planetId = 0;
			if(concurents.size()>1) {
				List<Double> volumes = new ArrayList<>();
				for(int id:concurents) {
					stSputnic.setInt(1, id);
					result = stSputnic.executeQuery();
					double volume = 0;
					while(result.next()) {
						volume += result.getDouble("radius")*result.getDouble("radius")*result.getDouble("radius");
					}
					volumes.add(volume);
				}
				//min volume
				int min = 0;
				for(Double volume : volumes) {
					if(volume<volumes.get(min)) {
						min = volumes.indexOf(volume);
					}
				}
				planetId = min;
			}
			stPlanet.setInt(1, concurents.get(planetId));
			result = stPlanet.executeQuery();
			result.next();
			Planet planet = new Planet(result);
			stGalaxy.setInt(1, planet.getGalaxyId());
			result = stGalaxy.executeQuery();
			result.next();
			Galaxy galaxy = new Galaxy(result);
			stSputnic.setInt(1, planetId);
			result = stSputnic.executeQuery();
			out.print("<h2>Информация о планете, галактике, в которой она находится, и ее спутниках, имеющей максимальное количество спутников, но с наименьшим общим объемом этих спутников.</h2>");
			out.print("<h4>Галактика: "+ galaxy.getName()+"</h4>");
			out.print("<h4>Планета: "+ planet.getName() + "</h4>");
			while(result.next()) {
				out.print("<h5>    "+ new Sputnic(result).getName() + "</h5>");
			}	
		}catch(SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
