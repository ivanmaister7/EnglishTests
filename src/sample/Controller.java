package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Controller {
    private String answer;
    private static final int NUMBER_OF_QUESTIONS = 2;
    private double tests = 0.001;
    private boolean first = true;

    @FXML
    private MenuBar menu;

    @FXML
    private Label labelQuestion;

    @FXML
    private Label labelAdd;

    @FXML
    private Label labelResultText;

    @FXML
    private Label labelResult;

    @FXML
    private TextField fieldQuest;

    @FXML
    private TextField fieldAns;

    @FXML
    private ToggleGroup ans;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnSkip;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnRestart;

    @FXML
    private ProgressBar progressBar;

    @FXML
    void initialize(){
        nextLevel();
        btnOk.setOnAction(actionEvent -> {
            RadioButton selected = (RadioButton) ans.getSelectedToggle();
            if(selected != null){
                if(selected.getText()==answer){
                    tests++;
                    if(first){
                        progressBar.setProgress(progressBar.getProgress() + 1.0/NUMBER_OF_QUESTIONS);
                    }
                    if(progressBar.getProgress()<1.0){
                        nextLevel();
                    } else {
                        setLevelView(false);
                        setResultView(true);
                    }
                    clear(selected);
                } else {
                    first = false;
                    selected.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                }
            }
        });
        btnSkip.setOnAction(actionEvent -> {
            nextLevel();
            tests++;
            clear((RadioButton) ans.getSelectedToggle());
        });
        menu.getMenus().get(0).getItems().get(0).setOnAction(actionEvent -> {
            //go to menu
            setMenuView(true);
            setLevelView(false);
            setResultView(false);
            setAddView(false);
        });
        menu.getMenus().get(0).getItems().get(1).setOnAction(actionEvent -> {
           restartTest();
        });
        btnStart.setOnAction(actionEvent -> {
            restartTest();
            setLevelView(true);
            setMenuView(false);
        });
        btnAdd.setOnAction(actionEvent -> {
            setMenuView(false);
            setAddView(true);
        });
        btnRestart.setOnAction(actionEvent -> {
            setLevelView(true);
            setResultView(false);
            restartTest();
        });
        btnSave.setOnAction(actionEvent -> {
           // add to database
        });
    }

    private void restartTest() {
        nextLevel();
        tests = 0;
        progressBar.setProgress(0);
    }

    private void clear(RadioButton selected) {
        radio1.setBackground(null);
        radio2.setBackground(null);
        radio3.setBackground(null);
        radio4.setBackground(null);
        if(selected != null){
            selected.setSelected(false);
        }
    }

    private void setResultView(boolean b) {
        labelResultText.setVisible(b);
        labelResult.setVisible(b);
        labelResult.setText("" + (int)(NUMBER_OF_QUESTIONS * 100/tests) + '%');
        btnRestart.setVisible(b);
    }

    private void setAddView(boolean b) {
        labelAdd.setVisible(b);
        fieldQuest.setVisible(b);
        fieldAns.setVisible(b);
        btnSave.setVisible(b);
    }

    private void setMenuView(boolean b) {
        btnStart.setVisible(b);
        btnAdd.setVisible(b);
    }

    private void setLevelView(boolean b) {
        menu.getMenus().get(0).getItems().get(1).setVisible(b);
        progressBar.setVisible(b);
        labelQuestion.setVisible(b);
        radio1.setVisible(b);
        radio2.setVisible(b);
        radio3.setVisible(b);
        radio4.setVisible(b);
        btnOk.setVisible(b);
        btnSkip.setVisible(b);
    }

    private void nextLevel(){
        Level level = new Level();
        radio1.setText(level.getVariants()[0]);
        radio2.setText(level.getVariants()[1]);
        radio3.setText(level.getVariants()[2]);
        radio4.setText(level.getVariants()[3]);
        labelQuestion.setText(level.getQuestion());
        answer = level.getAnswer();
        first = true;
    }

}
