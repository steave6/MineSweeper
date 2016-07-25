/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author steav
 */
public class MineField {
    
    public GridPane minefield;
    private final int Colum;
    private final int Row;
    final Random RanDom;
    final Map MapwithBomb;
    private final Map MapCompleted;

    public MineField(int gridRow, int gridColum) {
        this.Row = gridRow;
        this.Colum = gridColum;
        this.RanDom = new Random();
        this.MapwithBomb = this.CreateBomb();
        this.MapCompleted = this.MapComplete();
    }

    public GridPane Field() {
        minefield = new GridPane();
        for (int i = 0; i < this.Row; i++) {
            for (int j = 0; j < this.Colum; j++) {
                int row = i;
                int colum = j;
                int rowlum = row + (1000 * colum);
                MineButton button = new MineButton(row, colum);
                button.setOnAction((ActionEvent e) -> {
                    button.ClickButton(rowlum, this.MapCompleted);
                });
                minefield.add(button, row, colum);
            }
        }
        return minefield;
    }

    private Map CreateBomb() {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < this.Row; i++) {
            for (int j = 0; j < this.Colum; j++) {
                int Rowlocal = i;
                int Columlocal = j * 1000;
                int bomb = 0;
                if (this.RanDom.nextFloat() < 0.25) {
                    bomb = 9;
                }
                map.put(Rowlocal + Columlocal, bomb);
            }
        }
        return map;
    }

    public Map MapComplete() {
        Map map = this.MapwithBomb;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            Integer next = (Integer) iterator.next();
            int row = next % 1000;
            int colum = next / 1000;
            System.out.println("row: " + row);
            System.out.println("colum: " + colum);
            if (map.get(next).equals(9)) {
                continue;
            }
            int count = 0;
            for (int horizon = 0; horizon < 3; horizon++) {
                int checkrow = (row - 1) + horizon;
                if (checkrow < 0) {
                    continue;
                }
                for (int virtical = 0; virtical < 3; virtical++) {
                    int checkcolum = (colum - 1) + virtical;
                    if (checkcolum < 0 || (virtical == 1 && horizon == 1)) {
                        continue;
                    }
                    if (this.Row - 1 < checkrow || this.Colum - 1 < checkcolum) {
                        continue;
                    }
                    int checkrowlum = checkrow + (checkcolum * 1000);
                    if (map.get(checkrowlum).equals(9)) {
                        count++;
                    }
                }
            }
            map.replace(next, count);
        }
        return map;
    }
    
}
