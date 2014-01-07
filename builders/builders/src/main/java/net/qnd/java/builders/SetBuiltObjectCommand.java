package net.qnd.java.builders;

class SetBuiltObjectCommand implements Command {

	private String propertyName;

	private Builder<?> builder;

	public SetBuiltObjectCommand(String propertyName, Builder<?> builder) {
		this.builder = builder;
		this.propertyName = propertyName;
	}

	public void execute(Object object) {
		Object built = builder.build();
		new SetCommand(propertyName, built,
				Builders.getBuiltProductType(builder.getClass()))
				.execute(object);
	}

}