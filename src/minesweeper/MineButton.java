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
    public boolean isClicked;
    static int count;
    public boolean isSecondaryClicked;

    public MineButton(int Row, int Colum) {
        this.isSecondaryClicked = false;
        this.isClicked = false;
        this.Row = Row;
        this.Colum = Colum;
        this.setText("   ");
        MineButton.count = 0;
    }

    public int ClickButton(Integer rowlum, Map map) {
        System.out.println(map.get(rowlum));
        if (isClicked || isSecondaryClicked) {
            return 10;
        }
        isClicked = true;
                
        if (!map.get(rowlum).equals(0) && count == 0) {
            isClicked = false;
            return 11;
        }
        count++;
        
        this.setBackground(Background.EMPTY);
        
        if (map.get(rowlum).equals(9)) {
            this.setText("B");
            return (int) map.get(rowlum);
        } else if (map.get(rowlum).equals(0)) {
            this.setText(map.get(rowlum).toString());
            return (int) map.get(rowlum);
        } else {
            this.setText(map.get(rowlum).toString());
            return (int) map.get(rowlum);
        }
    }

    public void ClickSecondary() {
        if (!isSecondaryClicked) {
            isSecondaryClicked = true;
            this.setText("C");
        } else {
            isSecondaryClicked = false;
            this.setText("   ");
        }
    }
}
