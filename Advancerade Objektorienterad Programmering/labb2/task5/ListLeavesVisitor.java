package labb2.task5;

import java.util.*;

public class ListLeavesVisitor<T> implements TreeVisitor<T, List<T>> {

	public List<T> visit(Tree<T> t, List<T> initialResult) {
		return t.accept(this, initialResult);
	}

	public List<T> visit(Leaf<T> l, List<T> initialResult) {
		initialResult.add(l.getValue());
		return initialResult;
	}

	public List<T> visit(Node<T> n, List<T> initialResult) {
		List<T> result = initialResult;
		for (Tree<T> child : n.getChildren()) {
			result = child.accept(this, result);
		}
		return result;
	}

}