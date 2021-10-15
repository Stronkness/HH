package projekt;


/**
 * <h1>RoomDesignerGUI.java</h1>
 * <p>
 * The GUI used in the program.
 * </p>
 * 
 * @param frame The main-frame used to the program
 * @param panel The panel used to the furniture list
 * @param panelShop The panel used to the shopping list
 * @param panelwithscroll The scollbar used to scroll in the furniture list
 * @param emptypanel The panel with Authors name
 * @param menubar The menubar used for Save & Load
 * @param menu The menu 
 * @param saveitem The Save button to the menubar
 * @param loaditem The Load button to the menubar
 * @param buttons The furniture list containing all buttons
 * @param room The room used in the GUI
 * @param observer The observer used to update the room and the shoppinglist
 * 
 * @author André Frisk & Fredrik Kortetjärvi
 * @version 1.0
 * @since  2020-05-11
 */


import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class RoomDesignerGUI {
	static JFrame frame;
	JPanel panel;
	panelShopping panelShop;
	JScrollPane panelwithscroll;
	JTextField emptypanel;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem saveitem, loaditem;
	ButtonsList buttons;
	static EditRoom room;
	static objectmodel observer;
	
	/**
	 * <h3> Constructor RoomDesignerGUI </h3>
	 * <p> Everything to the GUI is initialised and added to the frame here </p>
	 */
	public RoomDesignerGUI(){
		//Create component
		frame = new JFrame("Room Designer: Student Apartment");
		panel = new JPanel();
		room = new EditRoom();
		observer = new objectmodel();
		panelShop = new panelShopping();
		panelwithscroll = new JScrollPane(panel);
		menubar = new JMenuBar();
		menu = new JMenu("File");
		saveitem = new JMenuItem("Save");
		loaditem = new JMenuItem("Load");
		
		//Settings Frame
		emptypanel = new JTextField("Author: Fredrik Kortetjärvi & André Frisk");
		buttons = new ButtonsList();
		//settings Frame
		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		//Settings Panels
		panel.setPreferredSize(new Dimension(150,18*150));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//Settings Author panel
		emptypanel.setEditable(false);
		emptypanel.setSize(400, 40);
		emptypanel.setHorizontalAlignment(JTextField.CENTER);
		emptypanel.setBackground(Color.LIGHT_GRAY);
		emptypanel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		
		//Settings JScrollPane
		panelwithscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelwithscroll.setViewportBorder(new LineBorder(Color.BLACK));
		
		//Added observer
		observer.attach(room);
		observer.attach(panelShop);
		
		//Add to furniture panel
		buttons.insertItem("Chair","chair.png", new Color(65,105,225));
		buttons.insertItem("Armchair","armchair.png", Color.CYAN);
		buttons.insertItem("Dining_Table","diningtable.png", Color.DARK_GRAY);
		buttons.insertItem("Couch","couch.png", Color.GRAY);
		buttons.insertItem("Lamp","lamp.png", Color.GREEN);
		buttons.insertItem("Wardrobe","wardrobe.png", Color.LIGHT_GRAY);
		buttons.insertItem("Bed","bed.png", Color.MAGENTA);
		buttons.insertItem("Bedside_Table","bedsidetable.png", Color.ORANGE);
		buttons.insertItem("Round_Table","roundtable.png", Color.PINK);
		buttons.insertItem("Drawer","drawer.png", Color.RED);
		buttons.insertItem("Desktop_Chair","desktopchair.png", new Color(218,165,32)); //Goldenrod
		buttons.insertItem("Desktop","desktop.png", Color.YELLOW);
		buttons.insertItem("Tv_Table","tvbench.png", new Color(0,250,154)); //Mediumspringgreen
		buttons.insertItem("Big_Plant","plant.png", new Color(123,104,238)); //Purple
		buttons.insertItem("Bookshelf","bookshelf.png", new Color(255,250,205)); //Lemonchiffon
		buttons.insertItem("Double_Bed","beddouble.png", new Color(160,82,45)); //Brown
		buttons.insertItem("Dresser_Drawer","dresserdrawer.png", new Color(51,102,0)); //Dark-Green
		buttons.insertItem("Hanging_Rack","hangingrack.png", new Color(0,128,128)); //Teal
		ArrayList<Buttons> templist = buttons.getButtons();
		for(Buttons i:templist) {
			panel.add(i);
		}

		//add to the menu
		menu.add(new MenuItem("Save"));
		menu.add(new MenuItem("Load"));
		menubar.add(menu);

		//Add to frame
		frame.add(panelwithscroll, BorderLayout.EAST);
		frame.add(panelShop, BorderLayout.WEST);
		frame.add(room, BorderLayout.CENTER);
		frame.add(emptypanel, BorderLayout.SOUTH);
		frame.setJMenuBar(menubar);


		//Turn on frame
		frame.validate();
		frame.setVisible(true);
	}
}
