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
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

/**
 * 盤面の生成や個別のマスの操作を担当するクラス
 * @author steav
 */
public class MineField {
    // Field variable
    public GridPane mineField;
    private final int Colum;
    private final int Row;
    final Random RanDom;
    Map MapCompleted;
    Map<Integer,MineButton> buttonMap;
    public Integer BombCount;
    
    public MineField(int gridRow, int gridColum) {
        this.Row = gridRow;
        this.Colum = gridColum;
        this.RanDom = new Random();
        this.MapCompleted = this.CreateBomb();
    }

    /**
     * ボタンとしてマスを生成
     * @return mineField 爆弾を含む盤面をreturn
     */
    public GridPane main() {
        mineField = new GridPane();
        buttonMap = new LinkedHashMap<>();
        
        for (int r = 0; r < this.Row; r++) {// 行の数だけ反復
            for (int c = 0; c < this.Colum; c++) {// 列の数だけ反復
                int row = r;
                int colum = c;
                int rowlum = row + (1000 * colum);// 行を1から3桁までで表現、列を4桁以上で表現
                MineButton button = new MineButton(row, colum);
                button.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {// 左クリックの処理
                        int result = button.ClickButton(rowlum, this.MapCompleted);
                        if (result == 0) {// クリックしたマスの値が0の場合
                            this.openZeroArea(rowlum);
                        }
                        if (result == 11) {// 初クリックの値が0以外の場合
                            while (result != 0) {// 0が出るまで繰り返し      
                                this.MapCompleted = this.CreateBomb();
                                result = button.ClickButton(rowlum, this.MapCompleted);
                                System.out.println("result = " + result);
                            }
                            this.openZeroArea(rowlum);
                        }
                    }else if (e.getButton() == MouseButton.SECONDARY) {// 右クリックの処理
                        System.out.println("check");
                        button.ClickSecondary();
                    }
                    
                    System.out.println("BombCount:" + BombCount);
                    System.out.println("ClickCount:" + MineButton.count);
                    if ((this.Row * this.Colum - BombCount) == MineButton.count) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game Clear!");
                        alert.setHeaderText("Every Bomb sweeped");
                        alert.show();
                    }
                });
                buttonMap.put(rowlum, button);
                mineField.add(button, row, colum);
            }
        }
        return mineField;
    }
    
    /**
     * クリックしたマスが0の場合周囲のマスすべてをクリックする
     * @param rowlum クリックしたマスの行と列の情報
     */
    public void openZeroArea(int rowlum) {
        int row = rowlum % 1000;
        int colum = rowlum / 1000;
        for (int r = 0; r < 3; r++) {
            int checkrow = (row - 1) + r;
            if (checkrow < 0) {
                continue;
            }
            for (int c = 0; c < 3; c++) {
                int checkcolum = (colum - 1) + c;
                if (checkcolum < 0 || (r == 1 && c == 1)) {
                    continue;
                }
                if (this.Row - 1 < checkrow || this.Colum - 1 < checkcolum) {
                    continue;
                } 
                int checkrowlum = checkrow + (checkcolum * 1000);
                MineButton button = this.buttonMap.get(checkrowlum);
                int result = button.ClickButton(checkrowlum, this.MapCompleted);
                if (result == 0) {// 新規に開くマスがすべて0以外となるまで再帰的に処理
                    this.openZeroArea(checkrowlum);
                }
            }
        }
    }

    /**
     * 爆弾のあるマスをランダムに生成
     * マスと対応するMapのkeyに爆弾を意味する9のvalueを与える
     * @return 
     */
    private Map CreateBomb() {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        BombCount = 0;
        for (int i = 0; i < this.Row; i++) {
            for (int j = 0; j < this.Colum; j++) {
                int row = i;
                int colum = j * 1000;
                int bomb = 0;
                if (this.RanDom.nextFloat() < 0.1) {// 爆弾の頻出度を設定
                    bomb = 9;
                    BombCount++;
                    System.out.println("BombCount++ =>" + BombCount);
                }
                int rowlum = row + colum;
                map.put(rowlum, bomb);
            }
        }
        return MapComplete(map);
    }

    /**
     * それぞれのマスの周りに爆弾が何個あるか調べその個数をMapのvalueとして与える
     * @param map
     * @return map 生成されたMapをreturn
     */
    public Map MapComplete(Map map) {
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            Integer next = (Integer) iterator.next();
            int row = next % 1000;
            int colum = next / 1000;
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
