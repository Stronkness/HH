public class MyArrayListMain {
	/**
	 * <hi>MyArrayListMain.java</h1>
	 * <p>
	 * The program used with your own created MyArrayList to test your Arraylist functions.
	 * </p>
	 * @author André Frisk
	 * @version 1.0
	 * @since  2019-04-09
	 */
	public static void main(String[] args) {
		
		MyArrayList<String> king = new MyArrayList<String>(); 
		Object[] array = king.toArray();
		
		king.add("Chris");
		king.add("Andre");
		king.add("Frisk");
		
		System.out.println(king.contains("Chris"));
		
		System.out.print(king.get(23));
		
		System.out.println(king.indexOf("Chris"));
		
		System.out.println(king.isEmpty());
		
		System.out.println(king.remove("Fredrik"));
		
		System.out.println(king.size());
		
		king.toArray();
		for(int i = 0; i < array.length-1; i++) {
		System.out.println(array[i]);
		}
		
		ArrayListIterator<String> itrprint = (ArrayListIterator<String>)king.iterator();
		while(itrprint.hasNext()) {
			System.out.print(itrprint.next());
			System.out.print(" ");
			
		}
	}
}
