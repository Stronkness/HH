//Omar Alfakir and Ahmad Alsalehy som har testat och programmerat tilsammans.
public class D1E2{
    public static void main(String[] args) {
        String c = args[0];
		int k = 5;
        int d = 10000;
        String[] filenames = StdIn.readAllStrings();
        int n = filenames.length;

        // create document sketches
		In cin = new In (c);
		String stext = cin.readAll();
		Sketch cSketch = new Sketch(stext, k,d);
        Sketch[] sketches = new Sketch[n];
        for (int i = 0; i < n; i++) {
            In in = new In (filenames[i]);
            String text = in.readAll();
			sketches[i] = new Sketch(text, k, d);
            
        }
				String mostSimilar = "";
		        double max = Double.NEGATIVE_INFINITY;
				for (int s = 0 ; s < n; s++){
                        
						StdOut.printf("%s%8.2f",filenames[s]+" ",cSketch.similarTo(sketches[s]));
                        StdOut.println();
                       if(max < cSketch.similarTo(sketches[s])){
						   max = cSketch.similarTo(sketches[s]);
					       mostSimilar = filenames[s];							
					   }
				}
				StdOut.println("The text that is more similar to " + c +" is "+ mostSimilar);
				
				
			
        
	}
}