//Omar Alfakir and Ahmad Alsalehy som har testat och programmerat tilsammans.
import java.awt.Color;
 public class White{
    public static void main(String[] args){
        String F =(args[0] + ".txt");
        In file = new In(F);
        double x0 = file.readDouble();
        double y0 = file.readDouble();
        double x1 = file.readDouble();
        double y1 = file.readDouble();
        StdDraw.setXscale(x0,x1);
        StdDraw.setYscale(y0,y1);
        int c = file.readInt();
        for (int j= 0; j< c; j++){
            file.readLine();
            file.readLine();
            String state = file.readLine();
            String Country = file.readLine();
            Polygon map = new Polygon(file);
            map.draw(); 
        }
            
            
        
        
       
        
    }
    
 }    