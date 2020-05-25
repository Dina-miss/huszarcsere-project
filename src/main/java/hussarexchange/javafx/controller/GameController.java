package hussarexchange.javafx.controller;

import hussarexchange.javafx.main.MyApplication;
import hussarexchange.javafx.model.Table;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;
import hussarexchange.results.GameResult;
import hussarexchange.results.GameResultDao;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
public class GameController {

    @Inject
    private FXMLLoader fxmlLoader;

    @Inject
    private GameResultDao gameResultDao;

    private String playerName;
    private Table gameState;
    private IntegerProperty steps = new SimpleIntegerProperty();
    private Instant startTime;

    @FXML
    private GridPane gameTable;

    @FXML
    private Label stepsLabel;

    @FXML
    private Label timeCount;

    private Timeline stopWatchTimeline;

    @FXML
    private Button resetButton;

    @FXML
    private Button giveUpButton;

    @FXML
    private Label nextHussar;

    private BooleanProperty gameOver = new SimpleBooleanProperty();

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private int selectedRow = -1;
    private int selectedCol = -1;
    private int selectedColor = -1;
    private int previousColor = -1;


    @FXML
    public void initialize() {
        gameOver.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                log.info("Game is over");
                log.debug("Saving result to database...");
                gameResultDao.persist(createGameResult());
                stopWatchTimeline.stop();
            }
        });
        resetGame();
    }


    public void handleClickOnCube(MouseEvent mouseEvent) {
        int row = GridPane.getRowIndex((Node) mouseEvent.getSource());
        int col = GridPane.getColumnIndex((Node) mouseEvent.getSource());
        if(selectedRow == -1 && selectedCol == -1 && (Table.currentState[row][col] != previousColor || previousColor == -1)) {
            selectedRow = row;
            selectedCol = col;
            selectedColor = Table.currentState[row][col];
        }
        else {
            log.info("Index ({}, {}) is chosen.", row, col);
            if(selectedColor == previousColor) {
                handleClickOnCube(mouseEvent);
            }
            if (!gameState.isGoal() && gameState.canStepInto(selectedRow, selectedCol, row, col)) {
                previousColor = selectedColor;
                Table.currentState[row][col] = selectedColor;
                Table.currentState[selectedRow][selectedCol] = 0;
                selectedRow = -1;
                selectedCol = -1;
                steps.setValue(steps.get()+1);
                stepsLabel.setText(steps.asString().get());
                nextHussar.setText(previousColor == 1 ? "Piros" : "Kék");
            }
        }
        displayGameState();
        if (gameState.isGoal()) {
            gameOver.setValue(true);
            log.info("Player {} has solved the game in {} steps", playerName, steps.get());
            resetButton.setDisable(true);
            giveUpButton.setText("Vége!");
        }
    }

    private void displayGameState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView view = (ImageView) gameTable.getChildren().get(i * 3 + j);
                if (view.getImage() != null) {
                    log.trace("Image({}, {}) = {}", i, j, view.getImage().getUrl());
                }
                if(Table.currentState[i][j] == 1){
                    view.setImage(MyApplication.blueHussar);
                }
                else if(Table.currentState[i][j] == 2) {
                    view.setImage(MyApplication.redHussar);
                }
                else if(Table.currentState[i][j] == 0) {
                    view.setImage(MyApplication.emptyHussar);
                }

            }
        }
    }


    private void resetGame() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Table.currentState[i][j] = Table.startState[i][j];
            }
            System.out.println();
        }

        steps.setValue(0);
        stepsLabel.setText(steps.asString().get());
        selectedRow = -1;
        selectedCol = -1;
        selectedColor = -1;
        previousColor = -1;
        nextHussar.setText("-");
        startTime = Instant.now();
        gameOver.setValue(false);
        displayGameState();
        createStopWatch();
    }

    public void handleGiveUpButton(ActionEvent actionEvent) throws IOException {
        String buttonText = ((Button) actionEvent.getSource()).getText();
        log.debug("{} is pressed", buttonText);
        if (buttonText.equals("Give Up")) {
            log.info("The game has been given up");
        }
        gameOver.setValue(true);
        log.info("Loading high scores scene...");
        Parent page = (Parent) fxmlLoader.load(getClass().getResource("/fxml/HighScoreScene.fxml"));
        MyApplication.stage.getScene().setRoot(page);
    }


    public void handleResetButton(ActionEvent actionEvent)  {
        log.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        log.info("Resetting game...");
        stopWatchTimeline.stop();
        resetGame();
    }

    private GameResult createGameResult() {
        GameResult result = GameResult.builder()
                .player(playerName)
                .solved(gameState.isGoal())
                .duration(Duration.between(startTime, Instant.now()))
                .steps(steps.get())
                .build();
        return result;
    }

    private void createStopWatch() {
        stopWatchTimeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            long millisElapsed = startTime.until(Instant.now(), ChronoUnit.MILLIS);
            timeCount.setText(DurationFormatUtils.formatDuration(millisElapsed, "HH:mm:ss"));
        }), new KeyFrame(javafx.util.Duration.seconds(1)));
        stopWatchTimeline.setCycleCount(Animation.INDEFINITE);
        stopWatchTimeline.play();
    }

}
