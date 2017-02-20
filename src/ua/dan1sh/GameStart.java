package ua.dan1sh;

import javax.swing.*;

/**
 * Created by a.danishevskiy on 13.02.2017.
 */
public class GameStart {
    public static void main(String[] args){
        GamePanel panel = new GamePanel();
        JFrame startFrame = new JFrame("BubbleShooter");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        panel.start();


    }
}
