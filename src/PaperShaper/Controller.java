package PaperShaper;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
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
    private TextArea sourceField,resultField;

    @FXML
    private CheckBox overlayFlag,clearFlag,nlFlag,brFlag;

    // 実行ボタン降下時処理
    @FXML
    protected void doButtonAction(){

        String source = sourceField.getText();

        // 置換処理群
        // 改行文字を空白に置換
        String result = source.replaceAll("\n", " ");
        // 改行後空白削除
        result = result.replaceAll("\n ","\n");
        // e.g.にとりあえず対応
        result = result.replaceAll("e\\.\ng\\.\n","e\\.g\\. ");

        // 改行チェッカー
        // ピリオドで改行
        if(brFlag.isSelected() == true)
            result = result.replaceAll("\\.","\\.\\.end").replaceAll("\\.end","\n");
        if(nlFlag.isSelected() == true)
            //2段改行チェッカー
            result = result.replaceAll("\n","\n\n");

        resultField.setText(result);
    }

    // "ピリオドで改行"が無効の時は2段改行を選択不能に
    @FXML
    protected void doBrAction(){
        if(brFlag.isSelected() == true) {
            nlFlag.setDisable(false);
        }
        else
            nlFlag.setDisable(true);
            nlFlag.setSelected(false);

    }

    // オーバーレイ是非処理
    @FXML
    private void doOverlayAction(){
        if(overlayFlag.isSelected() == true)
            thisStage.setAlwaysOnTop(true);
        else
            thisStage.setAlwaysOnTop(false);
    }

    // ソースクリック時フィールド自動クリア
    @FXML
    private void doSourceAction(){
        if(clearFlag.isSelected() == true)
            sourceField.setText("");
    }

    @FXML
    private void doClearAction(){
        if(clearFlag.isSelected() == true)
        sourceField.setText("");
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
