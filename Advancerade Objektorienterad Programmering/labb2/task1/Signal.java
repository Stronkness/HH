package labb2.task1;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Signal {
	private double amplitude;
	private final int SAMPLING = 1000;
	private ArrayList<SignalObserver> myObservers;
	private DefaultSampler samp = new DefaultSampler(); 

	public void addObserver(SignalObserver s) {
		myObservers.add(s);
	}

	public Signal() {
		myObservers = new ArrayList<SignalObserver>();
		Timer t = new Timer(SAMPLING, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextValue(samp.read());
			}
		});
		t.start();
	}
	
	private void nextValue(double x) {
		amplitude = x;
		for (SignalObserver so : myObservers) {
			so.updateSignal(amplitude);
		}		
	}
}