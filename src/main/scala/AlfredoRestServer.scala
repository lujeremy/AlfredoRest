import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder._
import org.slf4j.{Logger, LoggerFactory}
import v1.workout.WorkoutController

object AlfredoRestServer {

  implicit val logger: Logger = LoggerFactory.getLogger(this.getClass.getName)

  def main(args: Array[String]): Unit = {
    val controller = new WorkoutController()
    val app = Javalin.create().port(80).start

    app.get("/", ctx => ctx.result("Hi, the main page is currently a work in progress," +
      " check out the api at alfredobot.com/api"))
    app.get("/root", controller.root)

    app.routes(() => {
      path("api", () => {
        get(controller.getApiHome)
        path("workouts", () => {
          get(controller.getAllWorkouts)
          path(":id", () => {
            get(controller.getWorkout)
          })
        })
      })
    })
  }
}