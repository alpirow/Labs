<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="by.gsu.pms.connector.Connector"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.mysql.cj.jdbc.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Выберите запрос</title>
</head>
<body>
<h2>Выберите запрос</h2>
<br>
<p>Вывести информацию обо всех планетах, на которых присутствует жизнь, и их спутниках в заданной галактике.</p>
<form method="post" action="Query1">
	<select name="id">
	<%
	ResultSet rs = null;
	try{
		
		Connection db = Connector.getInstance().getConnection();
		String sql = "SELECT * FROM galaxy;";
		PreparedStatement st = db.prepareStatement(sql);
		rs = st.executeQuery();
	}catch(SQLException e){
		e.printStackTrace();
	}
	if(rs != null){
		while(rs.next()){
	%>
		<option value="<%=rs.getInt("id")%>"><%=rs.getString("name") %></option>
	<%}  }%>
	</select>
	<br>
	<button type="submit">Вывести</button>
</form>
<p>Вывести информацию о планетах и их спутниках, имеющих наименьший радиус и наибольшее количество спутников.</p>
<form method="post" action="Query2">
<button type="submit">Вывести</button>
</form>
<p>Вывести информацию о планете, галактике, в которой она находится, и ее спутниках, имеющей максимальное количество спутников, но с наименьшим общим объемом этих спутников.</p>
<form method="post" action="Query3">
<button type="submit">Вывести</button>
</form>
<p>Найти галактику, сумма ядерных температур планет которой наибольшая.</p>
<form method="post" action="Query4">
<button type="submit">Вывести</button>
</form>
</body>
</html>