import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenuGUI implements ActionListener{
    private JButton setUpNewProjectButton;
    private JButton viewExistingButton;
    private JButton exitButton;
    private JPanel panel;
    private MenuHandler menuHandler;
    public MainMenuGUI(MenuHandler menuHandler){
        this.menuHandler = menuHandler;
        System.out.println("HELLo");
        //recieves MenuHandler object as a parameter, so it can access the functions that lead to other GUIS.
        //listens to see if any of the buttons are pressed on this panel
        setUpNewProjectButton.addActionListener(this);
        viewExistingButton.addActionListener(this);
        exitButton.addActionListener(this);

    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checks what button was pressed
        //I wanted to use case here but when you do case(button) it says that the button isn't a constant
        if (e.getSource().equals(setUpNewProjectButton)) {
            //gets panel from object newprojectmenu from object buildmenu. quite long-winded
            menuHandler.getFrame().setContentPane(menuHandler.getNewProjectMenu().getPanel());
            menuHandler.getFrame().setSize(699,699);
        } else if (e.getSource().equals(viewExistingButton)) {
            menuHandler.getFrame().setContentPane(menuHandler.getViewProjectsMenu().getPanel());
            menuHandler.getFrame().setSize(700,700);

        } else if (e.getSource().equals(exitButton)) {
            System.exit(0);
        }
    }
}


