public class BouncingBalls{

	public static void main(String[]cmdLn){ // Simulate the motion of a bouncing ball
		StdDraw.enableDoubleBuffering();
		int n = Integer.parseInt(cmdLn[0]);
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);


		double[] positionX = new double [n];
		double[] positionY = new double [n];
		double[] vx = new double [n];
		double[] vy = new double [n];


 for( int a = 0; a < n; a++){
		 positionX[a] = Math.random() * (2*(1-0.05))-1;
		 positionY[a] = Math.random() * (2*(1-0.05))-1;
		 vx[a] = ((Math.random() * (2)-1)/100);
		 vy[a] = ((Math.random() * (2)-1)/100);
		 }


		double radius = 0.05;


	while(true){ //update the balls position

		for(int a = 0; a < n; a++){
		if (Math.abs(positionX[a] + vx[a]) + radius > 1.0) vx[a] = - vx[a];


		if (Math.abs(positionY[a] + vy[a]) + radius > 1.0) vy[a] = - vy[a];



		positionX[a] = positionX[a] + vx[a];
		positionY[a] = positionY[a] + vy[a];


			StdDraw.filledCircle(positionX[a], positionY[a], radius);


}

		StdDraw.show();
		StdDraw.pause(100);
		StdDraw.clear();

			}
		}

	}
