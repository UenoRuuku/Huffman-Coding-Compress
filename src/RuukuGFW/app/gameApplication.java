package RuukuGFW.app;

import RuukuGFW.Framework;
import RuukuGFW.UI.gamePane;
import RuukuGFW.UI.gameScene;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;


public class gameApplication {
    private final Stage stage;
    private final gameScene scene;
    private final gamePane root;

    private final HashMap<String, View> viewMap;
    private final ObjectProperty<View> currentView;

    private final Engine engine;

    onLaunch onLaunch;
    onFinish onFinish;
    onExit onExit;


    public gameApplication(Stage stage) {
        this.stage = stage;
        root = new gamePane();
        scene = new gameScene(root);
        viewMap = new HashMap<>();
        currentView = new SimpleObjectProperty<>();
        engine = new Engine();

        stage.setScene(scene);


        initFramework();
        initEngine();
        initApp();
    }

    private final void initFramework() {
        Framework.app = this;
        Framework.engine = engine;
    }

    private final void initEngine() {
        engine.onStart = () -> {
            for (View view : viewMap.values()) {
                view.onStart();
            }
        };

        engine.onUpdate = (time) -> {
            View view = getCurrentView();
            if (view != null) {
                view.onUpdate(time);
            }
        };

        engine.onStop = () -> {
            for (View view : viewMap.values()) {
                view.onStop();
            }
        };

        stage.focusedProperty().addListener((o, ov, nv) -> {
            if (nv) {
                engine.start();
            }
            else {
                engine.stop();
            }
        });
    }

    private final void initApp() {
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            if (onExit != null && !onExit.handle()) {
                e.consume();
            }
        });

        currentView.addListener((o, ov, nv) -> {
            if (ov != null) {
                System.out.println("add2执行！");
                root.getChildren().remove(ov.getPane());
            }
            if (nv != null) {
                root.getChildren().addAll(nv.getPane());
                System.out.println("add执行！");
                nv.onEnter();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public String getTitle() {
        return stage.getTitle();
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public StringProperty titleProperty() {
        return stage.titleProperty();
    }

    public double getWidth(){
        return stage.getWidth();
    }

    public void setWidth(double width){
        stage.setWidth(width);
    }

    public double getHeight(){
        return stage.getHeight();
    }

    public void setHeight(double height){
        stage.setHeight(height);
    }

    public void setIcon(String filePath){
        stage.getIcons().add(new Image(filePath));
    }


    //待补充 其他的stage设置方法

    public gameScene getScene() {
        return scene;
    }

    public View getView(String name) {
        return viewMap.get(name);
    }

    public void regView(String name, View view) {
        viewMap.put(name, view);
        viewMap.get(name).onLaunch();
    }

    public void unregView(String name) {
        View view = viewMap.remove(name);
        if(view != null && view == getCurrentView()){
            currentView.set(null);
        }
    }

    public View getCurrentView() {
        return currentView.get();
    }

    public ReadOnlyObjectProperty<View> getCurrentViewProperty() {
        return currentView;
    }

    public String getCurrentViewName(){
        return currentView.getName();
    }

    public void gotoView(String name) {
        View view = viewMap.get(name);
        if (view != null) {
            view.onStart();
            currentView.set(view);
        }
    }

    public void launch() {

        if (onLaunch != null) {
            onLaunch.handle();
        }
        stage.requestFocus();
        stage.show();
    }

     public void finish() {
        System.out.println();
        if (onFinish != null) {
            onFinish.handle();
        }
        for (View view : viewMap.values()) {
            view.onFinish();
        }
    }

    public void exit() {
        Platform.exit();
    }

    public static interface onLaunch {
        void handle();
    }

    public static interface onFinish {
        void handle();
    }

    public static interface onExit {
        boolean handle();
    }

}
