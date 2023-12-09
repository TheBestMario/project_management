import javax.swing.*;

public class MenuHandler {
    //initialises all relevant systems
    private final JFrame frame;
    private projectHandler projectHandler;
    private MainMenuGUI mainMenuGui;
    private NewProjectMenu newProjectMenu;
    private ViewProjectsMenu viewProjectsMenu;
    //private val table: TableHandler = TableHandler(this)

    public MenuHandler(){
        this.frame = new JFrame();
        this.mainMenuGui = new MainMenuGUI();
        this.projectHandler = new projectHandler();
        this.newProjectMenu = new NewProjectMenu();
        this.viewProjectsMenu = new ViewProjectsMenu();
        frame.setContentPane(mainMenuGui.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(701, 701);
        frame.setLocationRelativeTo(null);
    }

    public projectHandler getProjectHandler(){
        return projectHandler;
    }
    public JFrame getFrame(){
        return frame;
    }
    public MainMenuGUI getMainMenuGUI(){
        return mainMenuGui;
    }
    public NewProjectMenu getNewProjectMenu(){
        return newProjectMenu;
    }
    public ViewProjectsMenu getViewProjectsMenu(){
        return viewProjectsMenu;
    }
//    fun getTableHandler(): TableHandler{
//        return table;
//    }
}
