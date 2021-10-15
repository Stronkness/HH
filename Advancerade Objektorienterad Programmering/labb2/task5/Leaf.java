package labb2.task5;

public class Leaf<T> implements Tree<T> {

	private T value;

	public Leaf(T v) {
		value = v;
	}

	public <R> R accept(TreeVisitor<T, R> v, R initialResult) {
		return v.visit(this, initialResult);
	}

	public String toString() {
		return getClass().getName() + "[value = " + value + "]";
	}

	public T getValue() {
		return value;
	}
}