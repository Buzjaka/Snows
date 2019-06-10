import com.sun.media.sound.AiffFileReader;

import java.util.Random;

public class Snow {
    double x, y, gravity, resistance;
    double[] Air_resistance = {
            0.0095,
            0.0094,
            0.0093,
            0.0092,
            0.0091,
            0.0090
    };

    public Snow(double x, double y, double gravity) {
        this.x = x;
        this.y = y;
        this.gravity = gravity;
        resistance = Air_resistance[new Random().nextInt(Air_resistance.length)];
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move(double windDirection, double windPower) {
        this.x += (Math.cos(Math.toRadians(windDirection)) * windPower);
        this.y += gravity;
        gravity += (0.01 - resistance);
    }
}
