package labb2.task5;

public class LeavesCountVisitor<T> implements TreeVisitor<T, Integer> {

	public Integer visit(Tree<T> t, Integer initialResult) {
		return t.accept(this, initialResult);
	}

	public Integer visit(Leaf<T> l, Integer initialResult) {
		return initialResult + 1;
	}

	public Integer visit(Node<T> n, Integer initialResult) {
		Integer result = initialResult;
		for (Tree<T> child : n.getChildren()) {
			result = child.accept(this, result);
		}
		return result;
	}

}