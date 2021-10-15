package projekt;


/**
 * <h1> ObjectList.java</h1>
 * <p>
 * The Object for the furniture which contains every attribute it needs. For example color, name, the coordinates etc.
 * </p>
 * 
 * @param name The name of the furniture
 * @param adress The pathing to the image of the furniture
 * @param color The color used to the furniture
 * @param width The width of the furniture
 * @param length The length of the furniture
 * @param xCord The X-Coordinate of the furniture (where the mouse cursor places the furniture)
 * @param yCord The Y-Coordinate of the furniture (where the mouse cursor places the furniture)
 * @param label The label used in Shoppinglist
 * @param number The position of the object in the shoppinglist from up to down
 * @param observer The observer used to update the room and the shoppinglist
 * 
 * @author André Frisk & Fredrik Kortetjärvi
 * @version 1.0
 * @since  2020-05-11
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ObjectList {
    private String name;
    private String adress;
    private Color color;
    private int width;
    private int height;
    private int xCord;
    private int yCord;
    private JLabel label;
    private int number;
    
    /**
     * <h3> Constructor ObjectList </h3>
     * <p> Inserts the name of the furniture, the adress to the path and color to initialize the furniture. Adds to the room </p>
     * @param name The name of the furniture
     * @param adress The path to the image of the furniture
     * @param color The color used to the furniture
     */
    ObjectList(String name, String adress, Color color){
        this.name = name;
        this.adress = adress;
        this.color = color;
        label = new JLabel(name);
        label.setForeground(color);
        label.setText(name);
        label.addMouseListener(mouseAction());
    }
    
    /**
     * <h3> setNumber </h3> 
     * <p> Sets the number of position in the list </p>
     * @param number The number placed to the list
     */
    public void setnumber(int number){this.number=number;}
    
    /**
     * <h3> getNumber </h3> 
     * <p> Gets the number of position in the list </p>
     * @return Gets the number
     */
    public int getnumber(){return number;}
    
    /**
     * <h3> setName </h3> 
     * <p> Sets the name of the furniture </p>
     * @param name The chosen name of the furniture
     */
    public void setName(String name) {this.name = name;}

    /**
     * <h3> setAdress </h3> 
     * <p> Sets the pathing to the image </p>
     * @param adress The new adress to the image
     */
    public void setAdress(String adress) {this.adress = adress;}

    /**
     * <h3> setColor </h3> 
     * <p> Sets the color to the furniture </p>
     * @param color The chosen color to change to the furniture
     */
    public void setColor(Color color) {this.color = color;}

    /**
     * <h3> setWidth </h3> 
     * <p> Sets the width of the furniture </p>
     * @param width The width to set
     */
    public void setWidth(int width) {this.width = width;}

    /**
     * <h3> setHeight </h3> 
     * <p> Sets the length of the furniture </p>
     * @param height The length to set
     */
    public void setHeight(int height) {this.height = height;}

    /**
     * <h3> setxCord </h3> 
     * <p> Sets the X-Coordinate of the furniture </p>
     * @param xCord The X-Coordinate to be set
     */
    public void setxCord(int xCord) {this.xCord = xCord;}

    /**
     * <h3> setyCord </h3> 
     * <p> Sets the Y-Coordinate of the furniture </p>
     * @param yCord The Y-Coordinate to be set
     */
    public void setyCord(int yCord) {this.yCord = yCord;}

    /**
     * <h3> getName </h3>
     * <p> Gets the name of the furniture </p>
     * @return The name of the furniture
     */
    public String getName() {return name;}

    /**
     * <h3> getAdress </h3>
     * <p> Gets the pathing to the image to the furniture </p>
     * @return The path to the image
     */
    public String getAdress() {return adress;}

    /**
     * <h3> getColor </h3>
     * <p> Get the color of the furniture </p>
     * @return The color of the furniture
     */
    public Color getColor() {return color;}

    /**
     * <h3> getWidth </h3>
     * <p> Gets the width of the furniture </p>
     * @return The width of the furniture
     */
    public int getWidth() {return width;}

    /**
     * <h3> getHeight </h3>
     * <p> Gets the length of the furniture </p>
     * @return The length of the furniture
     */
    public int getHeight() {return height;}

    /**
     * <h3> getxCord </h3>
     * <p> Gets the X-Coordinate of the furniture </p>
     * @return The X-Coordinate of the furniture
     */
    public int getxCord() {return xCord;}

    /**
     * <h3> getyCord </h3>
     * <p> Gets the Y-Coordinate of the furniture </p>
     * @return The Y-Coordinate of the furniture
     */
    public int getyCord() {return yCord;}

    /**
     * <h3> getLabel </h3>
     * <p> Gets the label of the object to the shoppinglist. Each labels is a furiture </p>
     * @return The label to the specific furniture
     */
    public JLabel getLabel(){ return label; }
    
    /**
     * <h3> mouseAction </h3>
     * <p> Methods to be added on the object (shoppinglist in this case) to remove and select the furniture </p>
     * @return The MouseListener so that it can be used while clicking
     */
    private MouseListener mouseAction() {
        return new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){ //If left mousebutton is pressed select the object to it can be moved around
                    RoomDesignerGUI.room.moveobj(number);
                }else if(SwingUtilities.isRightMouseButton(e)){ //If right mousebutton i pressed select the object will be removed from shoppinglist and the room
                    RoomDesignerGUI.observer.removeobj(number);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };

    }
}
