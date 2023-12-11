import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NewProjectMenu implements KeyListener {
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
    private DefaultTreeModel taskTreeModel;
    private JTextField taskName, taskDesc;
    private JPanel taskPanel;
    private DefaultMutableTreeNode root;
    private Object info;
    private String rootName;
    private DefaultMutableTreeNode selectedNode;
    private ProjectHandler projectHandler;
    private MenuHandler menuHandler;

    public NewProjectMenu(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
        this.projectHandler = menuHandler.getProjectHandler();
        //initialises some variables and builds menu
        rootName = "New Project";
        taskName = new JTextField();
        taskDesc = new JTextField();
        taskPanel = new JPanel();
        LayoutManager BoxLayout = new BoxLayout(taskPanel, javax.swing.BoxLayout.LINE_AXIS);
        taskPanel.setLayout(BoxLayout);
        taskPanel.add(taskName);
        taskPanel.add(taskDesc);

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
                    taskTreeModel.getIndexOfChild(root,selectedNode);
                }
            }

        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuHandler.getFrame().setContentPane(menuHandler.getMainMenuGUI().getPanel());
                projectHandler.removeFromList(projectHandler.getProjectsList().getLast().getId());
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int taskInput = (JOptionPane.showConfirmDialog(menuHandler.getFrame(),taskPanel,
                        "Enter task Name and Description",JOptionPane.OK_CANCEL_OPTION));


                if (taskInput == JOptionPane.OK_OPTION && !taskName.getText().isEmpty()){
                    //makes template task
                    Project.Task task = projectHandler.getProjectsList().getLast().createTask();
                    //adds task info into UI elements
                    //taskListModel.addElement(taskName.getText());
                    //adds task made to temp list with attributes Name, Description, Parent(parent's ID)
                    projectHandler.getProjectsList().getLast().getTaskList().getLast().setName(taskName.getText());
                    projectHandler.getProjectsList().getLast().getTaskList().getLast().setDesc(taskDesc.getText());
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(task);
                    Project.Task parent;

                    if (info == null || info == root.getUserObject()){
                        root.add(node);
                        projectHandler.getProjectsList().getLast().getTaskList().getLast().setParent(0);

                    }else {
                        selectedNode.add(node);
                        parent = (Project.Task) info;
                        System.out.println("PARENT SELECTED: "+ parent.getId());
                        projectHandler.getProjectsList().getLast().getTaskList().getLast().setParent(parent.getId());
                    }

                    taskTreeModel.reload();
                    //resets task inputs
                    taskName.setText("");
                    taskDesc.setText("");
                }
                else if (taskInput == JOptionPane.CANCEL_OPTION){
                }

            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sequence of what happens after pressing submit
                String Name = projectNameField.getText();
                String Description = projectDescTextArea.getText();


                //sends data to update project
                if (Name == null || Name.isEmpty()){
                    return;
                }else projectHandler.getProjectsList().getLast().setName(Name);
                projectHandler.getProjectsList().getLast().setDesc(Description);

                //sends data to make tasks for latest project.

                //updates table after data saved
                menuHandler.getViewProjectsMenu().addToTable();
                //returns to home page
                menuHandler.getFrame().setContentPane(menuHandler.getMainMenuGUI().getPanel());

            }
        });
    }

    public JPanel getPanel() {
        //every time the menu is opened it will reset it and make project template
        resetTempData();
        updateDrawList();
        projectHandler.makeNewProject();
        return panel;
    }
    public void resetTempData(){
        //resets data from previous UI
        rootName = "New Project";
        root.setUserObject(rootName);
        projectNameField.setText(null);
        projectDescTextArea.setText(null);
        root.removeAllChildren();
        tree.setModel(null);
        taskTreeModel.reload();
    }
    private void updateDrawList(){
        tree.setModel(taskTreeModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        rootName = projectNameField.getText();
        root.setUserObject(rootName);
        taskTreeModel.reload();
    }
}