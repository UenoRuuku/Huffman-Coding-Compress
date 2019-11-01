package RuukuGFW.app;

import javafx.animation.AnimationTimer;

public class Engine {
    private  final Timer timer;

    private double nowNanos;
    private double lastNanos;
    private double deltaNanos;

    //Nanos per Frame
    private double npf;
    private double fps;

    onUpdate onUpdate;
    onStart onStart;
    onStop onStop;

    public Engine(){
        this(60);
    }

    public Engine(double fps){
        timer = new Timer();
        setFps(fps);
    }

    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
        this.npf = 1 / fps * 1E9;
    }

    public double getDeltaNanos() {
        return deltaNanos;
    }

    public double getNowNanos() {
        return nowNanos;
    }

    public double getLastNanos() {
        return lastNanos;
    }

    public double getNowMillis(){
        return nowNanos * 1E-6;
    }

    public double getNowSecs(){
        return nowNanos * 1E-9;
    }

    public double getLastMillis(){
        return lastNanos * 1E-6;
    }

    public double getLastSecs(){
        return lastNanos * 1E-9;
    }

    public double getdeltaMillis(){
        return deltaNanos * 1E-6;
    }

    public double getdeltaSecs(){
        return deltaNanos * 1E-9;
    }

    void  start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }


    private final class Timer extends AnimationTimer {
        @Override
        public void handle(long now) {
            nowNanos = now;
            if(lastNanos > 0) {
                deltaNanos += nowNanos - lastNanos;
            }
            lastNanos = nowNanos;

            if (deltaNanos >= npf) {
                if (onUpdate != null){
                    onUpdate.handle(deltaNanos);
                }

                deltaNanos -= npf;
            }
        }

        @Override
        public void start(){
            super.start();
            this.reset();
            if(onStart != null){
                onStart.handle();
            }
        }

        @Override
        public void stop(){
            super.stop();
            this.reset();
            if(onStop != null){
                onStop.handle();
            }
        }

        public void reset(){
            nowNanos = 0;
            lastNanos = 0;
            deltaNanos = 0;
        }
    }



    static interface onUpdate{
        void handle(double time);
    }

    static interface onStart{
        void handle();
    }

    static interface onStop{
        void handle();
    }
}
