
class projectHandler(private val menuHandler: menuHandler){

    private var projectsList: MutableList<Project> = mutableListOf()
    private var id = 0


    fun makeNewProject(name: String,description: String, num_Tasks: Int){
        val project = Project(id+1,name,description, num_Tasks)
        project.update_adjMatrix()
        updateListWith(project)
    }
    fun updateListWith(Item: Project){
        projectsList.add(Item)
    }
    fun removeFromList(Item: Project){
        projectsList.remove(Item)
    }
    fun getProjectsList(): MutableList<Project>{
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
    private val taskList: ArrayList<Task> = arrayListOf()
    private var adjMatrix: ArrayList<ArrayList<Boolean>> = arrayListOf()

    fun update_adjMatrix(){
        adjMatrix = arrayListOf()
        num_Tasks = this.taskList.size
        if (num_Tasks == 0)
            println("no tasks")
        else
            for (i in 1..num_Tasks) {
                val tempArray = arrayListOf<Boolean>()
                this.adjMatrix.add(tempArray)
                for (j in 1..num_Tasks) {
                    val isParent = this.taskList[i - 1].getParent()
                    if (isParent == j && isParent != 0) {
                        //sets child connection to parent
                        this.adjMatrix[i - 1].add(true)
                        //sets parent's connection to the child in array
                        this.adjMatrix[j - 1].set(i- 1,true)
                    } else {
                        this.adjMatrix[i - 1].add(false)
                    }
                }
            }
        println(this.adjMatrix)
    }
    fun getAttributeInArray(): Array<String>{

        return arrayOf(name, description, "$num_Tasks")
    }
    override fun toString(): String{
        return "$id, $name, $description, $num_Tasks"
    }
    fun create_task(list: Array<Array<String>>){
        //makes Task Object for each input
        for (i in 0 .. list.size-1){
            val task = Task(list[i][0], i+1,list[i][1], 0)
            taskList.add(task)
        }
        update_adjMatrix()
    }
    fun removeTaskFromList(task: Task){
        taskList.remove(task)
    }


    class Task(
        private var name: String,
        private val id: Int,
        private var desc: String,
        private var parent: Int
    ){
       fun getParent(): Int{
           return parent
       }
        fun getID(): Int{
            return id
        }
    }

}

//class TableHandler(private val menuHandler:menuHandler){
//    //recieves project details from the handler to update visual table in this menu
//    private var list: MutableList<Project> = menuHandler.getProjectHandler().getProjectsList()
//    private var size: Int = 0
//
//    fun firstUpdateTable(){
//        //checks for already existing data and adds to table.
//        list= menuHandler.getProjectHandler().getProjectsList()
//        size= menuHandler.getProjectHandler().countProjects()
//        val array:Array<Project> = list.toTypedArray()
//        for (item in list.indices)
//
//        {
//            //println(list)
//            val data: Array<String> = array[item].getAttributeInArray()
//
//            if (size != 0) {
//                //tablemodel.addRow(arrayOf(data))
//                //println(data[item])
//            }
//        }
//    }
//    fun updateTableAdded(){
//        val project = menuHandler.getProjectHandler().getCurrentObjectAttributes()
//        //tablemodel.addRow(project)
//    }
//}

