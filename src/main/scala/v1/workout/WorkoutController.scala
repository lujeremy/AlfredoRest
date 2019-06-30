package v1.workout

import io.javalin.Context
import org.slf4j.{Logger, LoggerFactory}
import utils.JsonObjectMapper

class WorkoutController {

  implicit val logger: Logger = LoggerFactory.getLogger(this.getClass.getName)
  val mapper = JsonObjectMapper.mapper
  val loader = new WorkoutLoader("credentials.txt")

  def root(ctx: Context): Unit = {
    ctx.result("root beer")
  }

  def getApiHome(ctx: Context): Unit = {
    logger.info("Fetching api home")
    ctx.result("Hello, this is AlfredoRest. I only support GETs at the moment, please" +
      " check out alfredobot.com/api/workouts or alfredobot.com/api/workouts/{id}")
  }

  def getAllWorkouts(ctx: Context): Unit = {
    logger.info("Fetching all workouts")
    val workoutMap: Map[String, List[Workout]] = loader.loadData()
    ctx.result(mapper.writeValueAsString(workoutMap)).contentType("application/json")
  }

  def getWorkout(ctx: Context): Unit = {
    logger.info(s"Fetching workout with ID: ${ctx.pathParam(":id")}")
    val workoutMap: Map[String, List[Workout]] = loader.loadData()
    val maybeWorkout = workoutMap("workouts").find(n => n.id == ctx.pathParam(":id").toLong)

    maybeWorkout.map(workout => ctx.result(mapper.writeValueAsString(workout)).contentType("application/json"))
      .getOrElse(ctx.result("Nothing found"))
  }
}

