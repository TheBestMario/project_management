import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class NewProjectMenu implements KeyListener, Menu {
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
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton deleteTaskButton;
    private DefaultTreeModel taskTreeModel;
    private DefaultMutableTreeNode root;
    private Object info;
    private String rootName = "New Project";
    private DefaultMutableTreeNode selectedNode;
    private ProjectHandler projectHandler;
    private MenuHandler menuHandler;
    private SpinnerDateModel spinnerModel1, spinnerModel2;
    private LocalDate today;
    private Project project;

    public NewProjectMenu(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
        this.projectHandler = menuHandler.getProjectHandler();
        //initialises some variables and builds menu
        this.today = projectHandler.getDateToday();
        //sets spinner attributes
        this.spinnerModel1 = new SpinnerDateModel(
                Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()),null,null, Calendar.DAY_OF_MONTH);
        this.spinnerModel2 = new SpinnerDateModel(
                Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()),null,null, Calendar.DAY_OF_MONTH);
        spinner1.setModel(spinnerModel1);
        spinner2.setModel(spinnerModel2);
        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner1,"dd/MM/yy");
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2,"dd/MM/yy");
        spinner1.setEditor(editor1);
        spinner2.setEditor(editor2);

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
                projectHandler.removeFromList(project.getId());
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Project.Task task = project.addTask();
                TaskView taskInput = new TaskView(menuHandler, task, project, "NewProjectMenu");
                taskInput.setVisible(true);
                taskInput.setLocationRelativeTo(menuHandler.getFrame());
                taskInput.setSize(500,500);

            }
        });
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedNode == null || selectedNode == root) {
                    deleteTaskButton.setEnabled(false);
                    return;

                }else if (selectedNode.isLeaf()){
                    info = selectedNode.getUserObject();
                    taskTreeModel.removeNodeFromParent(selectedNode);
                    project.removeTask((Project.Task) info);
                    taskTreeModel.reload();
                }

                else{
                    //removes children of task and then task itself
                    for (int i = 0; i < selectedNode.getChildCount(); i++){
                        DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) selectedNode.getChildAt(i);
                        info = childNode.getUserObject();
                        taskTreeModel.removeNodeFromParent(childNode);
                        project.removeTask((Project.Task) info);
                    }
                    info = selectedNode.getUserObject();
                    taskTreeModel.removeNodeFromParent(selectedNode);
                    project.removeTask((Project.Task) info);
                    taskTreeModel.reload();

                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sequence of what happens after pressing submit
                String Name = projectNameField.getText();
                String Description = projectDescTextArea.getText();
                Date input = (Date) spinner1.getValue();
                Date input2 = (Date) spinner2.getValue();
                LocalDate start = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate end = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //name should be filled out for a valid project
                //sends data to name
                if (Name == null || Name.isEmpty()){
                    return;
                }else{
                    project.setName(Name);
                    project.setDesc(Description);
                    project.updateDate(start, end);
                    //sends data to make tasks for latest project.

                    //updates table after data saved
                    menuHandler.getViewProjectsMenu().addToTable();
                    menuHandler.switchToMainMenu();

                }
            }
        });
    }

    @Override
    public JPanel getPanel() {
        //every time the menu is opened it will reset it and make project template
        return panel;
    }

    @Override
    public JFrame getFrame() {
        return null;
    }

    @Override
    public void display() {
        resetTempData();
        updateDrawList();
        projectHandler.makeNewProject();
        project = projectHandler.getProjectsList().getLast();

    }

    @Override
    public void hide() {
        //hides menu and returns to home page
        resetTempData();
        updateDrawList();
        projectHandler.makeNewProject();
    }

    public void resetTempData(){
        //resets data from previous UI
        info = null;
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
    public void updateModel(Project.Task task){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(task);
        Project.Task parent;

        if (selectedNode == null || selectedNode == root){
            root.add(node);
            project.getTaskList().getLast().setParent(0);

        }else {
            selectedNode.add(node);
            parent = (Project.Task) info;
            System.out.println("PARENT SELECTED: "+ parent.getId());
            project.getTaskList().getLast().setParent(parent.getId());
        }

        taskTreeModel.reload();
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

    public JSpinner getSpinner1() {
        return spinner1;
    }

    public JSpinner getSpinner2() {
        return spinner2;
    }
}