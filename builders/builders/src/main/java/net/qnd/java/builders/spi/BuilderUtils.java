package net.qnd.java.builders.spi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.qnd.java.builders.Builder;

public class BuilderUtils {

	/**
	 * Returns the type being built by an interface. This method uses the
	 * parameterized type for the Builder interface to determine the class that
	 * will be built.
	 * 
	 * @param builderType
	 * @return
	 */
	public static Class<?> getBuiltProductType(Class<?> builderType) {
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
				return getBuiltProductType((Class<?>) interfaceType);
			}
		}
		return null;
	}

}
