package ua.dan1sh;

import java.awt.*;

/**
 * Created by Андрей on 17.02.2017.
 */
public class Menu {
    //Fields
    private int buttonWidth, buttonHeight;
    private Color color;
    private String s;
    private int transp = 0;
    //Constructor
    public Menu(){
        buttonWidth = 120;
        buttonHeight = 60;

        color = Color.WHITE;
        s = " PLAY ";
    }
    //Methods
    public void update(){
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2 &&
                GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2 &&
                GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2 &&
                GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2){
            transp = 60;
            if (GamePanel.leftMouse){
                GamePanel.state = GamePanel.STATES.PLAY;
            }
        }else{
            transp = 0;
        }
    }

    public int getButtonHeight() {
        return buttonHeight;
    }

    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
        graphics2D.setColor(new Color(255, 255, 255, transp));
        graphics2D.fillRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(color);
        graphics2D.setFont(new Font("consoles", Font.BOLD, 40));
        long length = (int) graphics2D.getFontMetrics().getStringBounds(s, graphics2D).getWidth();
        graphics2D.drawString(s, (int) (GamePanel.WIDTH / 2 - length / 2), (int) (GamePanel.HEIGHT / 2 + buttonHeight / 4));
    }
}
