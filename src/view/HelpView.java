package view;

import RuukuGFW.app.View;
import animatefx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

import static RuukuGFW.Framework.app;

public class HelpView extends View {
    @Override
    public void onLaunch() {
        Font font = Font.loadFont("file:/C:/Users/Ruuku‘s PC/IdeaProjects/RuukuCompress/src/resources/font/Mouse.otf", 24);
        Font font2 = Font.loadFont("file:/C:/Users/Ruuku‘s PC/IdeaProjects/RuukuCompress/src/resources/font/Mouse.otf", 15);

        DropShadow dropshadow = new DropShadow();
        dropshadow.setColor(Color.WHITE);
        dropshadow.setOffsetX(0);
        dropshadow.setOffsetY(0);
        dropshadow.setRadius(30.0);

        Button back = new Button("back");
        back.setLayoutX(390);
        back.setLayoutY(470);
        back.setOnMouseEntered(e->{
            back.setEffect(dropshadow);
            new Flash(back).play();
        });
        back.setOnMouseExited(e->{
            back.setEffect(null);
        });
        back.setBackground(null);
        back.setFont(font);
        back.setTextFill(Paint.valueOf("white"));
        back.setOnMouseClicked(e->{
            Circle pane = new Circle();
            pane.setRadius(25);
            pane.setFill(Paint.valueOf("Red"));
            pane.setLayoutX(e.getSceneX());
            pane.setLayoutY(e.getSceneY());
            getChildren().addAll(pane);
            new ZoomIn(pane).play();
            new FadeOut(pane).play();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(()->{
                        getChildren().remove(pane);
                    });
                }
            },1000);
            app.gotoView("home");
        });

        Text text = new Text();
        text.setFill(Paint.valueOf("white"));
        Font Chinese = Font.loadFont("file:/C:/Users/Ruuku‘s PC/IdeaProjects/RuukuCompress/src/resources/font/方正像素字体.ttf",17);
        text.setFont(Chinese);
        text.setText("本产品基于霍夫曼树编码实现了文件和文件夹的压缩，\n同时利用javafx制作了一个简约的GUI界面。\n" +
                " \n" +
                "Compress标签旁边的两个按钮分别对应了压缩文件和文件夹\n" +
                "Decompress标签旁的按钮对应了解压，只接受.Ru格式的压\n缩文件。\n*如果强行运行会跳转到正常页面，但不会有任何效果。\n" +
                "选择文件之后在对应的标签下方会显示文件路径，之后点击\nrun按钮就会执行相应的命令。\n" +
                "\n");

        Button English = new Button("English Version");
        Button China = new Button("Chinese Version");


        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPrefHeight(470);
        vb.setPrefWidth(500);
        vb.setSpacing(20);
        vb.setLayoutX(50);
        vb.setLayoutY(50);
        vb.getChildren().addAll(text,English);
        vb.setStyle("-fx-background-image:url('resources/pic/background.png')");





        Text text2 = new Text();
        text2.setFill(Paint.valueOf("white"));
        text2.setFont(font2);
        text2.setText("The product, which mainly based on Huffman Tree Coding，\nmanaged to realize the Compression and Depression of \nFiles and Directories" +
                "\nThe GUI is based on JAVAFX." +
                " \n" +
                "\n"+
                "Buttons beside the Compress Button refer to Compress File \nand Directory respectively\n" +
                "The Button beside the Depress Button refer to Depress File.\n Now only .Ru files are available\n*If forced to run, the program will go as usual \nbut return nothing\n" +
                "press run to start your operation.\n" +
                "\n");
        VBox vb2 = new VBox();
        vb2.setAlignment(Pos.CENTER);
        vb2.setPrefHeight(470);
        vb2.setPrefWidth(500);
        vb2.setSpacing(20);
        vb2.setLayoutX(50);
        vb2.setLayoutY(50);
        vb2.getChildren().addAll(text2,China);
        vb2.setStyle("-fx-background-image:url('resources/pic/background.png')");

        English.setOnMouseEntered(e->{
            English.setEffect(dropshadow);
            new Flash(English).play();
        });
        English.setOnMouseExited(e->{
            English.setEffect(null);
        });
        English.setBackground(null);
        English.setFont(font);
        English.setTextFill(Paint.valueOf("white"));
        English.setOnMouseClicked(e->{
            Circle pane = new Circle();
            pane.setRadius(25);
            pane.setFill(Paint.valueOf("Red"));
            pane.setLayoutX(e.getSceneX());
            pane.setLayoutY(e.getSceneY());
            getChildren().addAll(pane);
            new ZoomIn(pane).play();
            new FadeOut(pane).play();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(()->{
                        getChildren().remove(pane);
                    });
                }
            },1000);
            new ZoomIn(vb2).play();
            getChildren().removeAll(vb,back);
            getChildren().addAll(vb2,back);
        });

        China.setOnMouseEntered(e->{
            China.setEffect(dropshadow);
            new Flash(China).play();
        });
        China.setOnMouseExited(e->{
            China.setEffect(null);
        });
        China.setBackground(null);
        China.setFont(font);
        China.setTextFill(Paint.valueOf("white"));
        China.setOnMouseClicked(e->{
            Circle pane = new Circle();
            pane.setRadius(25);
            pane.setFill(Paint.valueOf("Red"));
            pane.setLayoutX(e.getSceneX());
            pane.setLayoutY(e.getSceneY());
            getChildren().addAll(pane);
            new ZoomIn(pane).play();
            new FadeOut(pane).play();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(()->{
                        getChildren().remove(pane);
                    });
                }
            },1000);
            new ZoomIn(vb).play();
            getChildren().removeAll(vb2,back);
            getChildren().addAll(vb,back);
        });


        getChildren().addAll(vb,back);


        getPane().setStyle("-fx-background-image:url('resources/pic/back3.gif')");
        getPane().setOnMouseClicked(event -> {
            Circle pane = new Circle();
            pane.setRadius(25);
            int ran = (int)(Math.random()*7);
            String color = "red";
            switch (ran){
                case 0:
                    color = "red";
                    break;
                case 1:
                    color = "orange";
                    break;
                case 2:
                    color = "yellow";
                    break;
                case 3:
                    color = "aqua";
                    break;
                case 4:
                    color = "pink";
                    break;
                case 5:
                    color = "blue";
                    break;
                case 6:
                    color = "yellowgreen";
                    break;
                default:
            }
            pane.setFill(Paint.valueOf(color));
            pane.setLayoutX(event.getSceneX());
            pane.setLayoutY(event.getSceneY());
            getChildren().addAll(pane);
            new ZoomIn(pane).play();
            new FadeOut(pane).play();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(()->{
                        getChildren().remove(pane);
                    });
                }
            },1000);
        });
    }

    @Override
    public void onStart(){
        new FadeIn(getPane()).play();
    }
    @Override
    public void onLeave(){
        new FadeOut(getPane()).play();
    }
}
