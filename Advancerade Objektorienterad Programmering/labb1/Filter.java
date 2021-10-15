package labb1;

import java.util.ArrayList;

interface Filter{
	boolean accept(String x);
	String[] filter(String[] a, Filter f);
}
class Filter2 implements Filter{
	public String[] filter(String[] a, Filter f) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < a.length-1; i++) {
			if(f.accept(a[i])) {
				list.add(a[i]);
			}
		}
		String[] updated = list.toArray(new String[list.size()]);
		return updated;
	}
	public boolean accept(String x) {
		if(x != null && x.length() <= 3) {
			return true;
		}
		return false;
	}
}
