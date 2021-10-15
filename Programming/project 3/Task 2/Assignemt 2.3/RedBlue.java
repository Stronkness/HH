//Omar Alfakir and Ahmad Alsalehy som har testat och programmerat tilsammans.
import java.awt.Color;
public class RedBlue{
    public static void main(String[] args){
		//You should type the path of the file "Purple". 
        String dir = "C:\\Users\\omarf\\Programmering\\Projekt_3\\purple-america-data\\purple\\"; 
        String F =(dir + args[0] + ".txt");
        String z = (dir + args[0] + args[1] + ".txt");
        In O = new In(z);
        O.readLine();
        

        In file = new In(F);
        double x0 = file.readDouble();
        double y0 = file.readDouble();
        double x1 = file.readDouble();
        double y1 = file.readDouble();
        StdDraw.setXscale(x0,x1);
        StdDraw.setYscale(y0,y1);
        int c = file.readInt();
        Polygon[] maps = new Polygon[c];
        String Pstate = "";
        VoteColour newColor = null;
        for (int j= 0; j< c; j++){
            file.readLine();
            file.readLine();
            String state = file.readLine();
            
            String Country = file.readLine();
            maps[j] = new Polygon(file);
            
            if(!state.equals(Pstate)){
            VoteColour temp = new VoteColour(O);
            if(temp.getName().equals("Alaska") || temp.getName().equals("Hawaii"))
            temp = new VoteColour(O);
            newColor=temp;
            Pstate = state;
            System.out.println(state + " " + temp.getName() + " " + newColor.getColor().toString());

            }
            maps[j].drawC(newColor.getColor()); 
            
        
        
        }

    }

}