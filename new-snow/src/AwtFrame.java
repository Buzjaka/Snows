import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AwtFrame extends Frame implements Runnable {
    private BufferedImage bufImg;
    private Graphics bufGraphics;
    private double windDirection = 90;
    private double windPower = 1;
    private double gravity = 0.0000001;
    private ArrayList<Snow> snows = new ArrayList<>();
    private Random rand;

    public AwtFrame() {
        super("Test_AWT_Frame");
        rand = new Random();
        this.setVisible(true);
        this.setSize(800, 800);
        new Thread(this).start();
        new Timer().schedule(new TimerTask() {
            public void run() {
                randomWind();
            }
        }, 0, 2000);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bufImg, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        bufImg = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        genSnow();
        for (int i = 0; i < snows.size(); i++) {
            Snow temp = snows.get(i);
            if (outOfrange(temp)) {
                snows.remove(i);
                continue;
            }
            temp.move(windDirection, windPower);
        }
        bufGraphics = bufImg.getGraphics();
        bufGraphics.setColor(Color.WHITE);
        bufGraphics.clearRect(0, 0, 800, 800);
        for (int i = 0; i < snows.size(); i++) {
            Snow temp = snows.get(i);
            bufGraphics.fillOval((int) temp.getX(), (int) temp.getY(), 2, 2);
        }
        paint(g);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }

    public void randomWind() {
        windDirection = rand.nextInt(36) * 5;
        windPower = rand.nextDouble() / 2;
    }

    public void genSnow() {
        snows.add(new Snow(rand.nextInt(2000) - 500, 0, gravity));
    }

    public boolean outOfrange(Snow snow) {
        if (snow.getY() > 790) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        new AwtFrame();
       // System.out.println(":aGETbdrf");
    }
}
