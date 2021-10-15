package labb2.task5;


public interface TreeVisitor<T, R> {
	R visit(Tree<T> t, R initialResult);

	R visit(Leaf<T> l, R initialResult);

	R visit(Node<T> n, R initialResult);
}