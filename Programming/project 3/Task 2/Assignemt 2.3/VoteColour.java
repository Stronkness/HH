//Omar Alfakir and Ahmad Alsalehy som har testat och programmerat tilsammans.
import java.awt.Color;
public class VoteColour{
    private int rep, dem, Other, r, g, b;
    private String name, Line;

    
    public VoteColour(In in){
        
    Line = in.readLine();
    System.out.println(Line);
    
    String[] comm = Line.split(",");
    name = comm[0];
    dem = Integer.parseInt(comm[1]);
    rep = Integer.parseInt(comm[2]);
    Other = Integer.parseInt(comm[3]);
    r = (int) (((dem*1.00)/(dem + rep + Other))*255);
    b = (int) (((rep*1.00)/(dem + rep + Other))*255);
    g = (int) (((Other*1.00)/(dem + rep + Other))*255); 
    }
    
    
    public Color getColor(){
       return new Color( r, g,  b);
    }

    public String getName(){
        return name;
    }
    
}
