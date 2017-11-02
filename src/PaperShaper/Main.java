/**
 * @author @chatagiriii
 * @version 0.0.4
 *
 * JavaFXの練習を兼ねたクリップボードから張り付けた文字列の改行文字を空白文字に置換するモノです
 * 連続作業に備えてウィンドウのオーバレイ表示，フィールドの自動クリア，
 * 見やすい様に2段改行を実装しています
 */

package PaperShaper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        // FXML読み込み
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        // Stage初期設定
        stage.setTitle("コピペ整形くん");
        stage.setScene(new Scene(root, 540, 160));
        stage.setResizable(false);

        // ControllerからStageにアクセス出来るように
        Controller controller = (Controller)loader.getController();
        controller.setThisStage(stage);

        // Stage表示
        stage.show();
    }

    public static void main(String[] args) {
        // start内実行
        launch(args);
    }
}
