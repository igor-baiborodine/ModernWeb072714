package com.farata.course.mwd.java8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingActionListener {

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private JFrame frame;

    public String getTitle() {
        return this.frame.getTitle();
    }

    public static void main(String[] args) {
        SwingActionListener app = new SwingActionListener();
        app.setFrame(new JFrame("Lambdas in Swing"));
        app.initUI();
        app.show();
        app.centreWindow(app.getFrame());
    }

    public void initUI() {
        JButton oldButton = new JButton("I'm an Old Java Button");
        JButton newButton = new JButton("I'm brand new Java Button");
        JTextArea myConsole = new JTextArea("Click a button");

        //Action Listener with anonymous inner class
        oldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                myConsole.setText("Processing Click with Anonymous Inner Class");
            }
        });

        // Action Listener has one callback method actionPerformed()with lambda expression
        // Implement it using lambdas
        newButton.addActionListener(e -> myConsole.setText("Processing Click with Lambda"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(oldButton, BorderLayout.NORTH);
        frame.add(newButton, BorderLayout.CENTER);
        frame.add(myConsole, BorderLayout.SOUTH);

        frame.setSize(300, 400);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

}
