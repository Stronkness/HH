public class Animering{
	public static void main(String[] args){ 
	
		// Skalan för fönstret som bollen kommer vara i
		StdDraw.setXscale(0, 10);
		StdDraw.setYscale(0, 10);
		StdDraw.enableDoubleBuffering();
		
		// Hastighet och position för bollen
		Double xspeed = (0.1);
		Double yspeed = (0.2);
		Double xposition = (3.0);
		Double yposition = (2.0);
		
			// Loop för att rita ut bollen samt ta bort den innan nästa ritas ut
			while(true){
				StdDraw.clear();
				
				if(xposition < 0 || xposition > 10){
					xspeed = -xspeed;}
					
						if(yposition < 0 || yposition > 10){
							yspeed = -yspeed;}
				
				xposition = xposition + xspeed;
				yposition = yposition + yspeed;
				
				// Ritar ut ny boll efter StdDraw.clear()
				StdDraw.filledCircle(xposition, yposition, 0.5);
				StdDraw.show();
				StdDraw.pause(10);
			}
	}
}