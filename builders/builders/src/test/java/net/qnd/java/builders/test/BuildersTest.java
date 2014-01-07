package net.qnd.java.builders.test;

import static org.junit.Assert.*;
import net.qnd.java.builders.Builders;

import org.junit.Test;

public class BuildersTest {

	@Test
	public void testBuildSimple() {
		PersonBuilder builder = Builders.newBuilder(PersonBuilder.class);

		Person person = builder.firstName("First").lastName("Last").build();

		assertEquals("First", person.getFirstName());
		assertEquals("Last", person.getLastName());
	}
	
	@Test
	public void testBuildNestedSameType() {
		PersonBuilder builder = Builders.newBuilder(PersonBuilder.class);

		Person person = builder.firstName("First").lastName("Last").spouse()
				.firstName("SFirst").lastName("SLast").end().build();

		assertEquals("First", person.getFirstName());
		assertEquals("Last", person.getLastName());
		Person spouse = person.getSpouse();
		assertNotNull(spouse);
		assertEquals("SFirst", spouse.getFirstName());
		assertEquals("SLast", spouse.getLastName());
	}
	
	@Test
	public void testBuildNested() {
		PersonBuilder builder = Builders.newBuilder(PersonBuilder.class);

		Person person = builder.firstName("First").lastName("Last").address()
				.line1("line1").end().build();

		assertEquals("First", person.getFirstName());
		assertEquals("Last", person.getLastName());
		Address address = person.getAddress();
		assertNotNull(address);
		assertEquals("line1", address.getLine1());
	}
}
