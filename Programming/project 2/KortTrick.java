public class KortTrick {

	public static int[] bastre(int nummervald,int[] rad){
		for(int z = 2; z > -1; z--){

			rad[z] = (int)(nummervald/(Math.pow(3,z)));
			nummervald = (int)(nummervald%(Math.pow(3,z)));
		}
		return rad;
	}





    public static void main(String[] args) {

		StdDraw.setXscale(0,2);
		StdDraw.setYscale(0,3);

		int [] rad = new int [3];
		String[][] nyblandning=new String [9][3];
		String [] blandning=new String [27];
        String[] Suits = {
            "Clubs", "Diamonds", "Hearts", "Spades"
        };

        String[] Ranks = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
        };

        int n = Suits.length * Ranks.length;
        String[] deck = new String[n];
        for (int i = 0; i < Ranks.length; i++) {
            for (int j = 0; j < Suits.length; j++) {
                deck[Suits.length*i+ j] = Ranks[i] + "_of_" + Suits[j]+".png";
            }
        }

        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n-i));
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
		int a=0;
		for(int j =0;j<9;j++){
			for(int x=0;x<3;x++){
				nyblandning[j][x]=deck[a];
				a++;
			}
		}

		double y=1.60;
		double x=0.25;
		for(int f=0;f<9;f++){
			x=0.25;
			for(int g=0;g<3;g++){
				StdDraw.picture(x,y,nyblandning[f][g], 0.5, 1.4*0.5);
				x=x+0.7;
			}
			y=y-0.25;
		}


		System.out.println("Välj ett kort och skriv ett tal mellan 1 och 27?");
		int nummer=(StdIn.readInt()-1);
		int nummervald=nummer;

		rad=bastre(nummervald,rad);

		for(int z=0;z<3;z++){
			System.out.println("I vilken rad från 1 till 3 finner du ditt kort?");
			int valdrad = StdIn.readInt();

			for(int j=0; j<9;j++){
				String temp = nyblandning[j][valdrad-1];
				nyblandning[j][valdrad-1] = nyblandning[j][rad[z]];
				nyblandning[j][rad[z]] = temp;
			}


			StdDraw.clear();
			int m = 0;
			for(int j=0; j<3;j++){
				for(int l=0; l<9;l++){
					blandning[m] = nyblandning[l][j];
					m++;
				}
			}

			m = 0;
			for(int j=0; j<9;j++){
				for(int l=0; l<3;l++){
					nyblandning[j][l] = blandning[m];
					m++;
				}
			}

			y=1.60;
			x=0.25;
			for(int q=0;q<9;q++){
			x=0.25;
			for(int h=0;h<3;h++){
				StdDraw.picture(x,y,nyblandning[q][h], 0.5, 1.4*0.5);
				x=x+0.7;
			}
			y=y-0.25;
			}

		x=0.3;
		y=1.4;
		}

		StdDraw.clear();

		for(int t=0;t<(nummer+1);t++){
		StdDraw.picture(x,y,blandning[t],0.6,1.1);
		StdDraw.pause(700);
		x=x+0.03;
		y=y-0.03;
		}

    }

}
