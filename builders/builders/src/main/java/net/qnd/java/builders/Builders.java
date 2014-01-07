package net.qnd.java.builders;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class Builders {

	public static <BuilderT> BuilderT newBuilder(Class<BuilderT> builderType) {
		InvocationHandler handler = new DefaultBuilderHandler(builderType);
		return (BuilderT) Proxy.newProxyInstance(Thread.currentThread()
				.getContextClassLoader(), new Class<?>[] { builderType },
				handler);

	}
	
	static Class<?> getBuiltProductType(Class<?> builderType) {
		Type[] interfaces = builderType.getGenericInterfaces();
		for (Type interfaceType : interfaces) {
			if (interfaceType instanceof ParameterizedType) {
				ParameterizedType parameterizedIntf = (ParameterizedType) interfaceType;
				if (parameterizedIntf.getRawType().equals(Builder.class)) {
					if (parameterizedIntf.getActualTypeArguments()[0] instanceof Class) {
						return (Class<?>) parameterizedIntf
								.getActualTypeArguments()[0];
					}
				}
			} else if (interfaceType instanceof Class) {
				return getBuiltProductType((Class<?>)  interfaceType);
			}
		}
		return null;
	}
	
	static <BuilderT> BuilderT newNestedBuilder(Class<BuilderT> builderType, Object parentBuilder) {
		InvocationHandler handler = new DefaultBuilderHandler(builderType, parentBuilder);
		return (BuilderT) Proxy.newProxyInstance(Thread.currentThread()
				.getContextClassLoader(), new Class<?>[] { builderType, NestedBuilder.class },
				handler);

	}
	
	
	

}
