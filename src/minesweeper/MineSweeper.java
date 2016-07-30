/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author steav
 */
public class MineSweeper extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Stage window = primaryStage;

        MineField field = new MineField(8, 10);// マスの個数を設定し盤面を生成
        GridPane gridfield = field.main();
        
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setTopAnchor(gridfield, 0.0);
        anchorPane.setLeftAnchor(gridfield, 0.0);
        anchorPane.setRightAnchor(gridfield, 0.0);
        anchorPane.setBottomAnchor(gridfield, 0.0);
        

        Label remaining = new Label();
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(remaining,gridfield);
        
        // AnchorPaneにマスを配置し自動でwindowのサイズを調整
        anchorPane.getChildren().add(vbox);
        
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