//Omar Alfakir and Ahmad Alsalehy som har testat och programmerat tilsammans.
import java.awt.Color;
public class VoteColour{
    private int rep, dem, Other;
    private String name, Line;

    
    public VoteColour(In in){
    
    Line = in.readLine();
    System.out.println(Line);
    
    String[] comm = Line.split(",");
    name = comm[0];
    dem = Integer.parseInt(comm[1]);
    rep = Integer.parseInt(comm[2]);
    Other = Integer.parseInt(comm[3]);
     
    }
    
    
    public Color getColor(){
        if (dem > rep ) return StdDraw.RED;
        else if (rep > dem) return StdDraw.BLUE;
        
        return StdDraw.BLACK;     
           
        
    }

    public String getName(){
        return name;
    }
    
}
