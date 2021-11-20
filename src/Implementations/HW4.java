package Implementations;

import Interfaces.*;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HW4 implements Interfaces.HW4 {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterfaceGUI frame = new UserInterfaceGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
