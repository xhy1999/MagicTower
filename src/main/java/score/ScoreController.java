package score;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundSize.DEFAULT;

public class ScoreController implements Initializable {

    private static final int SCORE_WINDOW_WIDTH = 400;
    private static final int SCORE_WINDOW_HEIGHT = 350;

    @FXML private BorderPane borderPane;
    @FXML private HBox hBox;
    @FXML private TextField playerNameText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBackGround();
        initPlayerNameText();
    }

    public void initBackGround() {
//        BackgroundImage myBI = new BackgroundImage(new Image(this.getClass().getResource("/image/icon/mt.png").toString(),
//                32,32,false,true),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        borderPane.setBackground(new Background(myBI));
//        Image image1 = new Image(this.getClass().getResource("/image/icon/mt.png").toString());
//        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
//        hBox.setBackground(new Background(new BackgroundImage(image1,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                bSize)));
        borderPane.setGraphic();
        hBox.setBackground(new Background(new BackgroundImage(new Image(this.getClass().getResource("/image/wall/floor04_1.png").toString()), REPEAT, NO_REPEAT, CENTER, DEFAULT)));
    }

    //名字长度不超过40位
    public void initPlayerNameText() {
        playerNameText.textProperty().addListener((ov, oldValue, newValue) -> {
            if (playerNameText.getText().length() > 40) {
                String s = playerNameText.getText().substring(0, 40);
                playerNameText.setText(s);
            }
        });
    }



}
