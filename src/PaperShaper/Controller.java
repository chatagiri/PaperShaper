package PaperShaper;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.datatransfer.Clipboard;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Controller{

    // ControllerからStageへのアクセッサ
    Stage thisStage;
    void setThisStage(Stage stage) {
        thisStage = stage;
    }

    @FXML
    private TextField sourceField,resultField;

    @FXML
    private CheckBox overlayFlag,clearFlag;

    // 実行ボタン降下時処理
    @FXML
    protected void doButtonAction(){
        System.out.println(sourceField.getText());
        String source = sourceField.getText();
        // 改行文字を空白に置換
        String result = source.replaceAll("\n", "");
        result = source.replaceAll(".",".\n");
        resultField.setText(result);
    }

    // オーバーレイ是非処理
    @FXML
    private void doOverlayAction(){
        if(overlayFlag.isSelected() == true)
            thisStage.setAlwaysOnTop(true);
        else
            thisStage.setAlwaysOnTop(false);
    }

    // ソースクリック時フィールド自動クリア是非
    @FXML
    private void doSourceAction(){
        if(clearFlag.isSelected() == true)
            sourceField.clear();
    }

    @FXML
    private void doClearAction(){
        if(clearFlag.isSelected() == true)
        sourceField.clear();
    }

    // クリップボードから貼り付け処理
    @FXML
    protected void doPasteAction() throws IOException, UnsupportedFlavorException {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // Clipboardからテキストを取得
        String result = (String)clip.getData(DataFlavor.stringFlavor);
        sourceField.setText(result);
    }

    // クリップボードにコピーする処理
    @FXML
    protected void doCopyAction(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(resultField.getText());
        clipboard.setContents(selection, selection);
    }
}
