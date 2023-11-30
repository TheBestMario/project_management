import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newProjectMenu{

    private JLabel buildYourProjectLabel;
    private JTextField projectNameField;
    private JLabel projectNameLabel;
    private JButton addTaskButton;
    private JButton submitButton;
    private JLabel projectDescLabel;
    private JPanel panel;
    private JButton backButton;
    private JTextArea textArea1;
    private JTree tree1;

    public newProjectMenu(Builder builder) {
        int Num_Tasks = 0;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                builder.getFrame().setContentPane(builder.getMainMenuGUI().getPanel());
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = projectNameField.getText();
                String Description = textArea1.getText();
                String taskName = "temp name";
                String taskDesc = "temp description";
                builder.getProjectHandler().makeNewProject(Name,Description, Num_Tasks);

                builder.getProjectHandler().getProjectsList().get(builder.getProjectHandler().countProjects()-1).create_task(taskName, taskDesc);
                //returns to home page
                builder.getFrame().setContentPane(builder.getMainMenuGUI().getPanel());
            }
        });

    }
    public JPanel getPanel() {
        return panel;
    }
}
