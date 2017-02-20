package ua.dan1sh;

import java.awt.*;

/**
 * Created by a.danishevskiy on 13.02.2017.
 */
public class GameBack {
    //Fields
    private Color color;

    //Construcror
    public GameBack(){
        color = Color.BLUE;
    }
    //Functions
    public void update(){

    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}
