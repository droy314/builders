package net.qnd.java.builders;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class DefaultBuilderHandler implements InvocationHandler {

	private final Class<?> builderType;

	private final Class<?> productType;

	private final List<Command> commands = new ArrayList<Command>();

	private final Object parentBuilder;

	public DefaultBuilderHandler(Class<?> builderType) {
		this(builderType, null);
	}

	public DefaultBuilderHandler(Class<?> builderType, Object parentBuilder) {
		this.builderType = builderType;
		this.productType = Builders.getBuiltProductType(builderType);
		this.parentBuilder = parentBuilder;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		final int paramLength = method.getParameterTypes().length;
		if ("end".equals(methodName)) {
			return parentBuilder;
		} else if ("build".equals(methodName)) {
			return build();
		} else if ("toString".equals(methodName) || "equals".equals(methodName)
				|| "hashCode".equals(methodName)) {
			return method.invoke(proxy, args);
		} /*else if (methodName.startsWith("add") && paramLength == 0) {
			// Returns a builder for a child
		} */else if (paramLength == 1) {
			Command cmd = new SetCommand(methodName, args[0],
					method.getParameterTypes()[0]);
			commands.add(cmd);
			return proxy;
		} else if (paramLength == 0) {
			// FIXME: What happens if the return type is not a builder?
			Builder<?> newNestedBuilder = (Builder<?>) Builders
					.newNestedBuilder(method.getReturnType(), proxy);
			Command cmd = new SetBuiltObjectCommand(methodName,
					newNestedBuilder);
			commands.add(cmd);
			return newNestedBuilder;
		}
		return proxy;
	}

	private Object build() throws Throwable {
		// TODO: Use ObjectFactory here
		Object object = productType.newInstance();
		for (Command command : commands) {
			command.execute(object);
		}
		return object;
	}

}