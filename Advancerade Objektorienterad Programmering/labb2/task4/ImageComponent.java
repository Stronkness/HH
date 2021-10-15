package labb2.task4;


import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageComponent extends JComponent {
    private ImageIcon imageIcon;
    private Image image;
    public ImageComponent(){
        this.setPreferredSize(new Dimension(800,400));
    }
    @Override
    public void paintComponent(Graphics g){
        if(imageIcon!= null){
            g.drawImage(image,0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight(),null);
        }
    }
    public void setImage(ImageIcon image){
            imageIcon = image;
            this.image = imageIcon.getImage();

    }
}
