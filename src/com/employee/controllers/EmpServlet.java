
package com.employee.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.EmpModel;
import com.employee.model.Employee;

/**
 * Servlet implementation class EmpServlet
 * 
 * @param <EmpModel>
 */
@WebServlet("/servlet1")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// out.print("Hello world");
		String action = request.getParameter("action");

		EmpModel obj = new EmpModel();

		if (action.equals("load")) {
			ArrayList<Employee> list = (ArrayList<Employee>) obj.show();
			for (Employee emp : list) {

				out.print("<tr><td>" + "<input type='checkbox' name='emp'onchange='getRow(this)'/>" + "</td><td>"
						+ emp.getName() + "</td><td>" + emp.getId() + "</td><td>" + emp.getSalary() + "</td><td>"
						+ emp.getAge() + "</td></tr>");
				// out.println(" ");
			}
		} else {
			String name = request.getParameter("name");
			// out.print(name);
			int id = Integer.parseInt(request.getParameter("id"));
			int salary = Integer.parseInt(request.getParameter("salary"));
			int age = Integer.parseInt(request.getParameter("age"));

			if (action.equals("Delete")) {
				obj.delete(id, action);
			} else {
				Employee emp = obj.save(name, id, salary, age, action);
				out.print("<td>" + "<input type='checkbox' name='emp'onchange='getRow(this)'/>" + "</td><td>"
						+ emp.getName() + "</td><td>" + emp.getId() + "</td><td>" + emp.getSalary() + "</td><td>"
						+ emp.getAge() + "</td>");
			}

		}

	}

}
