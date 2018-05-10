/**
 * Project to Java Programming Language - 2016/2017
 * ------------------------------------------------
 * Project: Solitaire game
 * Date: May 2017
 *
 * @author  Juraj Kubis
 * @author  Jan Kubica
 */

package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import logic.Game;
import javafx.geometry.Insets;

/**
 * Class for start program and set window.
 */
public class Main extends Application {
    /**
     * Index of current GameScreen
     */
    public static int yourIDX;

    /**
     * Number of lauched games
     */
    public static int games_count = 0;

    /**
     * Generated Stage
     */
    public static Stage stage;

    /**
     * Parents for 4 Games at maximum
     */
    public static Parent[] windows = new Parent[4];

    /**
     * Gets id for screen
     * @return index for screen or -1 if unsuccessful
     */
    public static int getFreeIDX() {
        for (int i = 0; i < 4; i++)
            if(windows[i] instanceof Label)
                return i;
        return -1;
    }

    /**
     * Gets id for label
     * @return index for label or -1 if unsuccessful
     */
    public static int getNonFreeIDX() {
        for (int i = 0; i < 4; i++)
            if(windows[i] instanceof Pane)
                return i;
        return -1;
    }
    /**
     * Redraws the whole Screen
     * Sets Pane and resolution and configures other settings.
     */
    public static void redrawStage() {
        GridPane gridPane = new GridPane();
        Scene scene;

        if (Main.games_count > 1) {
            gridPane.setStyle("-fx-background-color: #000000;");
            gridPane.setHgap(1);
            gridPane.setVgap(1);

            gridPane.add(windows[0], 0, 0);
            gridPane.add(windows[1], 1, 0);
            gridPane.add(windows[2], 0, 1);
            gridPane.add(windows[3], 1, 1);

            scene = new Scene(gridPane, 1280, 960);
        } else {
            int idx = getNonFreeIDX();
            gridPane.add(windows[idx],0,0);
            scene = new Scene(gridPane, 630, 480);
        }

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Solitaire 1.0");
        stage.getIcons().add(new Image("/res/icon128x128.png"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Firstly initializes just one game and sets primary settings.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;

        Main.yourIDX = 0;



        windows[0] = FXMLLoader.load(getClass().getResource("gui.fxml"));
        windows[0].minWidth(630);
        windows[0].minHeight(480);

        for(int i = 1; i < 4; i++) {
            Label label = new Label();
            label.setMinSize(630, 480);
            windows[i] = label;
        }

        games_count = 1;

        Scene scene = new Scene(windows[0], 630, 480);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Solitaire Game");
        stage.getIcons().add(new Image("/res/icon128x128.png"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Starts the program
     * @param args given arguments to program
     */
    public static void main(String[] args) {
        launch(args);
    }
}
