// Omar Alfakir
// Ahamd Alsalehy
public class D2Ex3{
    public static void main(String[] cmdLn) {

        StdDraw.setXscale(0.0,5.0);
        StdDraw.setYscale(0.0,5.0);
        StdDraw.clear(StdDraw.GRAY);
        double x = 0.0;
        double y = 0.0;

        String[] SUITS = {
            "clubs", "diamonds", "hearts", "spades"
        };

        String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "jack", "queen", "king", "ace"
        };
        String[] [] A = new String [13][4];
        int n = SUITS.length * RANKS.length;
        String[] Decks = new String[n];
        for (int i = 0; i < RANKS.length; i++) {
           
            for (int j = 0; j < SUITS.length; j++) {
              
                Decks[SUITS.length*i + j] = RANKS[i] + "_of_" + SUITS[j]+".png";
                               
            }
            
        }
        for (int f = 0; f < n; f++) {
            int r = f + (int) (Math.random() * (n-f));
            String temp = Decks[r];
            Decks[r] = Decks[f];
            Decks[f] = temp;
        
          }
        int c = 0;
        for (int q = 0 ; q < 13; q++){
            for (int g = 0; g < 4 ; g++){
                A[q][g]= Decks[c];
                c++;
            }

          }
         for (int z = 0 ; z < 13; z++){
                x = (0.2 *z) +1 ;
              for (int s = 0; s < 4; s++){
                  y = (0.9 *s) +1;
                  StdDraw.picture(x,y,A[z][s], 0.5, 1.4*0.5); 
        }
        
    }

        StdDraw.show();
              
    }    
}  
    

