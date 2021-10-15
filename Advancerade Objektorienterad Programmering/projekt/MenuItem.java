package projekt;


/**
 * <h1> MenuItem.java</h1>
 * <p>
 * Creates a menu with methods for Save and Load
 * </p>
 * 
 * @param fc Used to choose the file for save and load
 * @param file The file to be used
 * @param absPath The pathing for the file
 * @param hand The FileHandler to get called to createFile or readFile
 * 
 * @author André Frisk & Fredrik Kortetjärvi
 * @version 1.0
 * @since  2020-05-11
 */


import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuItem extends JMenuItem{
	private JFileChooser fc;
	private File file;
	private String absPath;
	private FileHandler hand;

	
	/**
	 * <h3> Constructor MenuItem </h3>
	 * @param name The name of the Item pressed in the menu (Save/Load)
	 */
	public MenuItem(String name){
		new JMenuItem();
		init(name,null);
		addActionListener(e -> {
			fc = new JFileChooser();
			if(name.equals("Save")) { //Save
				int returnVal = fc.showSaveDialog(new JPanel()); //The Save File frame
				if(returnVal == JFileChooser.APPROVE_OPTION) { //Checks if the frame works successfully go through
					file = fc.getSelectedFile();
					absPath = file.getAbsolutePath();
					hand = new FileHandler();
					try {
						hand.createFile(absPath); //Creates the file and saves everything in the room/shoppinglist in it
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}


			}else { //Load
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"txt file",  "txt");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(new JPanel()); //The Load File frame
				if(returnVal == JFileChooser.APPROVE_OPTION) { //Checks if the frame works successfully go through
					file = fc.getSelectedFile();
					absPath = file.getAbsolutePath();

					hand = new FileHandler();
					hand.readFile(absPath); //Reads the file and inserts everything in the room/shoppinglist
				}


			}
		});
	}
	
}