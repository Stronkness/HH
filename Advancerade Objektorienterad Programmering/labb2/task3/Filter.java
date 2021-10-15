package labb2.task3;

public abstract class Filter {
	public String[] filter(String[] a, Filter f) {
		String[] test = new String[a.length];
		for(int i = 0; i < a.length-1; i++) {
			if(f.accept(a[i])) {
				test[i] = a[i];
			}
		}
		return test;
	}
   abstract boolean accept(String x);
}