package net.qnd.java.builders;

public interface NestedBuilder<P> {

	void setParent(Class<P> type, P builder);
	
}
