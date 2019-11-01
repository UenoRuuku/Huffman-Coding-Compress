package RuukuGFW.UI;


import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class RuukuButton extends Button {
    public RuukuButton(String label){
        super.setCursor(new ImageCursor(new Image("assets/textures/cursorChosen.png")));
        super.setOnMouseClicked(e -> {
            super.setStyle("-fx-background-image:url(assets/textures/buttonClicked.png);-fx-background-color: transparent ");
            super.setBackground(null);
        });
        super.setText(label);
        init();
    }

    public RuukuButton(String label, boolean judge){
        if(judge) {
            super.setCursor(new ImageCursor(new Image("assets/textures/cursorChosen.png")));
            super.setOnMouseClicked(e -> {
                super.setStyle("-fx-background-image:url(assets/textures/buttonClicked.png);-fx-background-color: transparent ");
                super.setBackground(null);
            });
        }else{

            super.setCursor(new ImageCursor(new Image("assets/textures/cursorForbidden.png")));
        }
        super.setText(label);
        init();
    }

    public void init(){
        super.setPrefSize(250,75);
        super.setFont(new Font("Mouse",20));
        super.setTextFill(Paint.valueOf("white"));
        super.setStyle("-fx-background-color: transparent ");
        super.setOnMouseEntered(e -> {
            super.setStyle("-fx-background-image:url(assets/textures/buttonSelected.png);-fx-background-color: transparent ");
            super.setBackground(null);
            super.setFont(new Font("Mouse",27));
        });
        super.setOnMouseExited(e -> {
            super.setStyle("-fx-background-image:null;-fx-background-color: transparent ");
            super.setFont(new Font("Mouse",20));
        });



    }
}
