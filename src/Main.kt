import java.lang.Exception
import java.sql.*
import javax.swing.JFrame

class menuHandler(
){
    //initialises all relevant systems
    private val frame:JFrame = JFrame()
    private val projectHandler: projectHandler = projectHandler(this)
    private val mainMenuGUI: mainMenuGUI = mainMenuGUI(this)
    private val newProjectMenu: newProjectMenu = newProjectMenu(this)
    private val viewProjectsMenu: viewProjectsMenu = viewProjectsMenu(this)
//    private val table: TableHandler = TableHandler(this)

    fun buildMenu(){
        frame.contentPane = mainMenuGUI.panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        frame.isVisible = true
        frame.setSize(701, 701)
        frame.setLocationRelativeTo(null)
    }
    fun getProjectHandler(): projectHandler {
        return projectHandler
    }
    fun getFrame(): JFrame{
        return frame
    }
    fun getMainMenuGUI(): mainMenuGUI {
        return mainMenuGUI
    }
    fun getNewProjectMenu(): newProjectMenu{
        return newProjectMenu
    }
    fun getViewProjectsMenu(): viewProjectsMenu{
        return viewProjectsMenu
    }
//    fun getTableHandler(): TableHandler{
//        return table;
//    }
}
fun main() {
    try {
        val connection = DriverManager.getConnection("jdbc:sqlite:projects_database.db")
        val results = connection.metaData.catalogs
        while (results.next()){
            val databaseName = results.getString(1)
        }
        results.close()
    } catch (e: Exception){
        println("problem occured")
    }
    val run = menuHandler()
    run.buildMenu()
}

