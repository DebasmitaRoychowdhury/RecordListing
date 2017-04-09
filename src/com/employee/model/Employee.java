
package com.employee.model;

public class Employee {
	private String name;
	private int id;

	private int salary;
	private int age;

	public Employee() {

	}

	public Employee(String name, int id, int salary, int age) {
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
