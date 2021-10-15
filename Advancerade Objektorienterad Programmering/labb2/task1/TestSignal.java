package labb2.task1;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestSignal {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		JButton b = new JButton("TestButton");
		Signal theSignal = new Signal();
		theSignal.addObserver(new SignalObserver() {
			public void updateSignal(double x) {
				printStars((int)x);
			}
		});
		Signal theSignal1 = new Signal();
		theSignal.addObserver(new StarObserver());
		theSignal.addObserver(new SignalWindow());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	private static void printStars(int x) {
		for (int i = 0; i < x; i++)
			System.out.print("*");
		System.out.println();		
	}

}
