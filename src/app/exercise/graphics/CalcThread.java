package app.exercise.graphics;

public class CalcThread extends Thread {
    CellularFrame frame;

    public CalcThread(CellularFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        frame.setNewPicture();
    }
}
