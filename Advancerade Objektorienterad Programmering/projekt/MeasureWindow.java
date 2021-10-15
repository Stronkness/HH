package projekt;


/**
 * <h1> MeasureWindow</h1>
 * <p>
 * Creates a window to insert length and width of how the furniture will look like
 * </p>
 * 
 * @param width The width of the furniture
 * @param length The length of the furniture
 * @param tflength The JTextField for the length part
 * @param tfwidth The JTextField for the width part
 * @param frame The frame used for the part where you insert length and width
 * @param panel The panel used to the frame
 * @param room The room used in the GUI
 * @param color The color of the furniture
 * @param obj The current furniture selected
 * @param observer The observer to update the room and shoppinglist
 * 
 * @author André Frisk & Fredrik Kortetjärvi
 * @version 1.0
 * @since  2020-05-11
 */


import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MeasureWindow  {
	int width, length;
	JTextField tflength;
	JTextField tfwidth;
	JFrame frame = new JFrame();
	JPanel panel;
	Color color;
	ObjectList obj;

	/**
	 * <h3> Constructor MeasureWindow </h3>
	 * <p> Settings for the frame which shows up after trying to create a new furniture </p>
	 * @param name The title of the frame
	 */
	public MeasureWindow(String name) {
		
		//Frame settings
		frame.setTitle(name);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setPreferredSize(new Dimension(180,100));
		panel = new JPanel();
		
		//JTextField settings
		tflength = new JTextField("Set Length");
		tflength.addMouseListener(eraseAll());
		tflength.setEditable(true);
		tfwidth = new JTextField("Set Width");
		tfwidth.addMouseListener(eraseAll());
		tfwidth.setEditable(true);
		
		//JButton
		JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Cancel");
		
		ok.addActionListener(actionok());
		cancel.addActionListener(actioncancel());
		
		//Add everything to the panel and frame
		panel.add(tflength);
		panel.add(tfwidth);
		panel.add(ok);
		panel.add(cancel);
		frame.add(panel);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.pack();

	}
	
	/**
	 * <h3> eraseAll </h3>
	 * <p> When clicked in the length or width field the whole field will be selected </p>
	 * @return MouseListener so that the mouse can do these actions
	 */
	private MouseListener eraseAll() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				tflength.selectAll();
				tfwidth.selectAll();
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		};
	}

	/**
	 * <h3> actionok </h3>
	 * <p> Used when the 'Ok' button is pressed </p>
	 * @return ActionListener to be used when 'Ok' is pressed
	 */
	private ActionListener actionok() {
		return e -> {
			String text = tflength.getText();
			String text2 = tfwidth.getText();

			if(text.matches("[0-9]+") && text2.matches("[0-9]+")) { //Checks if it contains only numbers

			int l = Integer.parseInt(text);
			int w = Integer.parseInt(text2);

				if(w == 0 || l == 0 || w > 10 || l > 10) { //Checks if its not over 10 which is the limit
					errorwindow();
				}else {
					frame.setVisible(false);
					obj.setHeight(l);
					obj.setWidth(w);
					obj.setColor(color);
					RoomDesignerGUI.observer.setobj(obj);


				}
			}else {
				errorwindow();
			}
		};
	}
	
	/**
	 * <h3> actioncancel </h3>
	 * <p> Used when the 'Cancel' button is pressed. Sets width and length to zero and closes the window. </p>
	 * @return ActionListener to be used when Cancel is pressed
	 */
	private ActionListener actioncancel() {
		return e -> {
			setLength(0);
			setWidth(0);
			frame.setVisible(false);


		};
	}
	
	/**
	 * <h3> setColor </h3>
	 * <p> Sets the color to the global variable used to the ObjectList object </p>
	 * @param color The color to be used later in the object
	 */
	public void setColor(Color color){
		this.color=color;
	}
	
	/**
	 * <h3> setLength </h3>
	 * <p> Sets the length written in the JTextField </p>
	 * @param l The length written in the JTextField
	 */
	private void setLength(int l) {
		length = l;
	}
	
	/**
	 * <h3> setWidth </h3>
	 * <p> Sets the width written in the JTextField </p>
	 * @param w The width written in the JTextField
	 */
	private void setWidth(int w) {
		width = w;
	}
	
	/**
	 * <h3> errorWindow </h3>
	 * <p> Shows a error message if the conditions in actionok() isn't met </p>
	 */
	private void errorwindow() {
		JOptionPane.showMessageDialog(frame, "Length & width cannot contain zeros, letters or be over 10 blocks. Try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * <h3>exist</h3>
	 * <p>check what state window is in</p>
	 * @return return false if window exist
	 */
	public Boolean exist(){
		return frame.isVisible();
	}

	/**
	 * <h3> object </h3>
	 * <p> Sets the newly selected object to current object </p>
	 * @param obj The object to be selected
	 */
	public void object(ObjectList obj){
		this.obj = obj;
	}
}
