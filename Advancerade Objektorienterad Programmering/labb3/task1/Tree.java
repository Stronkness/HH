package labb3.task1;

@Element(name="node")
public class Tree<T> {
	private Tree<T>[] children = null;
	private T value;
	
	public Tree(T v, Tree<T>[] trees) { 
		children = trees; value = v; 
	}
	public Tree(T v) {
		value = v; 
	}
	
	@SubElements(name="subnodes")
	public Tree<T>[] getChildren() {
		return children;
	}
	@ElementField(name="value")
	public T getValue() {
		return value;
	}
}
