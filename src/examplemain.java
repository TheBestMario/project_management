import javax.swing.*;

public class examplemain {
    public static void main() {
        JFrame frame = new JFrame("System Management");
        //frame.setContentPane(new mainMenuGUI().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}