package tegneprogram;

import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * superklasse for figurer
 */
public class Figure {

    // deklarering for startposisjoner
    private double x, y;

    /**
     * konstruktur
     * @param x
     * @param y
     */
    public Figure(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * metode for å returnere x (start koordinat x)
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * metode for å returnere y (start koordinat y)
     * @return
     */
    public double getY() {
        return y;
    }
    
    /**
     * GraphicsContext gc - brush objekt
     * @param gc
     */
    public void draw(GraphicsContext gc) {
    }
}
