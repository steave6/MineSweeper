/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

/**
 *
 * @author steav
 */
public class MineButton extends Button {
    
    int row;
    int colum;
    private final int Row;
    private final int Colum;

    public MineButton(int Row, int Colum) {
        this.Row = Row;
        this.Colum = Colum;
        this.setText("   ");
    }

    public void ClickButton(Integer rowlum, Map map) {
        System.out.println(map.get(rowlum));
        this.setBackground(Background.EMPTY);
        if (map.get(rowlum).equals(9)) {
            this.setText("b");
        } else {
            this.setText(map.get(rowlum).toString());
        }
    }
    
}
