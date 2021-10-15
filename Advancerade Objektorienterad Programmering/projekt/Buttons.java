package projekt;

/** <h1>Buttons</h1>
 * <p>This class is for manage all the buttons in the application</p>
 * @params ButtonName  name for the button
 * @params adress adress to the image
 * @params mw the window to type in the measurement
 * @params color color on the button
 * @params firsttime tell the program if its on already
 * @author Andre Frisk & Fredrik Kortetjärvi
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Buttons extends JButton{
    String buttonName;
    String adress;
    MeasureWindow mw;
    Color color;
    Boolean firsttime = true;
    /**
     * <h3>Buttons</h3>
     * <p>Create one button</p>
     * @param name name on button
     * @param adress full adress to image
     * @param color buttons color
     */
    Buttons(String name, String adress, Color color) {
        new JButton();
        buttonName = name;
        this.adress=adress;
        this.color = color;
        setImage(adress);
        addActionListener(action());
        setToolTipText(buttonName);
        setBackground(color);
    }

    /**
     * <h3>setImage</h3>
     * <p>set the image on the button</p>
     * @param adress adress to the image
     */
    private void setImage(String adress) {
        try {
            Image image = ImageIO.read(new File(adress));
            setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * <h3>action</h3>
     * <p>create a object when pressed</p>
     * @return will return the Actionlistener
     */
    private ActionListener action() {
        return arg0 -> {
            if(firsttime || !mw.exist()) {//check if firttime or the measurewindow exist
                mw = new MeasureWindow(buttonName);
                ObjectList temp = new ObjectList(buttonName,adress,color);//create the object
                mw.object(temp);
                mw.setColor(color);
                RoomDesignerGUI.frame.setVisible(true);//update frame.
                firsttime = false;

            }
        };
    }
}