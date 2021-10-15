public class DiagonalDots{
	public static void main(String[] cmdLn){

		int N = Integer.parseInt(cmdLn[0]); // N = kommandoradsargument
		
		StdDraw.setXscale(-N,N); // Skalan för X
		StdDraw.setYscale(-N,N); // Skalan för Y

		for(int i = -N; i<2*N+1; i++){
		StdDraw.filledCircle(i,i,0.5); // Cirkellinje börjar på positiva sidan
		StdDraw.filledCircle(-i,i,0.5); // Cirkellinje börjar på negativ sida (spegling)
		}
	}
}