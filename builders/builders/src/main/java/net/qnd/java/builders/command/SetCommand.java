package net.qnd.java.builders.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SetCommand implements Command {

	private final String propertyName;
	private final Object value;
	private final Class<?> propertyType;

	public SetCommand(String propertyName, Object value, Class<?> propertyType) {
		this.propertyName = propertyName;
		this.value = value;
		this.propertyType = propertyType;
	}

	public <T> T execute(T object) {
		Class<?> type = object.getClass();
		Method method = null;
		try {
			method = type.getMethod(getSetterName(), propertyType);
			method.invoke(object, value);
		} catch (NoSuchMethodException mEx) {
			mEx.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;
	}

	private String getSetterName() {
		return "set" + Character.toUpperCase(propertyName.charAt(0))
				+ propertyName.substring(1);
	}

}