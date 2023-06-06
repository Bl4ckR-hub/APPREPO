package app.exercise.graphics;

public class ShowingThread extends Thread {
    CellularFrame frame;

    public ShowingThread(CellularFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        frame.setPicture();
    }
}
