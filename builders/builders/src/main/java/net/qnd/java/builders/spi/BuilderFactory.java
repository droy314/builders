package net.qnd.java.builders.spi;

public interface BuilderFactory {

	<T, Z extends T> Z createBuilder(Class<T> builderType);

	<T, Z extends T> Z createNestedBuilder(Class<T> builderType,
			Object parentBuilder);

}
