package com.employee.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpModel {

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
		return con;

	}

	public Employee save(String name, int id, int salary, int age, String action) {
		Employee emp = new Employee(name, id, salary, age);

		Connection con = null;
		try {
			con = this.getConnection();
			PreparedStatement ps;

			if (action.equals("Insert")) {
				// System.out.println("Insert");
				ps = con.prepareStatement("INSERT INTO DB2.EMP2(NAME,ID,SALARY,AGE) VALUES (?,?,?,?)");

				ps.setString(1, name);
				ps.setInt(2, id);
				ps.setInt(3, salary);
				ps.setInt(4, age);
				int i = ps.executeUpdate();
				if (i != 0) {
					System.out.println(i + " records inserted");
					return emp;
				}

			} else if (action.equals("Update")) {
				// System.out.println("Update");
				ps = con.prepareStatement("UPDATE DB2.EMP2 SET NAME = ?,SALARY=?,AGE=? WHERE ID=?");

				ps.setString(1, name);
				ps.setInt(2, salary);
				ps.setInt(3, age);
				ps.setInt(4, id);
				int i = ps.executeUpdate();
				System.out.println("Update: " + i);

				if (i != 0) {
					System.out.println(i + " records updated");
					return emp;
				}
			}
			/*
			 * ps = con.prepareStatement("SELECT * FROM DB2.EMP2 WHERE ID=?");
			 * ps.setInt(1, id);
			 * 
			 * ResultSet rs = ps.executeQuery(); // out.print(
			 * "<table border='1' cellpadding='2' width='100%'>"); // out.print(
			 * "<tr><th>Name</th><th>Id</th><th>Salary</th><th>Age</th></tr>");
			 * while (rs.next()) {
			 * 
			 * // out.print("<td>" + "<input type='checkbox' name='emp' //
			 * onchange='getRow(this)'/>" + "</td><td>" // + rs.getString(1) +
			 * "</td><td>" + rs.getInt(2) + // "</td><td>" + rs.getInt(3) +
			 * "</td><td>" // + rs.getInt(4) + "</td>");
			 * emp.setName(rs.getString(1)); emp.setId(rs.getInt(2));
			 * emp.setSalary(rs.getInt(3)); emp.setAge(rs.getInt(4)); //
			 * list.add(1, emp);
			 * 
			 * }
			 */

			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class Not Found " + e);

		} catch (SQLException e) {
			System.out.println("Connection failed Or PreparedStatement error " + e);

		}
		return null;

	}

	public void delete(int id, String action) {
		if (action.equals("Delete")) {
			// System.out.println("DELETION LOOP");
			try {
				Connection con = this.getConnection();
				PreparedStatement ps;
				ps = con.prepareStatement("DELETE FROM DB2.EMP2 WHERE ID=?");
				ps.setInt(1, id);
				// System.out.println(id);
				int i = ps.executeUpdate();
				// System.out.println("Update: " + i);
				if (i != 0)
					System.out.println(i + " record deleted");

			} catch (ClassNotFoundException e) {
				System.out.println("Driver Class Not Found " + e);

			} catch (SQLException e) {
				System.out.println("Connection failed Or PreparedStatement error " + e);

			}
		}

	}

	public ArrayList<Employee> show() {
		ArrayList<Employee> emps = new ArrayList<Employee>();

		try {
			Connection con = this.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM DB2.EMP2");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();

				emp.setName(rs.getString(1));
				emp.setId(rs.getInt(2));
				emp.setSalary(rs.getInt(3));
				emp.setAge(rs.getInt(4));
				emps.add(emp);

			}

		} catch (Exception e) {
			System.out.println(e);

		}

		return emps;

	}

}