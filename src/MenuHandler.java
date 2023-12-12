import javax.swing.*;

public class MenuHandler {
    //initialises all relevant systems
    private final JFrame frame;

    //private val table: TableHandler = TableHandler(this)

    private final MainMenuGUI mainMenuGUI;

    private final ProjectHandler projectHandler;
    private final NewProjectMenu newProjectMenu;
    private final ViewProjectsMenu viewProjectsMenu;
    public MenuHandler(){
        this.mainMenuGUI = new MainMenuGUI(this);
        this.projectHandler = new ProjectHandler();
        this.newProjectMenu = new NewProjectMenu(this);
        this.viewProjectsMenu = new ViewProjectsMenu(this);
        this.frame = new JFrame();
        frame.setContentPane(mainMenuGUI.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(701, 701);
        frame.setLocationRelativeTo(null);
    }

    public ProjectHandler getProjectHandler(){
        return projectHandler;
    }
    public JFrame getFrame(){
        return frame;
    }
    public MainMenuGUI getMainMenuGUI(){
        return mainMenuGUI;
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
