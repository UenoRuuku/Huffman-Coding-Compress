package main;

import RuukuGFW.app.game;

import static RuukuGFW.Framework.*;

import animatefx.animation.JackInTheBox;
import javafx.scene.layout.Pane;
import view.*;

public class mainActivity extends game {

    public boolean finished = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onLaunch() {
        app.setWidth(600);
        app.setHeight(600);
        app.regView("home", new homeView());
        app.regView("help",new HelpView());
        app.gotoView("home");
        app.getStage().setResizable(false);
        app.setIcon("resources/pic/icon.png");
        app.setTitle("RuukuCompress");
        app.getStage().setOnCloseRequest(e -> {
            System.exit(0);
        });
    }
}
