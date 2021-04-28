package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
            //load new level
        });
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
