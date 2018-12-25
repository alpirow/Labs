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
import by.gsu.pms.pojo.Galaxy;

@WebServlet("/Query4")
public class Query4 extends HttpServlet {
	private static final long serialVersionUID = 4L;
    public Query4() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String sql = "SELECT * FROM galaxy WHERE id = (SELECT galaxy_id as id FROM planet group by galaxy_id order by sum(temp) desc limit 1);";
		PrintWriter out = response.getWriter();
		try {
			Connection db = Connector.getInstance().getConnection();
			PreparedStatement statement = db.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				out.print("<h2>Галактика, сумма ядерных температур планет которой наибольшая.</h2>");
				out.print("<h4>"+ new Galaxy(result).getName() + "</h4>");
			}
		}catch(SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
