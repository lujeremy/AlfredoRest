package v1.workout

import java.sql.DriverManager

import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable.ListBuffer
import scala.io.Source

class WorkoutLoader(filename: String) {

  implicit val logger: Logger = LoggerFactory.getLogger(this.getClass.getName)

  def loadData() : Map[String, List[Workout]]= {
    logger.info("Loading from database...")
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines().toList
    bufferedSource.close

    val url = lines(0)
    val username = lines(1)
    val password = lines(2)

    val outList = new ListBuffer[Workout]()
    try {
      Class.forName("com.mysql.cj.jdbc.Driver")
      val connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs   = statement.executeQuery("SELECT * FROM workouts")
      while (rs.next()) {
        val item = Workout(
          rs.getLong("ID"),
          rs.getString("Workout"),
          rs.getInt("Sets"),
          rs.getInt("Reps"),
          rs.getInt("Weight"),
          rs.getLong("Time")
        )
        outList.append(item)
      }
      connection.close
      val workoutMap = Map("workouts" -> outList.toList)
      workoutMap
    } catch {
      case e: Exception =>
        logger.error("Something went wrong")
        e.printStackTrace
        Map.empty
    }
  }
}
