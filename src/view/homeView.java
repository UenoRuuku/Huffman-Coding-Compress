package view;

import RuukuGFW.app.View;
import animatefx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import method.operator;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static RuukuGFW.Framework.app;

public class homeView extends View {

    private char[] compressRoute;
    private String compressStr = "";
    private boolean compressPrint = false;
    private char[] depressRoute;
    private String depressStr = "";
    private boolean depressPrint = false;
    private Label l1 = new Label("");
    private Label l2 = new Label("");
    private int i1 = 0;
    private int i2 = 0;


    private File selectedFile = null;
    private operator op = new operator();
    private static long startTime;
    private static long endTime;

    private static long startSize;
    private static long endSize;

    private static Thread thread;

    private static ProgressIndicator pI = new ProgressIndicator();

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }



    @Override
    public void onLaunch() {

        pI.setStyle("-fx-margin:20px;");

        Font font = Font.loadFont("file:/C:/Users/Ruuku‘s PC/IdeaProjects/RuukuCompress/src/resources/font/Mouse.otf", 24);
        Font font2 = Font.loadFont("file:/C:/Users/Ruuku‘s PC/IdeaProjects/RuukuCompress/src/resources/font/Mouse.otf", 15);
        //        Font font = new Font(100);
        Text t = new Text("Ruuku Compress");
        t.setLayoutY(20);
        t.setFont(font);
        t.setFill(Paint.valueOf("white"));



        Text t2 = new Text("Compress");
        t2.setFont(font);
        t2.setFill(Paint.valueOf("white"));


        Text t3 = new Text("Decompress");
        t3.setFont(font);
        t3.setFill(Paint.valueOf("white"));

        Text t4 = new Text("Progress");

        t4.setFont(font);
        t4.setFill(Paint.valueOf("white"));

        Text t5 = new Text("");
        Text t6 = new Text("");
        Text t7 = new Text("");

        t5.setFont(font);
        t5.setFill(Paint.valueOf("white"));

        t6.setFont(font);
        t6.setFill(Paint.valueOf("white"));

        t7.setFont(font);
        t7.setFill(Paint.valueOf("white"));

        Text t8 = new Text("");

        t8.setFont(font);
        t8.setFill(Paint.valueOf("white"));

        Text t9 = new Text("Loading……Please don't press the back button,\n or some mistakes will happen.");
        t9.setFont(font2);
        t9.setFill(Paint.valueOf("white"));

        Button b1 = new Button("file");
        Button b2 = new Button("file");
        Button b3 = new Button("run"); //压缩
        Button b4 = new Button("dir");
        Button b5 = new Button("run"); //压缩文件夹
        Button b6 = new Button("run"); //解压
        Button b7 = new Button("back");//返回
        Button help = new Button("Need Help?");

        l1.setTextFill(Paint.valueOf("black"));

        Text a1 = new Text("->");

        a1.setFont(font);
        a1.setFill(Paint.valueOf("white"));
        a1.setOpacity(0);

        Text a2 = new Text("->");

        a2.setFont(font);
        a2.setFill(Paint.valueOf("white"));
        a2.setOpacity(0);

        Text a4 = new Text("->");

        a4.setFont(font);
        a4.setFill(Paint.valueOf("white"));
        a4.setOpacity(0);

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setPrefWidth(500);
        h1.setSpacing(20);
        HBox h11 = new HBox();
        h11.getChildren().addAll(a1,b1);
        h11.setAlignment(Pos.CENTER);
        h11.setSpacing(-10);
        HBox h12 = new HBox();
        h12.getChildren().addAll(a4,b4);
        h12.setAlignment(Pos.CENTER);
        h12.setSpacing(-10);
        h1.getChildren().addAll(t2, h11, h12);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setPrefWidth(500);
        h2.setSpacing(20);
        HBox h22 = new HBox();
        h22.getChildren().addAll(a2,b2);
        h22.setSpacing(-10);
        h22.setAlignment(Pos.CENTER);
        h2.getChildren().addAll(t3,h22);


        HBox h4 = new HBox();
        h4.setAlignment(Pos.CENTER);
        h4.setPrefWidth(500);
        h4.setSpacing(20);

        HBox h5 = new HBox();
        h5.setAlignment(Pos.CENTER);
        h5.setPrefWidth(500);

        HBox h6 = new HBox();
        h6.setAlignment(Pos.CENTER);
        h6.setPrefWidth(500);

        VBox h7 = new VBox();
        h7.setPrefWidth(500);
        h7.setSpacing(20);
        h7.setAlignment(Pos.CENTER);
        h7.getChildren().addAll(pI, t9);


        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPrefHeight(470);
        vb.setPrefWidth(500);
        vb.setSpacing(20);
        vb.setLayoutX(50);
        vb.setLayoutY(50);
        vb.getChildren().addAll(h1, l1, h2, l2, h4);
        vb.setStyle("-fx-background-image:url('resources/pic/background.png')");

        VBox vb2 = new VBox();
        vb2.setAlignment(Pos.CENTER);
        vb2.setLayoutX(50);
        vb2.setLayoutY(50);
        vb2.setPrefHeight(470);
        vb2.setPrefWidth(500);
        vb2.setSpacing(20);
        vb2.setStyle("-fx-background-image:url('resources/pic/background.png')");

        h5.getChildren().addAll(t5, t6, t7);
        h6.getChildren().addAll(t8);

        vb2.getChildren().addAll(h5, h6, b7);


        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("Open Resource File");


        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Open Resource File");

        DirectoryChooser dc1 = new DirectoryChooser();
        dc1.setTitle("Open Resource Dir");

        DropShadow dropshadow = new DropShadow();
        dropshadow.setColor(Color.WHITE);
        dropshadow.setOffsetX(0);
        dropshadow.setOffsetY(0);
        dropshadow.setRadius(30.0);

        b1.setOnAction(e -> {
            File te = null;
            te = fileChooser1.showOpenDialog(app.getStage());
            if (te != null) {
                if (!h4.getChildren().contains(b3)) {
                    h4.getChildren().clear();
                    h4.getChildren().addAll(b3);
                }
                setSelectedFile(te);
                l2.setText("");
                compressRoute = selectedFile.getAbsolutePath().toCharArray();
                compressPrint = true;
                l1.setFont(font2);
                l1.setStyle("-fx-background-color:white");
            }
        });
        b1.setOnMouseEntered(e->{
            a1.setOpacity(1);
            b1.setEffect(dropshadow);
            new Flash(b1).play();
        });
        b1.setOnMouseExited(e->{
            a1.setOpacity(0);
            b1.setEffect(null);
        });
        b1.setBackground(null);
        b1.setFont(font);
        b1.setTextFill(Paint.valueOf("white"));

        b4.setOnAction(e -> {
            File te4 = null;
            te4 = dc1.showDialog(app.getStage());
            if (te4 != null) {
                if (!h4.getChildren().contains(b5)) {
                    h4.getChildren().clear();
                    h4.getChildren().addAll(b5);
                }
                setSelectedFile(te4);
                l2.setText("");
                compressRoute = selectedFile.getAbsolutePath().toCharArray();
                compressPrint = true;
                l1.setFont(font2);
                l1.setStyle("-fx-background-color:white");
            }
        });
        b4.setOnMouseEntered(e->{
            a4.setOpacity(1);
            b4.setEffect(dropshadow);
            new Flash(b4).play();
        });
        b4.setOnMouseExited(e->{
            a4.setOpacity(0);
            b4.setEffect(null);
        });
        b4.setBackground(null);
        b4.setFont(font);
        b4.setTextFill(Paint.valueOf("white"));

        b2.setOnAction(e -> {
            File te2 = null;
            te2 = fileChooser2.showOpenDialog(app.getStage());
            if (te2 != null) {
                if (!h4.getChildren().contains(b6)) {
                    h4.getChildren().clear();
                    h4.getChildren().addAll(b6);
                }
                setSelectedFile(te2);
                l1.setText("");
                depressRoute = selectedFile.getAbsolutePath().toCharArray();
                depressPrint = true;
                l2.setFont(font2);
                l2.setStyle("-fx-background-color:white");
            }
        });
        b2.setOnMouseEntered(e->{
            a2.setOpacity(1);
            b2.setEffect(dropshadow);
            new Flash(b2).play();
        });
        b2.setOnMouseExited(e->{
            a2.setOpacity(0);
            b2.setEffect(null);
        });
        b2.setBackground(null);
        b2.setFont(font);
        b2.setTextFill(Paint.valueOf("white"));

        b3.setOnMouseClicked(e -> {

            Circle pane = new Circle();
            pane.setRadius(25);
            pane.setFill(Paint.valueOf("Red"));
            pane.setLayoutX(e.getSceneX());
            pane.setLayoutY(e.getSceneY());
            getChildren().addAll(pane);
            new ZoomIn(pane).play();
            new FadeOut(pane).play();

            if (!vb2.getChildren().contains(h7)) {
                vb2.getChildren().remove(b7);
                vb2.getChildren().addAll(h7, b7);
            }
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    String desName = selectedFile.getAbsolutePath();
                    startTime = System.currentTimeMillis();
                    try {
                        op.operateCompress(desName);
                        endTime = System.currentTimeMillis();
                        t6.setText("Duration: " + (endTime - startTime) + " ms");

                        startSize = selectedFile.length();
                        File endFile = new File(desName.substring(0, desName.lastIndexOf(".")) + ".Ru");
                        endSize = endFile.length();
                        t8.setText("CompressPercentage: " + (double) (endSize * 100 / startSize) + "%");
                        h7.setOpacity(0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }
            };
            thread = new Thread(task);
            thread.start();

            new ZoomIn(vb2).play();
            getChildren().removeAll(vb,help);
            getChildren().addAll(vb2,help);
        });
        b3.setOnMouseEntered(e->{
            b3.setEffect(dropshadow);
            new Flash(b3).play();
        });
        b3.setOnMouseExited(e->{
            b3.setEffect(null);
        });
        b3.setBackground(null);
        b3.setFont(font);
        b3.setTextFill(Paint.valueOf("white"));

        b5.setOnMouseClicked(e -> {

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

            if (!vb2.getChildren().contains(h7)) {
                vb2.getChildren().remove(b7);
                vb2.getChildren().addAll(h7, b7);
            }
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    String desName = selectedFile.getAbsolutePath();
                    System.out.println(desName);
                    startTime = System.currentTimeMillis();
                    try {
                        op.operateCompressDir(desName);
                        endTime = System.currentTimeMillis();
                        t6.setText("Duration: " + (endTime - startTime) + " ms");

                        startSize = getDirSize(selectedFile);
                        File endFile = new File(desName + ".Ru");
                        endSize = endFile.length();
                        t8.setText("CompressPercentage: " + (double) (endSize * 100 / startSize) + "%");
                        h7.setOpacity(0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }
            };
            thread = new Thread(task);
            thread.start();
            new ZoomIn(vb).play();
            getChildren().removeAll(vb,help);
            getChildren().addAll(vb2,help);
        });
        b5.setOnMouseEntered(e->{
            b5.setEffect(dropshadow);
            new Flash(b5).play();
        });
        b5.setOnMouseExited(e->{
            b5.setEffect(null);
        });
        b5.setBackground(null);
        b5.setFont(font);
        b5.setTextFill(Paint.valueOf("white"));

        b6.setOnMouseClicked(e -> {

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

            if (!vb2.getChildren().contains(h7)) {
                vb2.getChildren().remove(b7);
                vb2.getChildren().addAll(h7, b7);
            }
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    String desName = selectedFile.getAbsolutePath();
                    startTime = System.currentTimeMillis();
                    try {
                        op.operateDepress(desName);
                        endTime = System.currentTimeMillis();
                        t6.setText("Duration: " + (endTime - startTime) + " ms");
                        h7.setOpacity(0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }
            };
            thread = new Thread(task);
            thread.start();
            h7.setOpacity(1);
            new ZoomIn(vb2).play();
            getChildren().removeAll(vb,help);
            getChildren().addAll(vb2,help);


        });
        b6.setOnMouseEntered(e->{
            b6.setEffect(dropshadow);
            new Flash(b6).play();
        });
        b6.setOnMouseExited(e->{
            b6.setEffect(null);
        });
        b6.setBackground(null);
        b6.setFont(font);
        b6.setTextFill(Paint.valueOf("white"));

        b7.setOnMouseClicked(e -> {

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

            t8.setText("");
            t6.setText("");
            new ZoomIn(vb).play();
            getChildren().removeAll(vb2,help);
            getChildren().addAll(vb,help);
            h7.setOpacity(1);
        });
        b7.setOnMouseEntered(e->{
            b7.setEffect(dropshadow);
            new Flash(b7).play();
        });
        b7.setOnMouseExited(e->{
            b7.setEffect(null);
        });
        b7.setBackground(null);
        b7.setFont(font);
        b7.setTextFill(Paint.valueOf("white"));

        help.setLayoutX(390);
        help.setLayoutY(470);
        help.setOnMouseEntered(e->{
            help.setEffect(dropshadow);
            new Flash(help).play();
        });
        help.setOnMouseExited(e->{
            help.setEffect(null);
        });
        help.setBackground(null);
        help.setFont(font);
        help.setTextFill(Paint.valueOf("white"));
        help.setOnMouseClicked(e->{
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
            app.gotoView("help");
        });


        getChildren().addAll(vb, t , help);
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


        //彩蛋
        Text egg = new Text("You find Touhou");
        t.setOnMouseClicked(e->{
            getPane().setStyle("-fx-background-image:url('resources/pic/back2.gif')");
            getChildren().remove(t);
            getChildren().addAll(egg);
        });
        egg.setLayoutY(20);
        egg.setFont(font);
        egg.setFill(Paint.valueOf("Red"));
        egg.setOnMouseClicked(e->{
            getPane().setStyle("-fx-background-image:url('resources/pic/back3.gif')");
            getChildren().remove(egg);
            getChildren().addAll(t);
        });

    }

    private static long getDirSize(File dir) {
        if (dir.listFiles() == null) {
            return 0;
        }
        File[] files = dir.listFiles();
        long sum = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                sum += getDirSize(file);
            } else {
                sum += file.length();
            }
        }
        return sum;
    }



    @Override
    public void onUpdate(double times) {
        if (compressPrint && i1 < compressRoute.length) {
            compressStr += compressRoute[i1];
            l1.setText(compressStr);
            i1++;
        } else {
            i1 = 0;
            compressStr = "";
            compressPrint = false;
        }
        if (depressPrint && i2 < depressRoute.length) {
            depressStr += depressRoute[i2];
            l2.setText(depressStr);
            i2++;
        } else {
            i2 = 0;
            depressStr = "";
            depressPrint = false;
        }
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
