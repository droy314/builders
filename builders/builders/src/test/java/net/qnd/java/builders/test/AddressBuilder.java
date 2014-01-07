package net.qnd.java.builders.test;

import net.qnd.java.builders.Builder;

public interface AddressBuilder extends Builder<Address>{

	AddressBuilder line1(String line1);
	
	AddressBuilder line2(String line2);
	
	AddressBuilder line3(String line3);
	
	PersonBuilder end();
	
}
