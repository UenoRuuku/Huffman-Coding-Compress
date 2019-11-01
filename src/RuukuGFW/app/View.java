package RuukuGFW.app;

import RuukuGFW.UI.gamePane;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public abstract class View {
    private final gamePane pane;

     public View(){
         pane = new gamePane();
     }

    public gamePane getPane() {
        return pane;
    }

    public ObservableList<Node> getChildren(){
         return pane.getChildren();
    }

    public abstract void onLaunch();

    public void onEnter(){
        //Do sth in subclass
    }

    public void onStart(){
        //Do sth in subclass
    }

    public void onStop(){
        //Do sth in subclass
    }

    public void onUpdate(double time){

    }

    public void onLeave(){
        //Do sth in subclass
         }

    public void onFinish(){
        //Do sth in subclass
    }
}
