import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class newProjectMenu{

    private JLabel buildYourProjectLabel;
    private JTextField projectNameField;
    private JLabel projectNameLabel;
    private JButton addTaskButton;
    private JButton submitButton;
    private JLabel projectDescLabel;
    private JPanel panel;
    private JButton backButton;
    private JTextArea projectDescTextArea;
    private JList taskList;
    private DefaultListModel<String> taskListModel;
    private JTextField taskName, taskDesc;
    private JPanel taskPanel;

    public newProjectMenu(menuHandler menuHandler) {
        int Num_Tasks = 0;

        taskName = new JTextField();
        taskDesc = new JTextField();
        taskPanel = new JPanel();
        LayoutManager BoxLayout = new BoxLayout(taskPanel, javax.swing.BoxLayout.X_AXIS);
        taskPanel.setLayout(BoxLayout);
        taskPanel.add(taskName);
        taskPanel.add(taskDesc);
        taskListModel = new DefaultListModel<>();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuHandler.getFrame().setContentPane(menuHandler.getMainMenuGUI().getPanel());
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int taskInput = (JOptionPane.showConfirmDialog(menuHandler.getFrame(),taskPanel,
                        "Enter task Name and Description",JOptionPane.OK_CANCEL_OPTION));
                if (taskInput == JOptionPane.OK_OPTION && !taskName.getText().isEmpty()){
                    taskListModel.addElement(taskName.getText());

                    //resets task inputs
                    taskName.setText("");
                    taskDesc.setText("");
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sequence of what happens after pressing submit
                String Name = projectNameField.getText();
                String Description = projectDescTextArea.getText();
                String taskName = "temp name";
                String taskDesc = "temp description";
                String[][] temp = new String[][]{{taskName,taskDesc},{taskName,taskDesc}};
                //sends data to make project
                menuHandler.getProjectHandler().makeNewProject(Name,Description, Num_Tasks);

                //sends data to make tasks for latest project.
                menuHandler.getProjectHandler().getProjectsList().get(
                        menuHandler.getProjectHandler().countProjects()-1).create_task(temp);
                //updates table after data saved
                menuHandler.getViewProjectsMenu().updateTable();
                //resets panel and returns to home page
                projectNameField.setText("");
                projectDescTextArea.setText("");
                menuHandler.getFrame().setContentPane(menuHandler.getMainMenuGUI().getPanel());

            }
        });

    }
    public JPanel getPanel() {
        updateDrawList();
        return panel;
    }
    private void updateDrawList(){
        taskList.setModel(taskListModel);
    }
}
