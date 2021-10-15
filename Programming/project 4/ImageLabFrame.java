import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class ImageLabFrame extends JFrame implements ActionListener {

    private JMenuItem aboutItem;
    private JMenuItem quitItem;
    private JMenuItem openItem;
    private JMenuItem saveItem;

    private BufferedImage image;
    private JLabel lab;
    private JFileChooser chooser;
    private JSlider slider;
    private JPanel sliderPanel;
    private TitledBorder border;


    private Picture pic1;  // Before filtering the images are swapped.
    private Picture pic2;  // Displayed image always stored in pic2


    private ScalableFilter currentFilter;

    public ImageLabFrame() {

        // Set up menus
        JMenuBar menuBar = new JMenuBar();

        JMenu imageLabMenu = new JMenu("imageLab");
        menuBar.add(imageLabMenu);

        aboutItem = new JMenuItem("About imageLab");
        imageLabMenu.add(aboutItem);
        quitItem = new JMenuItem("Quit");
        imageLabMenu.add(quitItem);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        openItem = new JMenuItem("Open File...");
        fileMenu.add(openItem);
        saveItem = new JMenuItem("Save As...");
        fileMenu.add(saveItem);


	JMenu imageMenu = new JMenu("Images");
	menuBar.add(imageMenu);
	addPictureGenerator(imageMenu, new Stripe());


        JMenu filterMenu = new JMenu("Filters");
        menuBar.add(filterMenu);
        addFilter(filterMenu,new BWFilter());
        filterMenu.addSeparator();
        addScalableFilter(filterMenu,new SwirlFilter());
        filterMenu.addSeparator();
        addFilter(filterMenu,new Red());
        addFilter(filterMenu,new Green());
        addFilter(filterMenu,new Blue());
        filterMenu.addSeparator();
        addFilter(filterMenu,new FlipX());
        addFilter(filterMenu,new FlipY());
        filterMenu.addSeparator();
        addFilter(filterMenu,new Glass());
        addScalableFilter(filterMenu, new BrightnessFilter());
        addFilter(filterMenu, new SharpenFilter());

        // Listeners for filters are added in addScalableFilter
        aboutItem.addActionListener(this);
        quitItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);

        // Set up rest of GUI

	lab = initialImage().getJLabel();

        slider = new JSlider(0,100);
        sliderPanel = new JPanel();
        sliderPanel.add(slider);
        sliderPanel.setVisible(false);
        Border b1 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        border = BorderFactory.createTitledBorder(b1,"");
        sliderPanel.setBorder(border);
        slider.addChangeListener( new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    double scale = (slider.getValue()-50)/50.0;
                    currentFilter.apply(pic1,pic2,scale);
                    lab.setIcon(pic2.getJLabel().getIcon());
                    repaint();
		}
            });

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);
        panel.add(lab,BorderLayout.NORTH);
        panel.add(sliderPanel,BorderLayout.SOUTH);
        setJMenuBar(menuBar);
        pack();
        setTitle("imageLab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prepare file chooser
        chooser = new JFileChooser(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
        chooser.setFileFilter(filter);
    }

    private Picture initialImage() {
        pic2 = new Picture(500,600);
        pic1 = new Picture(500,600);

        for (int x=0; x<pic2.width(); x++)
            for (int y=0; y<pic2.height(); y++) {
                double dist = 1.0-Math.sqrt((x-300)*(x-300) + (y-200)*(y-200))/500;
                int red = (int) (dist<0.5 ? 0 :
				 Math.min(Math.pow(dist,0.4) +
					  Math.pow(dist-0.5,0.1),1.0) * 255);
                int green = (int)(dist*255);
                int blue =  0;
		pic2.set(x,y,new Color(red,green,blue));
            }
        return pic2;
   }


    public void addPictureGenerator(JMenu menu, final PictureGenerator gen) {
        JMenuItem item = new JMenuItem (gen.getMenuName());
        menu.add(item);
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
		    pic2 = gen.picture();
		    pic1 = new Picture(pic2.width(),pic2.height());
		    lab.setIcon(pic2.getJLabel().getIcon());
                    sliderPanel.setVisible(false);
                    pack();
                    repaint();

                }
            });
    }

    public void addFilter (JMenu menu, final ImageFilter f) {
        JMenuItem item = new JMenuItem (f.getMenuName());
        menu.add(item);
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    swapImages();
                    f.apply(pic1,pic2);
		    lab.setIcon(pic2.getJLabel().getIcon());
                    sliderPanel.setVisible(false);
                    pack();
                    repaint();

                }
            });
    }

     public void addScalableFilter (JMenu menu, final ScalableFilter f) {
        JMenuItem item = new JMenuItem (f.getMenuName());
        menu.add(item);
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    swapImages();
                    currentFilter = f;
                    slider.setValue(50);
                    sliderPanel.setVisible(true);
                    border.setTitle(f.getMenuName());
                    pack();
                    repaint();
                }
            });
    }

    private void swapImages() {
        Picture tmp = pic1;
        pic1 = pic2;
        pic2 = tmp;
    }

    // Handler for imageLab and File menu

    public void actionPerformed(ActionEvent e) {
        String cmd = (e.getActionCommand());

        if (cmd.equals(aboutItem.getText()))
            JOptionPane.showMessageDialog(this,"Simple Image Program for DB2004\nversion 0.1\nThanks to BvS",
                                          "About imageLab", JOptionPane.INFORMATION_MESSAGE);

        else if (cmd.equals(quitItem.getText()))
            System.exit(0);

        else if (cmd.equals(openItem.getText())) {
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    pic2 = new Picture(chooser.getSelectedFile().getName());
		    pic1 = new Picture(pic2.width(),pic2.height());
		    lab.setIcon(pic2.getJLabel().getIcon());
                    sliderPanel.setVisible(false);
                    pack();
                    repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,"Could not open " + chooser.getSelectedFile().getName()
                                                  + "\n" + ex.getMessage(),
                                                  "Open Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else if (cmd.equals(saveItem.getText())) {
            int returnVal = chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    pic2.save(chooser.getSelectedFile().getName());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,"Could not write " + chooser.getSelectedFile().getName()
                                                  + "\n" + ex.getMessage(),
                                                  "Save Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
