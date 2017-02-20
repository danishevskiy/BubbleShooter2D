package ua.dan1sh;

import java.awt.*;

/**
 * Created by a.danishevskiy on 13.02.2017.
 */
public class Player {

    //Fields
    private double ANGLE = Math.toRadians(45);
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;

    private int speed;

    private Color color1;
    private Color color2;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;

    public static boolean isFiring;

    private double health;

    //Constructor

    public Player(){
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        r = 5;
        speed = 5;
        dx = 0;
        dy = 0;
        color1 = Color.WHITE;
        up = false;
        down = false;
        left = false;
        right = false;
        isFiring = false;
        health = 3;

    }

    //Functions

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
    public int getR(){
        return r;
    }

    public void hit(){
        health--;
    }

    public void update(){
        if (up && (y > r)){
            dy =  -speed;
        }
        if (down && y < (GamePanel.HEIGHT - r)){
            dy = speed;
        }
        if (left && x > r){
            dx = -speed;
        }
        if (right && x < GamePanel.WIDTH - r){
            dx = speed;
        }
        if (up && left || up && right || down && left || down && right){
            dy *= Math.sin(ANGLE);
            dx *= Math.cos(ANGLE);
        }
        y += dy;
        x += dx;

        dy = 0;
        dx = 0;

        if (isFiring){
            GamePanel.bullets.add(new Bullet());
        }

    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color1);
        graphics2D.fillOval((int) (x - r), (int)(y - r), 2 * r, 2 * r);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color1.darker());
        graphics2D.drawOval((int) (x - r), (int)(y-r), 2 * r, 2 * r);
        graphics2D.setStroke(new BasicStroke(1));

    }
}
