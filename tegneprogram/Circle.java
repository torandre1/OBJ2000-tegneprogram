package tegneprogram;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 *  subklasse for sirkel
 */
public class Circle extends Figure {

    /**
     * deklarering
     */
    private double radius;
    private double x = super.getX();
    private double y = super.getY();
    
    /**
     * konstruktør
     * @param radius
     * @param x
     * @param y 
     */
    public Circle(double radius, double x, double y) {
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
    *metode for å returnere y
    * @return 
    */
    public double getY() {
        return y;
    }
    
    /**
    *metode for å retunere radius
    * @return 
    */
    public double getRadius() {
        return radius;
    }

/**
 * metode for å sette radius
 * @param newRadius 
 */
    public void setRadius(double newRadius) {
        radius = newRadius;
    }
    
    /**
    * tegne-metode sirkel
    * @param gc 
    */
    public void draw(GraphicsContext gc) {
        double r = radius / 2;
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.TRANSPARENT);
        gc.setLineWidth(1.0);
        gc.fillOval(x - r, y - r, radius * 2, radius * 2);
        gc.strokeOval(x - r, y - r, radius * 2, radius * 2);
    }
}