package ua.dan1sh;

import java.awt.*;

/**
 * Created by Андрей on 15.02.2017.
 */
public class Enemy {
    //Field
    private double x;
    private double y;
    private int r;
    private int type;
    private  int rank;
    private double speed;
    private double dx;
    private double dy;
    private double health;

    private Color color;

    //Constructor
    public Enemy(int type, int rank){
        this.type = type;
        this.rank = rank;
        switch (type){
            case(1):
                color = Color.GREEN;
                switch (rank){
                case(1):

                    x = Math.random() * GamePanel.WIDTH;
                    y = 0;
                    r = 7;
                    speed = 2;
                    health = 2;

                    double angle = Math.toRadians(Math.random() * 360);
                    dx = Math.sin(angle) * speed;
                    dy = Math.cos(angle) * speed;

            }
        }

    }
    //Methods

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getR(){
        return r;
    }
    public boolean remove(){
        if(health <= 0){
            return true;
        }
        return false;

    }
    public void hit(){
        health--;
    }
    public void update(){
        x += dx;
        y +=dy;

        if (x < 0 && dx < 0) dx = -dx;
        if (x > GamePanel.WIDTH && dx > 0) dx = -dx;
        if (y < 0 && dy <0) dy = -dy;
        if (y > GamePanel.HEIGHT && dy > 0) dy = -dy;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(color);
        graphics2D.fillOval((int) (x - r), (int) (y - r), 2 * r, r);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color.darker());
        graphics2D.fillOval((int) (x - r), (int) (y - r), 2 * r, r);
        graphics2D.setStroke(new BasicStroke(1));
    }

}
