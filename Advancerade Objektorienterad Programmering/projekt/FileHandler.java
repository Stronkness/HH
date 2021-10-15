package projekt;


/**
 * <h1>FileHandler.java</h1>
 * <p>
 * Takes in a file and reads from it to upload furnitures with location, color, width, length etc.
 * Used for Save and Load in menu.
 * </p>
 * 
 * @param file The file imported with the path in the library
 * @param scan Scanner used to go through the txt file
 * @param list ArrayList with all objects used in the room
 * @param asbPath Pathing used to locate the file to the scanner
 * @param room The room used in the GUI
 * @param observer The observer used to update the list and room
 * 
 * @author André Frisk & Fredrik Kortetjärvi
 * @version 1.0
 * @since  2020-05-11
 */

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
	BufferedWriter file;
    Scanner scan;
    ArrayList<ObjectList> list;
    String absPath;
    EditRoom room;
    objectmodel observer;
    
    /**
     * <h3> Constructor FileHandler </h3>
     * <p> Inserts the room and observer to through the FileHandler insert furnitures to the room and the shoppinglist </p>
     * @param room The room used in the GUI
     * @param observer The observer used to update the list and room
     */
    FileHandler(EditRoom room,objectmodel observer){
    	this.observer=observer;
    	this.room = room;
    	getList(room.objects);
    }
    FileHandler(){
    	getList(RoomDesignerGUI.room.objects);
    }
    
    /**
     * <h3> createFile </h3>
     * <p> Creates a file through the Save option in the menu with everything that the ObjectList contains </p>
     * @param path
     * @throws IOException If the input doesn't work properly a throws will happen
     */
    public void createFile(String path) throws IOException {
    	absPath = path;
    	String text = "";
    	try {
			file = new BufferedWriter(new FileWriter(absPath));
			for(int i = 0; i < list.size(); i++) { //Gets the different attributes of ObjectList and inserts it in a file to be used in Load later
				text += list.get(i).getName() + " ";
				text += list.get(i).getAdress() + " ";
				text += list.get(i).getColor().getRed() + " ";
				text += list.get(i).getColor().getGreen() + " ";
				text += list.get(i).getColor().getBlue() + " ";
				text += list.get(i).getHeight() + " ";
				text += list.get(i).getWidth() + " ";
				text += list.get(i).getxCord() + " ";
				text += list.get(i).getyCord() + " ";
				text += "\n";
			}
			System.out.println(text);
			file.write(text);
			file.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * <h3> readFile </h3>
     * <p> 
     * Gets the file from the filePath and uses the Scanner to get through each object 
     * to insert it with all the attributes into the room and shoppinglist 
     * </p>
     * @param filePath Pathing to the file
     */
	public void readFile(String filePath) {
    	absPath = filePath;
    	try {
			scan = new Scanner(new File(absPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	while(scan.hasNext()) { // Sets the different attributes of an ObjectList from the Scanner
    	RoomDesignerGUI.room.clearlist();
    	RoomDesignerGUI.observer.clearnum();
    	while(scan.hasNext()) {
    		String name = scan.next();
    		String adress = scan.next();
    		int red = Integer.parseInt(scan.next());
    		int green = Integer.parseInt(scan.next());
    		int blue = Integer.parseInt(scan.next());
    		Color color = new Color(red,green,blue);
    		ObjectList temp = new ObjectList(name,adress,color);
    		temp.setHeight(Integer.parseInt(scan.next()));
    		temp.setWidth(Integer.parseInt(scan.next()));
    		temp.setxCord(Integer.parseInt(scan.next()));
    		temp.setyCord(Integer.parseInt(scan.next()));
			RoomDesignerGUI.observer.setobj(temp);
    		RoomDesignerGUI.room.upobj();

    		
    	}
    	scan.close();
    	}
    }
	
	/**
	 * <h3> getList </h3>
	 * <p> Fetches the list to be used to the objects in FileHandler </p>
	 * @param list The list to be used in FileHandler
	 */
    public void getList(ArrayList<ObjectList> list) {
    	this.list=list;
    }
    
}
