package by.gsu.pms;

import java.io.IOException;
import java.io.PrintWriter;
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
import by.gsu.pms.pojo.Planet;
import by.gsu.pms.pojo.Sputnic;


@WebServlet("/Query1")
public class Query1 extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public Query1() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String sql = "SELECT * FROM planet WHERE life = true AND galaxy_id = ?";
		PrintWriter out = response.getWriter();
		try {
			PreparedStatement st = Connector.getInstance().getConnection().prepareStatement(sql);
			st.setInt(1,Integer.parseInt(request.getParameter("id")));
			ResultSet result = st.executeQuery();
			List<Planet> planetList = new ArrayList<Planet>();
			while(result.next()) {
				planetList.add(new Planet(result));
			}
			sql = "SELECT * FROM sputnic WHERE planet_id = ?";
			st = Connector.getInstance().getConnection().prepareStatement(sql);
			for(Planet planet:planetList) {
				out.print("<h4>"+ planet.getName() + "</h4>");
				st.setInt(1, planet.getId());
				result = st.executeQuery();
				while(result.next()) {
					out.print("<h5>"+ new Sputnic(result).getName() + "</h5>");
				}
			}
		}catch(SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

}
