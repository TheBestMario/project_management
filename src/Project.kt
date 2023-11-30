import javax.swing.table.DefaultTableModel

class projectHandler(private val builder: Builder){

    private var projectsList: MutableList<Project> = mutableListOf()
    private var id = 0

    fun updateListWith(Item: Project){
        projectsList.add(Item)
    }
    fun removeFromList(Item: Project){
        projectsList.remove(Item)
    }
    fun makeNewProject(name: String,description: String, num_Tasks: Int){
        val project = Project(id+1,name,description, num_Tasks)
        updateListWith(project)
        System.out.println(project.toString())
        builder.getTableHandler().updateTableAdded()

    }
    fun getProjectsList(): MutableList<Project>{
        println(projectsList)
        return projectsList
    }
    fun getCurrentObjectAttributes(): Array<String> {
        return projectsList.last().getAttributeInArray()
    }
    fun countProjects() : Int{
        return projectsList.size
    }
}

class Project(
    private val id: Int,
    private var name: String,
    private var description: String,
    private var num_Tasks: Int,
)

{
    private lateinit var adjMatrix: Array<IntArray>


    fun update_adjMatrix(parents: Array<IntArray>, id: Int){

        this.adjMatrix
    }
    fun getAttributeInArray(): Array<String>{
        return arrayOf(name, description, "$num_Tasks")
    }
    override fun toString(): String{
        return "$id, $name, $description, $num_Tasks"
    }
    fun create_task(name: String, desc: String){
        val task = Task(name, +1, desc)
        //task_list.add(task)
    }
    fun removeTaskFromList(task: Task){
        //task_list.remove(task)
    }


    class Task(
        private var name: String,
        private val id: Int,
        private var desc: String
    ){
       fun test(){

       }
    }

}

class TableHandler(private val builder:Builder){
    //recieves project details from the handler to update visual table in this menu
    private var list: MutableList<Project> = builder.getProjectHandler().getProjectsList()
    private var columnNames = arrayOf("Project Name", "Description", "Num. Tasks")
    private var ourmodel:DefaultTableModel = DefaultTableModel(columnNames, 0)
    private var size: Int = 0

    fun firstUpdateTable(){
        //checks for already existing data and adds to table.
        list= builder.getProjectHandler().getProjectsList()
        size= builder.getProjectHandler().countProjects()
        var array:Array<Project> = list.toTypedArray()
        for (item in list.indices)
        {
            //println(list)
            var data: Array<String> = array[item].getAttributeInArray()

            if (size != 0) {
                ourmodel.addRow(arrayOf(data))
                //println(data[item])
            }
        }
    }
    fun updateTableAdded(){
        val project = builder.getProjectHandler().getCurrentObjectAttributes()
        ourmodel.addRow(project)
    }

    fun getmodel(): DefaultTableModel{
        return ourmodel
    }
}

