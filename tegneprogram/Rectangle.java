package tegneprogram;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * subklasse for rektangel
 * 
 */
public class Rectangle extends Figure {

    // deklarering
    private double radius;
    private double x = super.getX();
    private double y = super.getY();

    /**
     * konstruktør
     * @param radius
     * @param x
     * @param y
     */
    public Rectangle(double radius, double x, double y) {
        super(x, y);
        this.radius = radius;
    }

    /**
     * metode for å returnere x
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * metode for å returnere y
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * metode for å returnere radius
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     * metode for å sette radius
     * @param newR
     */
    public void setRadius(double newRadius) {
        radius = newRadius;
    }

    /**
     * tegne-metode rektangel
     * @param gc
     */
    public void draw(GraphicsContext gc) {
        double r = radius / 2;
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.TRANSPARENT);
        gc.setLineWidth(1.0);
        gc.fillRect(x - r, y - r, radius * 2, radius * 2);
        gc.strokeRect(x - r, y - r, radius * 2, radius * 2);
    }
}