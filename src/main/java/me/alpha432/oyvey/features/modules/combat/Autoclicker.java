import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class AutoClicker extends JFrame implements KeyListener {

    private boolean clicking = false; // whether the autoclicker is active
    private Robot robot;

    public AutoClicker() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        this.setTitle("Autoclicker");
        this.setSize(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);

        // Thread to run the autoclicker loop
        new Thread(() -> {
            while (true) {
                if (clicking) {
                    robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
                    try {
                        Thread.sleep(50); // click every 50ms (20 CPS)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_7) { // toggle on 7
            clicking = !clicking;
            System.out.println("Autoclicker " + (clicking ? "ON" : "OFF"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new AutoClicker();
    }
}
