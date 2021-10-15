package projekt;

/**
 * <h1>EditRoom</h1>
 * <p>create room to insert furniture inside a room</p>
 * @params objects hold all objects
 * @params obj is the active object
 * @params count hold count
 * @params cubesize is the size of the cubes
 * @author Andre Frisk & Fredrik Kortetjärvi
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.*;

class EditRoom extends JPanel implements Observer {

    //ArrayList<Point> point;
    ArrayList<ObjectList> objects;
    ObjectList obj;
    int count=0;
    int Cubesize=20;

    /**
     * <h3>EditRoom</h3>
     * <p>set up the mouse listener and panel</p>
     */
    public EditRoom() {
        //point = new ArrayList<>();
        objects = new ArrayList<>();
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(checkRoom(e.getX() / Cubesize - 1,e.getY() / Cubesize - 1)) {//check for not go outside the room walls
                	if(checkDoor(e.getX() / Cubesize - 1,e.getY() / Cubesize - 1)) {// check for clearance for the door
                		if(checkWindows(e.getX() / Cubesize - 1,e.getY() / Cubesize - 1)) { // check window clearance

                				fillcell(e.getX() / Cubesize - 1, e.getY() / Cubesize - 1);//fill the box that was clicked

                		}
                	}
                }
            }
        });


    }

    /**
     * <h3>getPreferredSize</h3>
     * <p>get preferred size for the panel</p>
     * @return return Dimension of the preferred size
     */
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    /**
     * <h3>paintComponent</h3>
     * <p>will paint all objects in our List</p>
     * @param g is the graphics component
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        count=0;
        for (ObjectList fill :objects){// loop the objects to paint all of them.


                for (int i = 1; i <= objects.get(count).getWidth(); i++) {//fill out the width and height
                    for (int j = 1; j <= objects.get(count).getHeight(); j++) {
                        int x = Cubesize * i + (fill.getxCord() * Cubesize);
                        int y = Cubesize * j + (fill.getyCord() * Cubesize);
                        g.setColor(objects.get(count).getColor());
                        g.fillRect(x, y, Cubesize, Cubesize);
                    }
                }
            count++;
        }
        
        g.setColor(Color.BLACK);//paint all the lines in the panel
        Dimension size = getSize();
        g.drawRect(Cubesize,Cubesize,size.width,size.height);

        for(int i=Cubesize; i<=size.width; i+=Cubesize){
            g.drawLine(i,0,i,size.height);
        }
        for(int i=Cubesize; i<=size.height; i+=Cubesize){
            g.drawLine(0,i,size.width,i);
        }      
      
      //HARDCODE FOR ROOM
        createBlackRoom(g);// create the room
        
    }

    /**
     * <h3>fillcell</h3>
     * <p>fill the cell with x and y cords </p>
     * @param x x coordinate to fill
     * @param y y coordinate  to fill
     */
    public void fillcell(int x,int y){
    	//if(checkRoom(x+obj.getWidth()-1,y+obj.getHeight()-1)){
            obj.setyCord(y);
            obj.setxCord(x);
            repaint();
       // }

    }

    /**
     * <h3>clearlist</h3>
     * <p>clear the list</p>
     */
    public void clearlist() {
    	objects.clear();
    }

    /**
     * <h3>upobj</h3>
     * <p>set the last object to the active object</p>
     */
    public void upobj() {
        if(objects.get(objects.size()-1)!= null){
            obj = objects.get(objects.size() - 1);
        }
    	repaint();
    }

    /**
     * <h3>moveobj</h3>
     * <p>set a object to active </p>
     * @param number the index of the object to activate
     */
    public void moveobj(int number){
        this.obj=objects.get(number);
    }
    
    /**
     * <h3> createBlackRoom </h3>
     * <p> 
     * Used to draw the room used in the program. 
     * Use the runnable program to more understand which wall is which with the comments below.
     * </p>
     * @param g The paint object from Graphics to paint out the walls, door and windows of the room
     */
    private void createBlackRoom(Graphics g) {
    	//The first upper part of the room (the line)
        for(int n = 9; n < 42; n++) {  
      	  for(int m = 6; m < 7; m++) {
      		  int x = Cubesize * n;
      		  int y = Cubesize * m;
      		  g.fillRect(x, y, Cubesize, Cubesize);
      	  }
        }
        //The most right part of the room with windows
        for(int n = 41; n < 42; n++) {  
        	  for(int m = 6; m < 29; m++) {
        		  //Windows
        		  if((m > 10 && m < 15) || (m > 18 && m < 24)) {
            		  int x = Cubesize * n;
            		  int y = Cubesize * m;
            		  g.setColor(new Color(224,255,255));
            		  g.fillRect(x, y, Cubesize, Cubesize);
            		  
        		  }else {
        			  g.setColor(Color.BLACK);
        		  int x = Cubesize * n;
        		  int y = Cubesize * m;
        		  g.fillRect(x, y, Cubesize, Cubesize);  
        		  }
          }
        }
        //The most lower part of the room
        for(int n = 41; n > 21; n--) {  
      	  for(int m = 28; m < 29; m++) {
      		  int x = Cubesize * n;
      		  int y = Cubesize * m;
      		  g.fillRect(x, y, Cubesize, Cubesize);  
      	  }
        }
        //The wall which goes up after the lower part of the room
        for(int n = 22; n < 23; n++) {  
      	  for(int m = 15; m < 29; m++) {
      		  int x = Cubesize * n;
      		  int y = Cubesize * m;
      		  g.fillRect(x, y, Cubesize, Cubesize);  
      	  }
        }
        //The wall which goes to the left to almost complete the whole room design (before the last wall with the door)
        for(int n = 22; n > 8; n--) {  
      	  for(int m = 15; m < 16; m++) {
      		  int x = Cubesize * n;
      		  int y = Cubesize * m;
      		  g.fillRect(x, y, Cubesize, Cubesize);  
      	  }
        }
        //The last wall which goes up to the first line drawn with an empty space for the door
        for(int n = 8; n < 9; n++) {  
      	  for(int m = 15; m > 5; m--) {
      		  if(m > 7 && m < 12) {
      			  //Make empty space for door
      		  }else {
      		  int x = Cubesize * n;
      		  int y = Cubesize * m;
      		  g.fillRect(x, y, Cubesize, Cubesize);  
      		  }
      	  }
        }
        //Door
        for(int n = 8; n > 4; n--) {  
          for(int m = 8; m < 9; m++) {
        		  int x = Cubesize * n;
        		  int y = Cubesize * m;
        		  g.fillRect(x, y, Cubesize, Cubesize);  
        	  }
          }
}

    /**
     * <h3>checkRoom</h3>
     * <p>check for the wall is collide</p>
     * @param x X coordinate
     * @param y Y coordinate
     * @return return true if its not collide
     */
    private Boolean checkRoom(int x, int y){
    	//Room dimensions
        if((7<x && x<40) && (5<y && y<27) ) {
            if(22>x && y>13){
            	errormsgRoom();
                return false;
            }
                return true;
        }
        errormsgRoom();
        return false;
    }

    /**
     * <h3>checkDoor/h3>
     * <p>check for the door clearance is collide</p>
     * @param x X coordinate
     * @param y Y coordinate
     * @return return true if its not collide
     */
    private Boolean checkDoor(int x, int y) {
    	if((y > 7 && y < 11) && (x > 6 && x < 11)) {
    		errormsgDoor();
    		return false;
    	}else {
    		return true;
    	}
    }
    /**
     * <h3>checkWindow</h3>
     * <p>check for the window clearance is collide</p>
     * @param x X coordinate
     * @param y Y coordinate
     * @return return true if its not collide
     */
    private Boolean checkWindows(int x, int y) {
    	int width = obj.getWidth();
    	if((y > 8 && y < 14) && ((x + width-2) > 36 && (x + width-2) < 39)) {
    		errormsgWindow();
    		return false;
    	}else if((y > 16 && y < 23) && ((x + width-2) > 36 && (x + width-2) < 39)) {
    		errormsgWindow();
    		return false;
    	}else {
    		return true;
    	}
    }

    /**
     * <h3>errorDoor</h3>
     * <p>error message popup if door collided </p>
     */
    private void errormsgDoor() {
    	JFrame frame = new JFrame();
    	JOptionPane.showMessageDialog(frame, "Cannot put furniture in front of the door that's in a 3x3 square in front of the door. Try again!", "DOOR ERROR", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * <h3>errorWindow</h3>
     * <p>error message popup if window collided </p>
     */
    private void errormsgWindow() {
    	JFrame frame = new JFrame();
    	JOptionPane.showMessageDialog(frame, "Cannot put furniture in front of the windows that's 2 squares in front of the windows. Try again!", "WINDOW ERROR", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * <h3>errorRoom</h3>
     * <p>error message popup if wall collided </p>
     */
    private void errormsgRoom() {
    	JFrame frame = new JFrame();
    	JOptionPane.showMessageDialog(frame, "Cannot put furniture outside of the room. Try again!", "ROOM ERROR", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * <h3>update</h3>
     * <p>update the @params object and update the current obj</p>
     * @param object updated objectList
     */
    @Override
    public void update(ArrayList<ObjectList> object) {
        this.objects = object;
        upobj();

    }
}