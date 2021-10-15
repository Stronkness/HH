package projekt;

/**
 * <h1>panelShopping</h1>
 * <p>this class will make the shopping list panel</p>
 * @params object  list of all objects
 * @author Andre Frsik & Fredrik Kortetjärvi
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class panelShopping extends JPanel implements Observer {
    ArrayList<ObjectList> object;

    /**
     * <h3>panelShopping</h3>
     * <p>setup the panel</p>
     */
    panelShopping(){
        new JPanel();
        add(new JLabel("Shopping List:"));
        setSize(100,400);
        setPreferredSize(new Dimension(200,800));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        object = new ArrayList<>();
    }

    /**
     * <h3>addlabel</h3>
     * <p>add the labels to the panel</p>
     * @param label label to add
     */
    public void addlabel(JLabel label){
        add(label);

    }

    /**
     * <h3>addall</h3>
     * <p>add all the object to the panel</p>
     */
    public void addall(){
        removeAll();
        add(new JLabel("Shopping List:"));
        for(ObjectList temp:object){
            addlabel(temp.getLabel());
        }


    }

    /**
     * <h3>update</h3>
     * <p>update the @params object and refresh the screen</p>
     * @param object updated objectList
     */
    @Override
    public void update(ArrayList<ObjectList> object) {
        this.object = object;
        addall();
        RoomDesignerGUI.frame.setVisible(true);
    }
}
