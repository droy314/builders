package net.qnd.java.builders.command;

import net.qnd.java.builders.Builder;

public class Commands {

	public static Command setPropertyWithBuilderOutput(
			String propertyName, Builder<?> nestedBuilder) {
		return new SetBuiltObjectCommand(propertyName,
				nestedBuilder);
	}

	public static Command setPropertyValue(String propertyName,
			final Class<?> propertyType, final Object value) {
		return new SetCommand(propertyName, value,
				propertyType);
	}

}
