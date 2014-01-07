package net.qnd.java.builders.spi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.qnd.java.builders.Builder;
import net.qnd.java.builders.command.Command;
import net.qnd.java.builders.command.Commands;

public abstract class AbstractBuilderHandler {

	protected final Class<?> productType;
	private final List<Command> commands = new ArrayList<Command>();
	protected final Object parentBuilder;
	protected final BuilderFactory factory;

	protected AbstractBuilderHandler(BuilderFactory factory,
			Class<?> builderType, Object parentBuilder) {
		this.factory = factory;
		this.productType = BuilderUtils.getBuiltProductType(builderType);
		this.parentBuilder = parentBuilder;

	}

	protected Object stubCall(Object proxy, Method method, Object[] args)
			throws Throwable, IllegalAccessException, InvocationTargetException {
		String methodName = method.getName();
		final int paramLength = method.getParameterTypes().length;
		if ("end".equals(methodName)) {
			return parentBuilder;
		} else if ("build".equals(methodName)) {
			return build();
		} else if ("toString".equals(methodName) || "equals".equals(methodName)
				|| "hashCode".equals(methodName)) {
			return method.invoke(proxy, args);
		} /*
		 * else if (methodName.startsWith("add") && paramLength == 0) { //
		 * Returns a builder for a child }
		 */else if (paramLength == 1) {
			final Class<?> propertyType = method.getParameterTypes()[0];
			final Object value = args[0];
			Command cmd = Commands.setPropertyValue(methodName, propertyType,
					value);
			addCommand(cmd);
			return proxy;
		} else if (paramLength == 0) {
			// FIXME: What happens if the return type is not a builder?
			Builder<?> nestedBuilder = (Builder<?>) factory
					.createNestedBuilder(method.getReturnType(), proxy);
			Command cmd = Commands.setPropertyWithBuilderOutput(methodName,
					nestedBuilder);
			addCommand(cmd);
			return nestedBuilder;
		}
		return proxy;
	}

	protected void addCommand(Command command) {
		if (command != null) {
			commands.add(command);
		}
	}

	protected Object build() throws Throwable {
		// TODO: Use ObjectFactory here
		Object object = productType.newInstance();
		for (Command command : commands) {
			command.execute(object);
		}
		return object;
	}

}
