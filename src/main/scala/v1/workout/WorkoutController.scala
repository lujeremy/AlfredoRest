package v1.workout

import io.javalin.Context
import utils.JsonObjectMapper

class WorkoutController {
  val mapper = JsonObjectMapper.mapper
  val loader = new WorkoutLoader("credentials.txt")

  def root(ctx: Context): Unit = {
    ctx.result("root beer")
  }

  def getAllWorkouts(ctx: Context): Unit = {
    val workoutMap: Map[String, List[Workout]] = loader.loadData()
    ctx.result(mapper.writeValueAsString(workoutMap))
  }

  def getWorkout(ctx: Context): Unit = {
    val workoutMap: Map[String, List[Workout]] = loader.loadData()
    val maybeWorkout = workoutMap("workouts").find(n => n.id == ctx.pathParam(":id").toLong)

    maybeWorkout.map(workout => ctx.result(mapper.writeValueAsString(workout)))
      .getOrElse(ctx.result("Nothing found"))
  }
}

