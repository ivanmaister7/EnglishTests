package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Controller {
    String answer;

    @FXML
    private MenuBar menu;

    @FXML
    private Label labelQuestion;

    @FXML
    private Label labelAdd;

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
    void initialize(){
        nextLevel();
        btnOk.setOnAction(actionEvent -> {
            RadioButton selected = (RadioButton) ans.getSelectedToggle();
            if(selected != null){
                if(selected.getText()==answer){
                    nextLevel();
                    radio1.setBackground(null);
                    radio2.setBackground(null);
                    radio3.setBackground(null);
                    radio4.setBackground(null);
                    selected.setSelected(false);
                } else {
                    selected.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                }
            }
        });
        btnSkip.setOnAction(actionEvent -> {
            //load new level + skip
        });
        menu.getMenus().get(0).getItems().get(0).setOnAction(actionEvent -> {
            //go to menu
            setLevelView(false);
            setMenuView(true);
            setAddView(false);
        });
        menu.getMenus().get(0).getItems().get(1).setOnAction(actionEvent -> {
            //restart level
        });
        btnStart.setOnAction(actionEvent -> {
            setLevelView(true);
            setMenuView(false);
        });
        btnAdd.setOnAction(actionEvent -> {
            setMenuView(false);
            setAddView(true);
        });
        btnSave.setOnAction(actionEvent -> {
           // add to database
        });
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
    }

}
