package labb2.task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;

public class ImagePresenter extends Presenter{
    ArrayList<ImageIcon> list = new ArrayList<ImageIcon>();
    ImageComponent im = new ImageComponent();
    public void showImage(){
        im.setImage(list.get(index));
        showText(text.getFile(index));

    }
    public void addImage(String x){
        list.add(new ImageIcon(x));
    }
    public ImageIcon getImage(int index){
        return list.get(index);
    }
    public int size(){
        return list.size();
    }
    @Override
    public Component createCenterComponent() {
        return im;
    }

    @Override
    public void northButtonPressed() {}

    @Override
    public void eastButtonPressed() {
        im.setImage(getImage(index%size()));
        textField.setText(text.getFile(index%size()));
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    @Override
    public void southButtonPressed() {}

    @Override
    public void westButtonPressed() {
        if(index < 0) {
            index = size()-1;
            im.setImage(getImage(index % size()));
            textField.setText(text.getFile(index % size()));
        }else{
        im.setImage(getImage(index%size()));
        textField.setText(text.getFile(index%size()));
        }
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == east){
            index++;
            eastButtonPressed();
        }else if(e.getSource()==west){
            index--;
            westButtonPressed();
        }

    }
    public static void main(String[] args) throws FileNotFoundException {
        ImagePresenter whole = new ImagePresenter();
        whole.addImage("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\radical.jpg"); 
		whole.addImage("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\qlimax.jpg"); 
		whole.addImage("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\defqon.jpg"); 
		whole.addImage("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\hej.jpg"); 
		whole.addImage("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\klocka.jpg"); 
		 
		whole.text.addFile(new File("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\radical.txt")); 
		whole.text.addFile(new File("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\qlimax.txt")); 
		whole.text.addFile(new File("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\defqon.txt")); 
		whole.text.addFile(new File("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\hej.txt")); 
		whole.text.addFile(new File("C:\\Users\\46734\\eclipse-workspace\\advanced-object-oriented-programming\\labb2\\task4\\klocka.txt")); 
       
		whole.display();
        whole.showImage();
    }
}
