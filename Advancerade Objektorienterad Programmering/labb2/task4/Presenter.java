package labb2.task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Presenter implements ActionListener {
    JFrame frame;
    JButton east,west;
    JTextField textField;
    JComponent component;
    JPanel panel;
    textComponent text = new textComponent();
    int index = 0;

    public void display(){
        //create objects

        frame = new JFrame("Image viewer");
        west = new JButton("Left");
        east = new JButton("Right");
        textField = new JTextField(null,35);
        panel = new JPanel();
        component = (JComponent) createCenterComponent();

        //settings frame
        frame.setSize(900,500);
        frame.setLayout(new GridBagLayout());
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //settings buttons
        west.addActionListener(this);
        east.addActionListener(this);
        //settings textField
        textField.setEditable(false);

        //text
        GridBagConstraints grid = new GridBagConstraints();

        grid.fill =GridBagConstraints.HORIZONTAL;
        grid.gridwidth = 4;
        grid.gridx = 1;
        grid.gridy = 0;
        frame.add(textField, grid);

        grid.fill =GridBagConstraints.HORIZONTAL;
        grid.gridwidth = 2;
        grid.gridx = 0;
        grid.gridy = 1;
        frame.add(component, grid);

        grid.fill =GridBagConstraints.HORIZONTAL;
        grid.gridwidth = 4;
        grid.gridx = 0;
        grid.gridy = 2;
        panel.add(west, grid);
        panel.add(east, grid);
        frame.add(panel, grid);


        frame.setVisible(true);
        frame.pack();
    }
    public void showText(String text){
        textField.setText(text);
        textField.repaint();
    }
   public abstract Component createCenterComponent();
   public abstract void northButtonPressed();
   public abstract void eastButtonPressed();
   public abstract void southButtonPressed();
   public abstract void westButtonPressed();


}
