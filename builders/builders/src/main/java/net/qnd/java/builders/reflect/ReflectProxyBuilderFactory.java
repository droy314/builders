package net.qnd.java.builders.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import net.qnd.java.builders.spi.BuilderFactory;

public class ReflectProxyBuilderFactory implements BuilderFactory {

	@SuppressWarnings("unchecked")
	public <T, Z extends T> Z createBuilder(Class<T> builderType) {
		InvocationHandler handler = new DefaultBuilderHandler(this, builderType);
		return (Z) Proxy.newProxyInstance(Thread.currentThread()
				.getContextClassLoader(), new Class<?>[] { builderType },
				handler);
	}

	@SuppressWarnings("unchecked")
	public <T, Z extends T> Z createNestedBuilder(Class<T> builderType,
			Object parentBuilder) {
		InvocationHandler handler = new DefaultBuilderHandler(this, builderType,
				parentBuilder);
		return (Z) Proxy.newProxyInstance(Thread.currentThread()
				.getContextClassLoader(), new Class<?>[] { builderType },
				handler);
	}

}
