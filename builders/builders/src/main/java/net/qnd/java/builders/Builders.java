package net.qnd.java.builders;

import net.qnd.java.builders.reflect.ReflectProxyBuilderFactory;
import net.qnd.java.builders.spi.BuilderFactory;


public class Builders {

	public static <BuilderT> BuilderT newBuilder(Class<BuilderT> builderType) {
		return newBuilder(new ReflectProxyBuilderFactory(), builderType);
	}
	
	public static <BuilderT> BuilderT newBuilder(BuilderFactory factory, Class<BuilderT> builderType) {
		return factory.createBuilder(builderType);
	}

	
}
