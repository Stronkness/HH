package labb2.task5;

public class PrettyPrintTress<T> implements TreeVisitor<T, String>{
	int counter = 0;
	boolean eraseInvisible = true;
	
	public String visit(Tree<T> t, String initialResult) {
		return t.accept(this, initialResult);
	}

	public String visit(Leaf<T> l, String initialResult) {
		return initialResult + " \n" + l.getValue();
	}

	public String visit(Node<T> n, String initialResult) {
		if(eraseInvisible) {
			eraseInvisible = false;
		}else {
		counter++;
		initialResult += "\n Node " + counter;
		}
		for (Tree<T> child : n.getChildren()) {
			for(int i = 0; i < counter; i++) {
				initialResult += "\t";
			}
			initialResult = child.accept(this, initialResult);
		}
		counter--;
		return initialResult;
	}
}
