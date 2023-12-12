import java.time.LocalDate
import java.util.Date

class ProjectHandler() {

    private var projectsList: MutableList<Project> = mutableListOf()
    private var id = 1
    private var dateToday= LocalDate.now()


    fun makeNewProject(){
        val project = Project(id,null,null, dateToday, null)
        id+=1
        updateListWith(project)
    }
    fun getDateToday(): LocalDate{

        return dateToday
    }
    fun updateListWith(Item: Project){
        projectsList.add(Item)
    }
    fun removeFromList(ID:Int){
        for (i in projectsList){
            if (i.getId() == ID)
                projectsList.remove(i)
                break
        }

    }
    fun getProjectsList(): MutableList<Project>{
        return projectsList
    }
    fun getCurrentObjectAttributes(): Array<String?> {
        return projectsList.last().getAttributesArrayForm()
    }
    fun countProjects() : Int{
        return projectsList.size
    }
}

class Project(
    //project params
    private val id: Int,
    private var name: String?,
    private var description: String?,
    private var startDate: LocalDate,
    private var endDate: LocalDate?
)

{
    //initialise variables
    private var num_Tasks: Int = 0
    private val taskList: ArrayList<Task> = arrayListOf()
    private var adjMatrix: ArrayList<ArrayList<Int>> = arrayListOf()
    fun updateDate(start: LocalDate, end: LocalDate?){
        if (start != null)
            this.startDate = start
        if (end != null)
            this.endDate = end
    }
    fun updateAdjMatrix(){
        adjMatrix = arrayListOf()
        if (num_Tasks == 0)
            println("no tasks")
        else
            for (i in 1..num_Tasks) {
                val tempArray = arrayListOf<Int>()
                this.adjMatrix.add(tempArray)
                for (j in 1..num_Tasks) {
                    val isParent = this.taskList[i - 1].getParent()
                    if (isParent == j && isParent != 0) {
                        //sets child connection to parent
                        this.adjMatrix[i - 1].add(1)
                        //sets parent's connection to the child in array
                        this.adjMatrix[j - 1].set(i- 1,1)
                    } else {
                        this.adjMatrix[i - 1].add(0)
                    }
                }
            }
        println(this.adjMatrix)
    }
    fun getAdjMatrix(): ArrayList<ArrayList<Int>>{
        return adjMatrix
    }
    fun getAttributesArrayForm(): Array<String?> {
        return arrayOf("$id",name, description, "$num_Tasks","$startDate","$endDate")
    }
    fun getName(): String?{
        return name;
    }
    fun getDescription(): String?{
        if (description == null){
            description = ""
        }
        return description
    }
    fun divideTaskLength(){

    }
    fun setName(Name:String){
        name = Name;
    }
    fun setDesc(Desc:String){
        description = Desc
    }
    fun createTask(): Task{
        //makes Task Object for each input
        num_Tasks+=1
        val task = Task("",num_Tasks,null,null)
        taskList.add(task)

        return task
    }
    fun removeTask(task: Task){
        num_Tasks-=1
        for (i in 0 .. num_Tasks-1){
            if (taskList[i].equals(task)){
                val tasks = taskList.takeLast(i)
                for (i in tasks){
                    i.setId(i.getId()?.minus(1))
                }
                break
            }
        }
        taskList.remove(task)
    }
    fun getTaskList(): ArrayList<Task>{
        return taskList
    }
    fun getId():Int{
        return id
    }


    inner class Task(
        private var name: String,
        private var id: Int?,
        private var description: String?,
        private var parent: Int?
    ){
        private var startDate:LocalDate? = null;
        private var endDate:LocalDate? = null;
        fun getParent(): Int? {
           return parent
        }
        fun setDates(start: LocalDate, end: LocalDate){
            startDate = start
            endDate = end
        }

        fun getId(): Int? {
            return id
        }
        fun setId(id: Int?){
            this.id = id
        }
        fun setName(name: String){
            this.name = name
        }
        fun getName(): String{
            return this.name
        }
        fun getDates(): Array<LocalDate?>{
            return arrayOf(startDate, endDate)
        }
        fun setDesc(desc:String){
            this.description = desc
        }
        fun setParent(p:Int){
            this.parent = p
        }

        override fun toString(): String{
            return name
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

