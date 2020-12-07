package score;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML
    private TextField playerNameText;

    @FXML
    private Label msgLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPlayerNameText();
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

    @FXML
    public void clearMsgLabel() {
        msgLabel.setText("");
    }

    @FXML
    public void moveMsg() {
        msgLabel.setText("玩家移动步数");
    }

}
