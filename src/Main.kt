import java.sql.*


//Please use JDK version 21.
//
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

