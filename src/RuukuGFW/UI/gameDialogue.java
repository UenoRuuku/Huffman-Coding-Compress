package RuukuGFW.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


public class gameDialogue extends Pane {

    private Label l = new Label();
    private String text2 = "";
    private int check = 0;

    public gameDialogue() {
        super.setStyle("-fx-background-image:url(assets/textures/gameDialogue.png)");
        super.setLayoutY(400);
        super.setLayoutX(100);
        super.setPrefSize(801, 288);
        super.getChildren().addAll(l);
    }

    public void setText(String text) {
        l.setFont(new Font("Mouse",20));
        l.setTextFill(Paint.valueOf("black"));
        l.setText(text);
        l.setLayoutY(100);
        l.setLayoutX(100);
    }

}
