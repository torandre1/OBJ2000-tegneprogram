package tegneprogram;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * subklasse for linje
 */
public class Line extends Figure {

/**
 * deklarering
 */
    private double xStart = super.getX();
    private double yStart = super.getY();
    private double xEnd;
    private double yEnd;

/**
 * konstruktør
 * @param xStart
 * @param yStart
 * @param xEnd
 * @param yEnd 
 */
    public Line(double xStart, double yStart, double xEnd, double yEnd) {
        super(xStart, yStart);
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    /**
     * metode for startposisjon X
     * @return
     */
    public double getX() {
        return xStart;
    }

    /**
     * metode for startposisjon Y
     * @return
     */
    public double getY() {
        return yStart;
    }

    /**
     * metode for sluttposisjon x
     * @return
     */
    public double getXx() {
        return xEnd;
    }
    
    /**
     * metode for sluttposisjon y
     * @return
     */
    public double getYy() {
        return yEnd;
    }
    
    /**
     * setter metoder for sluttposisjon
     * @param xEnd
     */
    public void setXx(double xEnd) {
        this.xEnd = xEnd;
    }

    /**
     * setter metoder for sluttposisjon
     * @param yEnd
     */
    public void setYy(double yEnd) {
        this.yEnd = yEnd;
    }

    /**
     * tegne-metode linje
     * @param gc
     */
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.strokeLine(xStart, yStart, xEnd, yEnd);
    }
}