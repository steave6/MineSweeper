/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

/**
 * 個々のマス（ボタン）の機能を決定するクラス
 * @author steav
 */
public class MineButton extends Button {
    
    int row;
    int colum;
    public boolean isClicked;
    static Integer count = 0;
    static Integer flgcount = 0;
    public boolean isSecondaryClicked;

    public MineButton(int Row, int Colum) {
        this.isSecondaryClicked = false;
        this.isClicked = false;
        this.setText("   ");
    }

    /**
     * マスを左クリックした場合の処理
     * @param rowlum
     * @param map
     * @return 開いたマスの状態に応じてintのデータをreturn
     */
    public int ClickButton(Integer rowlum, Map map) {
        System.out.println(map.get(rowlum));
        if (isClicked || isSecondaryClicked) {// クリック済みか、ロック済みかを判定
            return 10;
        }
        isClicked = true;
        
        // 初クリックで0でない場合には11をreturn
        if (!map.get(rowlum).equals(0) && count == 0) {
            System.out.println("first click: " + map.get(rowlum));
            isClicked = false;
            return 11;
        }
        count++;
        
        this.setBackground(Background.EMPTY);
        
        // 爆弾は9、その他は周りのマスの爆弾の数に応じてそれぞれの数値をreturn
        if (map.get(rowlum).equals(9)) {
            this.setText("B");
            Alert alert = new Alert(AlertType.INFORMATION, "Bommmmmmmmmmmmb");
            alert.setHeaderText("Game Over");
            alert.show();
            return (int) map.get(rowlum);
        } else if (map.get(rowlum).equals(0)) {
            this.setText("   ");
            return (int) map.get(rowlum);
        } else {
            this.setText(map.get(rowlum).toString());
            return (int) map.get(rowlum);
        }
    }

    /**
     * 右クリックの機能
     * フラグを立てマスにCを表示させる
     */
    public void ClickSecondary() {
        if (!isSecondaryClicked && !isClicked) {
            isSecondaryClicked = true;
            flgcount++;
            this.setText("C");
        } else if (!isClicked) {
            isSecondaryClicked = false;
            this.setText("   ");
        }
    }
}
