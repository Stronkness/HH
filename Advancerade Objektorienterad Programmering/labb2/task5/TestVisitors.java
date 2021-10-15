package labb2.task5;

import java.util.ArrayList;
import java.util.List;

public class TestVisitors {
	public static void main(String[] args) {
		args = new String[] { "one", "two", "three" };
		List<Tree<String>> leaves = new ArrayList<Tree<String>>();
		
		for (int i = 0; i < args.length; i++)
			leaves.add(new Leaf<String>(args[i]));
		
		Tree<String> t1 = new Node<String>(leaves);
		Tree<String> t2 = new Node<String>(leaves);
		Tree<String> t3 = new Node<String>(leaves);
		
		List<Tree<String>> lst = new ArrayList<Tree<String>>();
		lst.add(t1);
		lst.add(t2);
		lst.add(t3);
		Tree<String> t = new Node<String>(lst);
		
		TreeVisitor<String,Integer> thv = new TreeHeightVisitor<String>();
		TreeVisitor<String,List<String>> llv = new ListLeavesVisitor<String>();
		TreeVisitor<String,Integer> lcv = new LeavesCountVisitor<String>();
		
		System.out.println("Tree: " + t);
		System.out.println("Tree height: "+t.accept(thv, 0));
		System.out.println("Leaves: "+t.accept(llv, new ArrayList<String>()));
		System.out.println("Leaves count: "+t.accept(lcv, 0));
		
		System.out.println("Pretty print out Trees: \n");
		TreeVisitor<String, String> print = new PrettyPrintTress<String>();
		System.out.println("Tree: " + t.accept(print, ""));
		
	}
}
