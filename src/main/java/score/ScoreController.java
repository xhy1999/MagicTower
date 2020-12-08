package score;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.Player;
import entity.Tower;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.TowerPanel;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML private TextField playerNameText;

    @FXML private Label msgLabel;
    @FXML private Label lvLabel;
    @FXML private Label hpLabel;
    @FXML private Label atkLabel;
    @FXML private Label defLabel;
    @FXML private Label expLabel;
    @FXML private Label monLabel;
    @FXML private Label yKeyLabel;
    @FXML private Label bKeyLabel;
    @FXML private Label rKeyLabel;
    @FXML private Label stepLabel;
    @FXML private Label killLabel;
    @FXML private Label killBossLabel;
    @FXML private Label scoreLabel;

    private final static short SLEEP_TIME = 30;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPlayerNameText();
        lvLabel.textProperty().bind(lvService.valueProperty());
        hpLabel.textProperty().bind(hpService.valueProperty());
        atkLabel.textProperty().bind(atkService.valueProperty());
        defLabel.textProperty().bind(defService.valueProperty());
        expLabel.textProperty().bind(expService.valueProperty());
        monLabel.textProperty().bind(monService.valueProperty());
        yKeyLabel.textProperty().bind(yKeyService.valueProperty());
        bKeyLabel.textProperty().bind(bKeyService.valueProperty());
        rKeyLabel.textProperty().bind(rKeyService.valueProperty());
        stepLabel.textProperty().bind(stepService.valueProperty());
        killLabel.textProperty().bind(killService.valueProperty());
        killBossLabel.textProperty().bind(killBossService.valueProperty());
        scoreLabel.textProperty().bind(scoreService.valueProperty());
        lvService.start();
        hpService.start();
        atkService.start();
        defService.start();
        expService.start();
        monService.start();
        yKeyService.start();
        bKeyService.start();
        rKeyService.start();
        stepService.start();
        killService.start();
        killBossService.start();
        scoreService.start();
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

    Service<String> scoreService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    updateValue("A");
                    for (int i = 1; i <= 4; i++) {
                        Thread.sleep(700);
                        switch (i) {
                            case 1:
                                updateValue("A");
                                break;
                            case 2:
                                updateValue("S");
                                break;
                            case 3:
                                updateValue("SS");
                                break;
                            case 4:
                                updateValue("SSS");
                                break;
                        }
                    }
                    return "SSS";
                }
            };
        }
    };

    Service<String> lvService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getLevel() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getLevel() + "";
                }
            };
        }
    };

    Service<String> hpService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getHp() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getHp() + "";
                }
            };
        }
    };

    Service<String> atkService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getAttack() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getAttack() + "";
                }
            };
        }
    };

    Service<String> defService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getDefense() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getDefense() + "";
                }
            };
        }
    };

    Service<String> expService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getExp() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getExp() + "";
                }
            };
        }
    };

    Service<String> monService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getMoney() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getMoney() + "";
                }
            };
        }
    };

    Service<String> yKeyService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getYKey() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getYKey() + "";
                }
            };
        }
    };

    Service<String> bKeyService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getBKey() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getBKey() + "";
                }
            };
        }
    };

    Service<String> rKeyService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getRKey() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getRKey() + "";
                }
            };
        }
    };

    Service<String> stepService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getStepNum() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getStepNum() + "";
                }
            };
        }
    };

    Service<String> killService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getKillNum() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getKillNum() + "";
                }
            };
        }
    };

    Service<String> killBossService = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int i = 1; i <= 100; i++) {
                        updateValue((int) (ScoreApplication.player.getKillBossNum() * (i / 100.0)) + "");
                        Thread.sleep(SLEEP_TIME);
                    }
                    return ScoreApplication.player.getKillBossNum() + "";
                }
            };
        }
    };

    @FXML
    public void clearMsgLabel() {
        Platform.runLater(() -> msgLabel.setText(""));
    }

    @FXML
    public void lvMsg() {
        Platform.runLater(() -> msgLabel.setText("玩家等级"));
    }

    @FXML
    public void hpMsg() {
        Platform.runLater(() -> msgLabel.setText("玩家血量"));
    }

    @FXML
    public void atkMsg() {
        Platform.runLater(() -> msgLabel.setText("玩家攻击"));
    }

    @FXML
    public void defMsg() {
        Platform.runLater(() -> msgLabel.setText("玩家防御"));
    }

    @FXML
    public void expMsg() {
        Platform.runLater(() -> msgLabel.setText("玩家经验"));
    }

    @FXML
    public void monMsg() {
        Platform.runLater(() -> msgLabel.setText("玩家金币"));
    }

    @FXML
    public void yKeyMsg() {
        Platform.runLater(() -> msgLabel.setText("黄钥匙数"));
    }

    @FXML
    public void bKeyMsg() {
        Platform.runLater(() -> msgLabel.setText("蓝钥匙数"));
    }

    @FXML
    public void rKeyMsg() {
        Platform.runLater(() -> msgLabel.setText("红钥匙数"));
    }

    @FXML
    public void moveMsg() {
        Platform.runLater(() -> msgLabel.setText("玩家移动步数"));
    }

    @FXML
    public void killMsg() {
        Platform.runLater(() -> msgLabel.setText("杀敌数"));
    }

    @FXML
    public void killBossMsg() {
        Platform.runLater(() -> msgLabel.setText("击杀Boss数"));
    }

}
