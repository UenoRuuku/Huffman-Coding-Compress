package RuukuGFW.app;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class game extends Application {

    private gameApplication app;

    public abstract void onLaunch();

    public void onFinish(){
        //Do sth in subclass
    }

    public boolean onExit(){
        //Do sth in subclass

        return true;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        app = new gameApplication(primaryStage);
        app.onLaunch = this::onLaunch;
        app.onFinish = this::onFinish;
        app.onExit = this::onExit;
        app.launch();
    }

    @Override
    public void stop() throws Exception{
        app.finish();
    }
}
