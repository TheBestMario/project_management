import java.sql.*


//Please use JDK version 21
//If it doesn't work
//please go Project Structure > Modules > Dependencies
// and add the .jar of the jfreechart library in this same folder.
fun main() {
    try {
        val connection = DriverManager.getConnection("jdbc:sqlite:projects_database.db")
        val results = connection.metaData.catalogs
        while (results.next()){
            val databaseName = results.getString(1)
        }
        results.close()
    } catch (e: Exception){
        println("problem occured with database")
    }
    MenuHandler()
}

