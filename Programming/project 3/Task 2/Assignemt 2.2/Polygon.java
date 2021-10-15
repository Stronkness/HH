//Omar Alfakir and Ahmad Alsalehy som har testat och programmerat tilsammans.
import java.awt.Color;
public class Polygon{
    private  int NoCo; //Coordinate Numbers
    private double[] x, y;
    private int NoRe; // Region Number
    

    public Polygon(In in){
        NoCo = in.readInt();
        x = new double[NoCo];
        y = new double[NoCo];
        for (int i = 0; i < NoCo; i++){
        x[i] = in.readDouble();
        y[i] = in.readDouble();
        }
            
         
        
    }

    public void draw () {
        StdDraw.filledPolygon( x,  y);
        
    }
    public void drawC (Color c) {
        StdDraw.setPenColor(c);
        StdDraw.filledPolygon( x,  y);
        
    }
    
}
