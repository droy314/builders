package net.qnd.java.builders.command;

import net.qnd.java.builders.Builder;
import net.qnd.java.builders.spi.BuilderUtils;

class SetBuiltObjectCommand implements Command {

	private String propertyName;

	private Builder<?> builder;

	public SetBuiltObjectCommand(String propertyName, Builder<?> builder) {
		this.builder = builder;
		this.propertyName = propertyName;
	}

	public <T> T execute(T object) {
		Object built = builder.build();
		return (new SetCommand(propertyName, built,
				BuilderUtils.getBuiltProductType(builder.getClass())))
				.execute(object);
	}

}