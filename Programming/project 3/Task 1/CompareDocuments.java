public class CompareDocuments {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        String[] filenames = StdIn.readAllStrings();
        int n = filenames.length;

        // create document sketches
        Sketch[] sketches = new Sketch[n];
        for (int i = 0; i < n; i++) {
            In in = new In (filenames[i]);
            String text = in.readAll();
            sketches[i] = new Sketch(text, k, d);
        }
       
        // print header
        StdOut.print("    ");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%8.4s", filenames[i]);
        }
        StdOut.println();

        // print n-by-n table
        for (int i = 0; i < n; i++) {
            StdOut.printf("%.4s", filenames[i]);
            for (int j = 0; j < n; j++) {
                StdOut.printf("%8.2f", sketches[i].similarTo(sketches[j]));
            }
            StdOut.println();
        }
    }
}