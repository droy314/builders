package net.qnd.java.builders.test;

import net.qnd.java.builders.Builder;

public interface PersonBuilder extends Builder<Person> {
	
	PersonBuilder firstName(String firstName);
	
	PersonBuilder lastName(String lastName);
	
	AddressBuilder address();
	
	PersonBuilder spouse();
	
	PersonBuilder addChild();
	
	PersonBuilder end();

}
