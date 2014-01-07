package net.qnd.java.builders.test;

import java.util.List;

public class Person {
	
	private String firstName;
	
	private String lastName;
	
	private Person spouse;
	
	private List<Person> children;
	
	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}

	private Address address;

	public Person getSpouse() {
		return spouse;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
