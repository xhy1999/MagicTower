package score;

import entity.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.TowerPanel;

import java.io.IOException;

public class ScoreApplication extends Application {

    protected static Player player;

    public static void main(String[] args) {
        player = new Player();
        player.calculateScore();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = FXMLLoader.load(this.getClass().getResource("/view/score.fxml"));
        primaryStage.setTitle("最终得分");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/image/icon/mt.png").toString()));
        primaryStage.setX(TowerPanel.screenUtil.getScreenWidth() / 3 + TowerPanel.WINDOW_WIDTH);
        primaryStage.setY(TowerPanel.screenUtil.getScreenWidth() / 5);
        //设置窗口样式
        primaryStage.initStyle(StageStyle.DECORATED);
        //窗口不可改变高度 宽度
        primaryStage.setResizable(false);
        //设置窗口透明的
        //primaryStage.setOpacity(0.3);
        //窗口置顶
        //primaryStage.setAlwaysOnTop(true);
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("/view/score.css").toExternalForm());
        // 将场景添加到窗口
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.player.calculateScore();
    }

}
