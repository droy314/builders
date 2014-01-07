package net.qnd.java.builders.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.qnd.java.builders.spi.AbstractBuilderHandler;
import net.qnd.java.builders.spi.BuilderFactory;

class DefaultBuilderHandler extends AbstractBuilderHandler implements InvocationHandler {

	public DefaultBuilderHandler(BuilderFactory factory, Class<?> builderType) {
		this(factory, builderType, null);
	}

	public DefaultBuilderHandler(BuilderFactory factory, Class<?> builderType, Object parentBuilder) {
		super(factory, builderType, parentBuilder);
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return stubCall(proxy, method, args);
	}

}