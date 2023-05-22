package tegneprogram;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Obligatorisk oppgave i OBJ2000 Høst 2022
 * Oblig-gruppe 11
 * @author
 * Cecille Klev,
 * Tor André Myhre
 */

/**
 * Programkommentar:
 * 
 * Programmet bruker Canvas og GraphicsContext som "ark" og "brush" for å kunne
 * tegne sirkel, rektangel og linje. 
 * Dette fungerer ved at man velger hvilken figur man ønsker å lage ved å bruke knappene i GUI.
 * Etter man har valgt en av figurere kan man trykke høyre museknapp der man vil starte figuren,
 * for så å kunne dra den ut for å velge størrelse. Når man slipper museknappen er figuren tegnet og
 * den kan ikke endres på. Angrer man på tegningen kan man velge å slette alt som er blitt tegnet på arket.
 * 
 * Programmet har en del mangler i følge kravene spesifisert i oppgaven:
 * - Det er ikke mulig å skrive ut tekst til arket
 * - Det er ikke mulig å velge farger, verken på figur eller fyll til figur
 * - Det er ikke mulig å velge en figur som allerede er tegnet, for så å kunne endre størrelse eller farge på noe vis.
 * - Infopanel fungerer delvis, men ikke slik det er bedt om i oppgaven. Den viser ikke noe info om allerede tegnede figurer.
 * 
 * GUI: 
 * - er lagd ferdig slik vi har kommet frem til, selv om det er mange ting som ikke er implemetert, 
 *  ville vi vise hvordan det vil se ut om programmet var 100% ferdig.
 * - De plassene i GUI der det ikke er implemenrt noe er det lagt inn Alerts på mouseevents slik at man likevel får noe respons på alt i GUI'et,
 *  dette for å bare "tette" igjen manglenene i programmet. 
 * 
 * Det er lagt til en ekstra funksjon som ikke var et krav i oppgaven som gjør at man kan slette det man har tegnet på arket via clearRect(),
 * som er en metode inkludert i GraphicsContext og som var grei å implentere, derfor gjorde vi som et plaster.
 *
 * 
 */

public class Main extends Application {

   /**
    * deklarasjoner
    */
    private Canvas canvas;
    private GraphicsContext gc;
    private Circle circle;
    private Rectangle rectangle;
    private Line line;
    private ArrayList<Figure> figureList;
    private Boolean b1, b2, b3 = false;

    /**
    * pressHandler for oppretting av figurer
    * @param mouseEvent 
    */
    private void pressHandler(MouseEvent mouseEvent) {
        if (b1) {
            if (b2) {
                // sirkel
                circle = new Circle(1, mouseEvent.getX(), mouseEvent.getY());
            } else if (!b2) {
                // rektangel
                rectangle = new Rectangle(1, mouseEvent.getX(), mouseEvent.getY());
            }
        } else {
            // linje
            line = new Line(mouseEvent.getX(), mouseEvent.getY(), 1, 1);
        }
    }

    /**
    * dragHandler for størrelse ved å dra musa med museknappen holdt inne
    * @param mouseEvent 
    */
    private void dragHandler(MouseEvent mouseEvent) {
        double ex = mouseEvent.getX();
        double ey = mouseEvent.getY();
        double newRadius;

        if (b1) {
            if (b2) {
                // sirkel
                newRadius = Math.sqrt(Math.pow(circle.getX() - ex, 2) + Math.pow(circle.getY() - ey, 2)); // radius for sirkel
                circle.setRadius(newRadius); // radius settes til figure ved hjelp av drag
                gc.clearRect(0, 0, 1050, 700);
                //figur legges i arraylist figureList
                figureList.add(circle); 
                for (Figure i : figureList) {
                    i.draw(gc);
                }
                circle.draw(gc);
            } else if (!b2) {
                // rektangel
                newRadius = Math.sqrt(Math.pow(rectangle.getX() - ex, 2) + Math.pow(rectangle.getY() - ey, 2));
                rectangle.setRadius(newRadius);
                gc.clearRect(0, 0, 1050, 700);
                figureList.add(rectangle); 
                for (Figure i : figureList) {
                    i.draw(gc);
                }
                rectangle.draw(gc);
            }
        } else {
            // linje
            gc.clearRect(0, 0, 1050, 700);
            figureList.add(line);

            line.setXx(ex);
            line.setYy(ey);
            for (Figure i : figureList) {
                i.draw(gc);
            }
            line.draw(gc);
        }
    }

    /**
    * releaseHandler lagrer objektene i arraylist figureList
    * @param mouseEvent 
    */
    private void releaseHandler(MouseEvent mouseEvent) {
        if (b1) {
            if (b2) {
                figureList.add(circle); 
            } else if (!b2) {
                figureList.add(rectangle); 
            }
        } else {
            figureList.add(line); 
        }
    }

    /**
    * clear() sletter innholdet i figureList
    * clearRect() på gc gjør alt innhold transparent innenfor en rektanguler int,int
    */
    private void clear() {
        figureList.clear();
        gc.clearRect(0, 0, 1050, 700); 
    }

/**
 * 
 * @param stage
 * @throws Exception 
 */
    @Override
    public void start(Stage stage) throws Exception {
        
        
         // arraylist for figurer
        figureList = new ArrayList<Figure>();

        // tegneark og brush
        canvas = new Canvas(900, 700);
        gc = canvas.getGraphicsContext2D();

        /**
         * GUI komponenter
         */
        
        // Buttons, labels
        Label lblHeadline = new Label("Select shape");
        Button btCircle = new Button("Circle");
        Button btRectangle = new Button("Rectangle");
        Button btLine = new Button("Line");
        Button btClear = new Button("Clear");
        Label lblTxt = new Label("Print text");
        Button btText = new Button("Text");
        TextArea txtA = new TextArea("Your text here..");
        Label lblFwBw = new Label("Move shapes");
        Button btBackward = new Button("Move back");
        Button btForward = new Button("Move forward");
        Label lblColorShape = new Label("Shape color: ");
        Label lblColorFill = new Label("Fill color: ");
        Button btHelp = new Button("Help");
        
        // Alert objekt for knapper som ikke er ferdige
        Alert alert = new Alert(AlertType.NONE);

        //ColorPicker for stroke og fill
        ColorPicker colorShape = new ColorPicker();
        colorShape.setValue(Color.BLACK);
        ColorPicker colorFill = new ColorPicker();
        colorFill.setValue(Color.TRANSPARENT);

        // komponenter design
        btCircle.setCursor(Cursor.HAND);
        btCircle.setMinWidth(100);
        btCircle.setPadding(new Insets(5, 5, 5, 5));
        btRectangle.setCursor(Cursor.HAND);
        btRectangle.setMinWidth(100);
        btRectangle.setPadding(new Insets(5, 5, 5, 5));
        btLine.setCursor(Cursor.HAND);
        btLine.setMinWidth(100);
        btLine.setPadding(new Insets(5, 5, 5, 5));
        btClear.setCursor(Cursor.HAND);
        btClear.setMinWidth(100);
        btClear.setPadding(new Insets(5, 5, 5, 5));
        btText.setCursor(Cursor.HAND);
        btText.setMinWidth(100);
        btText.setPadding(new Insets(5, 5, 5, 5));
        btBackward.setCursor(Cursor.HAND);
        btBackward.setMinWidth(100);
        btBackward.setPadding(new Insets(5, 5, 5, 5));
        btForward.setCursor(Cursor.HAND);
        btForward.setMinWidth(100);
        btForward.setPadding(new Insets(5, 5, 5, 5));
        txtA.setMaxWidth(100);
        txtA.setMaxHeight(100.0);
        
        // infopanel
        VBox infoVBox = new VBox();
        infoVBox.setSpacing(4);
        infoVBox.setStyle("-fx-background-color: #C0C0C0");
        Label infoLabel = new Label("Info Panel");
        Label infoSelected = new Label("Selected: ");
        Label infoSelectedColor = new Label("Shape #: ");
        Label infoFillColor = new Label("Fill #: ");
        Label infoSize = new Label("Size: ");
        Label infoPosX = new Label("Pos X: ");
        Label infoPosY = new Label("Pos Y: ");
        

        /**
         * VBox'er
         */
        
        // infopanel
        infoVBox.getChildren().addAll(infoLabel, infoSelected, infoSelectedColor,
                                        infoFillColor, infoSize, infoPosX, infoPosY);
        
        // knapper, labels
        VBox btns = new VBox();
        btns.setSpacing(4);
        btns.getChildren().addAll(lblHeadline, btCircle, btRectangle, btLine, btClear,
                                    lblFwBw, btForward, btBackward, 
                                    lblTxt, btText, txtA, 
                                    lblColorShape, colorShape, lblColorFill, colorFill);
        
        // instruksjons knapp
        VBox help = new VBox();
        help.getChildren().add(btHelp);
        
        // VBox "wrapper" for alle GUI-komponentenee
        VBox vBox = new VBox();
        vBox.getChildren().addAll(btns, infoVBox, help);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(8);
        vBox.setPrefWidth(100);
        vBox.setStyle("-fx-background-color: #f3f6f4");

        /**
        * ActionEvent for btCircle
        */
        btCircle.setOnAction((ActionEvent e) -> {
            // booleans til velger av figur
            b1 = true;
            b2 = true;
            // infopanel
            infoSelected.setText("Shape: Circle");
            infoSelectedColor.setText("Shape #: NA");
            infoFillColor.setText("Fill #: NA");
            infoSize.setText("Size: NA");
            infoPosX.setText("Pos X: NA");
            infoPosY.setText("Pos Y: NA");
        });
        
        /**
         * ActionEvent for btRectangle
         */
        btRectangle.setOnAction((ActionEvent e) -> {
            b1 = true;
            b2 = false;
            infoSelected.setText("Shape: Rectangle");
            infoSelectedColor.setText("Shape #: NA");
            infoFillColor.setText("Fill #: NA");
            infoSize.setText("Size: NA");
            infoPosX.setText("Pos X: NA");
            infoPosY.setText("Pos Y: NA");
        });
        
        /**
         * ActionEvent for btLine
         */
        btLine.setOnAction((ActionEvent e) -> {
            b1 = false;
            infoSelected.setText("Shape: Line");
            infoSelectedColor.setText("Shape #: NA");
            infoFillColor.setText("Fill #: NA");
            infoSize.setText("Size: NA");
            infoPosX.setText("Pos X: NA");
            infoPosY.setText("Pos Y: NA");
        });
        
        /**
         * ActionEvent for btText
         */
        btText.setOnAction((ActionEvent e) -> { 
           // b3 = true; 
           // Alert, pga funksjon ikke ferdig
           alert.setAlertType(AlertType.INFORMATION);
           alert.setTitle("Text");
           alert.setContentText("This function is not implemented yet.\n"
                                + "Please check back later for updates here: www.github.com/.......");
           alert.show();
           
           //infopanel
           infoSelected.setText("Shape: Text");
           infoSelectedColor.setText("Shape #: NA");
           infoFillColor.setText("Fill #: NA");
           infoSize.setText("Size: NA");
           infoPosX.setText("Pos X: NA");
           infoPosY.setText("Pos Y: NA");
        });
        
        /**
         * ActionEvent for btClear
         */
        btClear.setOnAction((ActionEvent e) -> {
            clear(); // sletter arrayList figureList
            
            // infopanel
            infoSelected.setText("Shape: ");
            infoSelectedColor.setText("Shape #: ");
            infoFillColor.setText("Fill #: ");
            infoSize.setText("Size: ");
            infoPosX.setText("Pos X: ");
            infoPosY.setText("Pos Y: ");
        });
        
        /**
         * ActionEvent for btForward
         */
        btForward.setOnAction((ActionEvent e) -> {
           // Alert, pga funksjon ikke fullstendig
           alert.setAlertType(AlertType.INFORMATION);
           alert.setTitle("Move forward");
           alert.setContentText("This function is not implemented yet.\n"
                                + "Please check back later for updates here: www.github.com/.......");
           alert.show();
           //infopanel
           infoSelected.setText("Shape: ");
           infoSelectedColor.setText("Shape #: ");
           infoFillColor.setText("Fill #: ");
           infoSize.setText("Size: ");
           infoPosX.setText("Pos X: ");
           infoPosY.setText("Pos Y: "); 
        });
       
        /**
        * ActionEvent for btBackward
        */
        btBackward.setOnAction((ActionEvent e) -> {
            // Alert, pga funksjon ikke fullstendig
           alert.setAlertType(AlertType.INFORMATION);
           alert.setTitle("Move backward");
           alert.setContentText("This function is not implemented yet.\n"
                                + "Please check back later for updates here: www.github.com/.......");
           alert.show();
           //infopanel
           infoSelected.setText("Shape: ");
           infoSelectedColor.setText("Shape #: ");
           infoFillColor.setText("Fill #: ");
           infoSize.setText("Size: ");
           infoPosX.setText("Pos X: ");
           infoPosY.setText("Pos Y: ");
        });
                
        /**
         * ActionEvent for colorShape
         */
        colorShape.setOnAction((ActionEvent e) -> {
            // Alert, pga funksjon ikke fullstendig
            alert.setAlertType(AlertType.INFORMATION);
            alert.setTitle("Change colors on shape");
            alert.setContentText("This function is not implemented yet.\n"
                                + "Please check back later for updates here: www.github.com/.......");
            alert.show();
            //infopanel
            infoSelected.setText("Shape: ");
            infoSelectedColor.setText("Shape #: ");
            infoFillColor.setText("Fill #: ");
            infoSize.setText("Size: ");
            infoPosX.setText("Pos X: ");
            infoPosY.setText("Pos Y: ");
        });
       
    /**
    * Actionevent for colorFill
    */            
        colorFill.setOnAction((ActionEvent e) -> {
           // Alert, pga funksjon ikke fullstendig
           alert.setAlertType(AlertType.INFORMATION);
           alert.setTitle("Change color on fill");
           alert.setContentText("This function is not implemented yet.\n"
                                + "Please check back later for updates here: www.github.com/.......");
            alert.show();
            //infopanel
            infoSelected.setText("Shape: ");
            infoSelectedColor.setText("Shape #: ");
            infoFillColor.setText("Fill #: ");
            infoSize.setText("Size: ");
            infoPosX.setText("Pos X: ");
            infoPosY.setText("Pos Y: ");  
        });
        
            /**
    * Actionevent for btHelp
    */            
        btHelp.setOnAction((ActionEvent e) -> {
           // Alert, pga funksjon ikke fullstendig
           alert.setAlertType(AlertType.INFORMATION);
           alert.setTitle("Instructions");
           alert.setContentText("Step 1: Select a figure to draw in the right menu\n"
                                + "Step 2: Create figure by pressing right mouse button\n"
                                + "Step 3: Drag while mouse is pressed to adjust size\n"
                                + "Step 4: Press Clear to Delete");
            alert.show();
            //infopanel
            infoSelected.setText("Shape: ");
            infoSelectedColor.setText("Shape #: ");
            infoFillColor.setText("Fill #: ");
            infoSize.setText("Size: ");
            infoPosX.setText("Pos X: ");
            infoPosY.setText("Pos Y: ");  
        });

    /**
    * Excetion e for mouseevents 
    */
        try {
            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
            canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Error").showAndWait();
        }
        
       /**
        * borderpane, scene, stage
        */
        BorderPane bPane = new BorderPane();
        bPane.setRight(vBox);
        bPane.setCenter(canvas);
        
        Scene scene = new Scene(bPane, 1050, 700);
        
        stage.setTitle("tegneprogram");                         
        Image icon = new Image("tegneprogram/icon.png");    
        stage.getIcons().add(icon); // bilde icon til stage
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * main
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
}
