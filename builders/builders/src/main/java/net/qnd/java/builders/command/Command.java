package net.qnd.java.builders.command;

public interface Command {
	<T> T execute(T object);
}