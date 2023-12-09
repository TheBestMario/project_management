import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NewProjectMenu extends MenuHandler implements KeyListener {
    private JLabel buildYourProjectLabel;
    private JTextField projectNameField;
    private JLabel projectNameLabel;
    private JButton addTaskButton;
    private JButton submitButton;
    private JLabel projectDescLabel;
    private JPanel panel;
    private JButton backButton;
    private JTextArea projectDescTextArea;
    private JTree tree;
    //private JList taskList;
    //private DefaultListModel<String> taskListModel;
    private DefaultTreeModel taskTreeModel;
    private JTextField taskName, taskDesc;
    private JPanel taskPanel;
    public ArrayList<String[]> temp;
    private DefaultMutableTreeNode root;
    private Object info;
    private String rootName;
    private DefaultMutableTreeNode selectedNode;
    public NewProjectMenu() {
        //initialises some variables and builds menu
        rootName = "New Project";
        taskName = new JTextField();
        taskDesc = new JTextField();
        taskPanel = new JPanel();
        LayoutManager BoxLayout = new BoxLayout(taskPanel, javax.swing.BoxLayout.LINE_AXIS);
        taskPanel.setLayout(BoxLayout);
        taskPanel.add(taskName);
        taskPanel.add(taskDesc);
        //taskListModel = new DefaultListModel<>();
        root = new DefaultMutableTreeNode("New Project");
        taskTreeModel = new DefaultTreeModel(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        projectNameField.addKeyListener(this);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) return;
                else{
                    info = selectedNode.getUserObject();
                    System.out.println(info);
                }
            }

        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setContentPane(getMainMenuGUI().getPanel());
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int taskInput = (JOptionPane.showConfirmDialog(getFrame(),taskPanel,
                        "Enter task Name and Description",JOptionPane.OK_CANCEL_OPTION));
                if (taskInput == JOptionPane.OK_OPTION && !taskName.getText().isEmpty()){
                    //adds task info into UI elements
                    //taskListModel.addElement(taskName.getText());
                    //adds task made to temp list with attributes Name, Description, Parent(parent's ID)
                    temp.add(new String[]{taskName.getText(),taskDesc.getText(),String.valueOf(1)});
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(taskName.getText());
                    if (info == null || info == root.getUserObject()){
                        root.add(node);
                    }else {
                        selectedNode.add(node);

                    }
                    taskTreeModel.reload();
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

                //sends data to make project
                getProjectHandler().makeNewProject(Name,Description);

                //sends data to make tasks for latest project.
                if (temp != null){
                    getProjectHandler().getProjectsList().get(
                            getProjectHandler().countProjects()-1).create_task(temp);
                }
                //updates table after data saved
                getViewProjectsMenu().updateTable();
                //returns to home page
                getFrame().setContentPane(getMainMenuGUI().getPanel());

            }
        });

    }



    public JPanel getPanel() {
        //every time the menu is opened it will reset it.
        resetTempData();
        updateDrawList();
        return panel;
    }
    public void resetTempData(){
        //resets data from previous UI
        //taskListModel.removeAllElements();
        rootName = "New Project";
        projectNameField.setText("");
        projectDescTextArea.setText("");
        root.removeAllChildren();
        tree.setModel(null);
        taskTreeModel.reload();
        temp = new ArrayList<String[]>();
    }
    private void updateDrawList(){
        tree.setModel(taskTreeModel);
        //taskList.setModel(taskListModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        rootName = projectNameField.getText();
        System.out.println(rootName);
        root.setUserObject(rootName);
        taskTreeModel.reload();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}