import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class mainMenuGUI {
    private JButton setUpNewProjectButton;
    private JButton viewExistingProjectsButton;
    private JButton exitButton;
    private JPanel panel;

    public mainMenuGUI(Builder builder){
        //recieves buildmenu Object as a parameter, so it can access the object it has created (the other GUIs)
        JFrame frame  = builder.getFrame();

        setUpNewProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gets panel from object newprojectmenu from object buildmenu. quite long-winded
                frame.setContentPane(builder.getNewProjectMenu().getPanel());
            }

        });

        viewExistingProjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(builder.getViewProjectsMenu().getPanel());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
