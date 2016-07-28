/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author steav
 */
public class MineSweeper extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Stage window = primaryStage;

        MineField field = new MineField(15, 10);
        GridPane gridfield = field.Field();
        
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setTopAnchor(gridfield, 0.0);
        anchorPane.setLeftAnchor(gridfield, 0.0);
        anchorPane.setRightAnchor(gridfield, 0.0);
        anchorPane.setBottomAnchor(gridfield, 0.0);
        
        anchorPane.getChildren().add(gridfield);
        
        Scene scene = new Scene(anchorPane);
        
        window.setTitle("MineSweeper!");
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}