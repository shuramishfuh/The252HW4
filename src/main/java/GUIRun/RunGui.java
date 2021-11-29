package GUIRun;

import Implementations.UserInterfaceGUI;

import java.awt.EventQueue;

public class RunGui implements Interfaces.HW4 {
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