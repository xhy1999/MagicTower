package score;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ScoreApplication extends Application {

    private static final int SCORE_WINDOW_WIDTH = 400;
    private static final int SCORE_WINDOW_HEIGHT = 350;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/score.fxml"));
        primaryStage.setTitle("最终得分");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/image/icon/mt.png").toString()));
        //设置窗口样式
        primaryStage.initStyle(StageStyle.DECORATED);
        //窗口不可改变高度 宽度
        primaryStage.setResizable(false);
        //设置窗口透明的
        //primaryStage.setOpacity(0.3);
        //窗口置顶
        //primaryStage.setAlwaysOnTop(true);
        Scene scene = new Scene(root, SCORE_WINDOW_WIDTH, SCORE_WINDOW_HEIGHT);
        scene.getStylesheets().addAll(this.getClass().getResource("/view/score.css").toExternalForm());
        // 将场景添加到窗口
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
