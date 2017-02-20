package ua.dan1sh;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by a.danishevskiy on 13.02.2017.
 */
public class GamePanel extends JPanel implements Runnable{

    //Field
    public static int WIDTH = 600;
    public static int HEIGHT = 600;
    public static int mouseX;
    public static int mouseY;


    private Thread thread;

    private BufferedImage image;
    private Graphics2D graphics2D;

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Menu menu;
    public static Wave wave;
    private int FPS;
    private double millisFPS;
    private long timerFPS;
    private int sleepTime;
    public static boolean leftMouse;

    public static enum STATES{
        MENU,
        PLAY
    }
    public static STATES state = STATES.MENU;


    //Constructor
    public GamePanel(){
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());

    }

    // FUnctions

    public void gameUpdate(){
        //Background update
        background.update();
        //Player update
        player.update();

        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove){  //dsdwe
                bullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).update();
        }

        //Bullets-enemies collide
        for (int i = 0; i < enemies.size(); i++){
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            for (int j = 0; j < bullets.size(); j++){
                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();
                double dx = ex - bx;
                double dy = ey - by;

                double dist = Math.sqrt(dx*dx + dy * dy);

                if ((int)dist < e.getR() + b.getR()){
                    e.hit();
                    bullets.remove(j);
                    j--;
                    boolean remove = e.remove();
                    if (remove){
                        enemies.remove(i);
                        i--;
                        break;
                    }

                }

            }

        }

        //Player-enemy collides
        for (int i = 0; i < enemies.size(); i++){
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();
            double dx = ex - px;
            double dy = ey - py;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if ((int) dist <= e.getR() + player.getR()){
                e.hit();
                player.hit();
                boolean remove = e.remove();
                if (remove){
                    enemies.remove(i);
                    i--;
                }
            }
        }

        //Wave update
        wave.update();




    }

    public void gameRender(){
        // Background draw
        background.draw(graphics2D);
        //Player update
        player.draw(graphics2D);
        //Enemies update
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).draw(graphics2D);
        }

        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(graphics2D);
        }

        //Wave draw
        if (wave.showWave()){
            wave.draw(graphics2D);
        }


    }
    private void gameDraw(){
        Graphics graphic2 = this.getGraphics();
        graphic2.drawImage(image, 0, 0, null);
        graphic2.dispose();
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {

        FPS = 30;
        millisFPS = 1000 / FPS;
        sleepTime = 0;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D)image.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        leftMouse = false;
        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        wave = new Wave();
        menu = new Menu();


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = (Graphics2D) bufferedImage.getGraphics();
        g1.setColor(new Color(255, 255 ,255));
        g1.drawOval(0, 0, 4, 4);
        g1.drawLine(2, 0, 2, 4);
        g1.drawLine(0, 2, 4, 2);
        Cursor cursor = toolkit.createCustomCursor(bufferedImage, new Point(3, 3), "myCursor");
        g1.dispose();


        while(true){ // TODO States
            this.setCursor(cursor);

            timerFPS = System.nanoTime();

            if (state.equals(STATES.MENU)){
                background.update();
                background.draw(graphics2D);
                menu.update();
                menu.draw(graphics2D);

                gameDraw();
            }
            if (state.equals(STATES.PLAY)){
                gameUpdate();
                gameRender();
                gameDraw();

            }






            timerFPS = (System.nanoTime() - timerFPS) / 1000000;
            if (millisFPS > timerFPS){
                sleepTime = (int) (millisFPS - timerFPS);
            }else sleepTime = 1;


            try {
                thread.sleep(sleepTime); // TODO FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;
        }


    }
}
