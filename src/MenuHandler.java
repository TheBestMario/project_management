import javax.swing.*;


public class MenuHandler{
    //initialises all relevant systems
    private Menu menu;
    private final JFrame frame;

    //private val table: TableHandler = TableHandler(this)

    private final MainMenuGUI mainMenuGUI;

    private final ProjectHandler projectHandler;
    private final NewProjectMenu newProjectMenu;
    private final ViewProjectsMenu viewProjectsMenu;
    public MenuHandler() {
        this.mainMenuGUI = new MainMenuGUI(this);
        this.projectHandler = new ProjectHandler();
        this.newProjectMenu = new NewProjectMenu(this);
        this.viewProjectsMenu = new ViewProjectsMenu(this);
        this.frame = initFrame();
        setMenu(mainMenuGUI);
    }
    private JFrame initFrame(){
        JFrame frame = new JFrame("Project Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(701, 701);
        frame.setLocationRelativeTo(null);
        return frame;
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

    public void setMenu(Menu menu){
        if (this.menu != null){
            this.menu.hide();
        }
        this.menu = menu;
        frame.setContentPane(menu.getPanel());
        System.out.println(this.menu);
        this.menu.display();
    }
    public void switchToMainMenu(){
        setMenu(mainMenuGUI);
    }
    public void switchToNewProjectMenu(){
        setMenu(newProjectMenu);
    }
    public void switchToViewProjectsMenu(){
        setMenu(viewProjectsMenu);
    }
    public void openViewingMenu(Project project){
        ViewingMenu viewingMenu = new ViewingMenu(this, project);
    }
}
