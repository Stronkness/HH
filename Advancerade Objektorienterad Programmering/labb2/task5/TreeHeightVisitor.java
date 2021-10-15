package labb2.task5;

public class TreeHeightVisitor<T> implements TreeVisitor<T, Integer> {

	public Integer visit(Tree<T> t, Integer initialResult) {
		return t.accept(this, initialResult);
	}

	public Integer visit(Leaf<T> l, Integer initialResult) {
		return 0;
	}

	public Integer visit(Node<T> n, Integer initialResult) {
		Integer result = 0;
		Integer max = result;
		for (Tree<T> child : n.getChildren()) {
			result = child.accept(this, 0);
			if (result > max)
				max = result;
		}
		return max + 1;
	}
}