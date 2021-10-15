package projekt;


/**
 * <h1>ButtonsList</h1>
 * <p>add all buttons to one List</p>
 * @params list
 * @parmas  os hold what os you using
 * @parmas adress is the home adress to the project
 * @author Andre Frisk & Fredrik Kortetjärvi
 */

import java.awt.*;
import java.util.ArrayList;

public class ButtonsList {

    ArrayList<Buttons> list;
    String os = System.getProperty("os.name");
    String adress;

    /**
     * <h3>ButtonsList</h3>
     * <p>set up the adress for the system and make an List</p>
     */
    public ButtonsList( ){
        list = new ArrayList<>();
        //look for os for path
        if(os.equals("Windows 10")) {
            adress="C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\projekt\\";
        }else{
            adress="/home/tipparn/Documents/Programming/advanced-object-oriented-programming/projekt/";
        }


    }

    /**
     * <h3>insertItem</h3>
     * <p>add buttons in the list</p>
     * @param name name on the button
     * @param adress name on the file
     * @param color color on the button
     */
    public void insertItem(String name, String adress, Color color) {
        Buttons temp = new Buttons(name,this.adress+adress, color);
        list.add(temp);
    }

    /**
     * <h3>getButtons</h3>
     * <p>return the List</p>
     * @return return the list
     */
    public ArrayList<Buttons> getButtons(){
        return list;
    }

}