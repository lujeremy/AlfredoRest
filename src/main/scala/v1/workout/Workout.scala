package v1.workout

import utils.JsonObjectMapper

case class Workout(
                    id: Long,
                    name: String,
                    sets: Int,
                    reps: Int,
                    weight: Int,
                    time: Long) {

  def toJson: String =
    JsonObjectMapper.mapper.writeValueAsString(this)
}

object Workout {

}
